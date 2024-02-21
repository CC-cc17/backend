CREATE PROCEDURE `automated_pairing`()
BEGIN
    -- 定义变量
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_student_id INT;
    DECLARE v_company_id INT;
    DECLARE v_match_time TIMESTAMP;

    -- 定义游标
    DECLARE cur1 CURSOR FOR
        SELECT 
            s.id, c.id
        FROM 
            student_detail s
        JOIN 
            company_detail c ON s.industry_type = c.industry_type
        WHERE 
            (c.gender_require = 'Any' OR c.gender_require = s.gender)
            AND s.available_start <= c.position_start
            AND s.available_end >= c.position_end
            AND s.match_status = 0 -- 仅选择未被匹配的学生
            AND c.quota > 0 -- 仅选择有名额的公司
        FOR UPDATE; -- 使用悲观锁锁定选中的行

    -- 定义未找到记录时的继续处理程序
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- 开启事务
    START TRANSACTION;

    -- 打开游标
    OPEN cur1;

    read_loop: LOOP
        -- 从游标取得数据
        FETCH cur1 INTO v_student_id, v_company_id;
        
        -- 当游标结束时退出循环
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- 更新公司配额，使用悲观锁确保数据一致性
        UPDATE company_detail SET quota = quota - 1 WHERE id = v_company_id AND quota > 0;

        -- 如果公司配额更新成功，则更新学生状态
        IF ROW_COUNT() > 0 THEN
            UPDATE student_detail SET match_status = 1 WHERE id = v_student_id AND match_status = 0;
            
            -- 如果学生状态更新成功，则记录配对结果
            IF ROW_COUNT() > 0 THEN
                -- 将配对结果插入到job_match表
                INSERT INTO job_match (student_id, company_id, match_time)
                VALUES (v_student_id, v_company_id, NOW());
            END IF;
        END IF;
        
    END LOOP;

    CLOSE cur1;

    -- 提交事务
    COMMIT;

END