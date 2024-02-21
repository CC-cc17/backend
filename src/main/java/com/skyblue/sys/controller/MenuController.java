package com.skyblue.sys.controller;
import com.skyblue.sys.entity.MenuItem;
import com.skyblue.sys.service.ISysUserService;
import com.skyblue.sys.service.impl.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.skyblue.common.vo.Result;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permission")
@CrossOrigin
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/getMenu")
    public ResponseEntity<Result<Map<String, List<MenuItem>>>> getMenu(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body(Result.fail(400, "Token不能为空"));
        }

        // 根据token获取用户信息
        Map<String, Object> userMap = sysUserService.getUserInfo(token);
        if (userMap == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Result.fail(401, "Token无效或过期"));
        }

        // 假定用户的角色是存储在"userType"里
        String role = (String) userMap.get("role");
        // 获取菜单列表
        List<MenuItem> menuItems = menuService.getMenuByRole(role);
        if (menuItems != null) {
            Map<String, List<MenuItem>> menuData = Collections.singletonMap("menu", menuItems);
            return ResponseEntity.ok(Result.success(menuData, "获取成功"));
        } else {
            return ResponseEntity.ok(Result.fail(404, "获取菜单失败"));
        }
    }
}