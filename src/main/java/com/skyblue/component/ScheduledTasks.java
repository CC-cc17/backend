package com.skyblue.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ScheduledTasks {

    private final DataSource dataSource;

    public ScheduledTasks(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Scheduled(fixedDelay = 10000) // 每10秒执行一次，您可以根据需要调整
    public void callStoredProcedure() {
        try (Connection conn = dataSource.getConnection();
             CallableStatement statement = conn.prepareCall("{CALL automated_pairing()}")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
