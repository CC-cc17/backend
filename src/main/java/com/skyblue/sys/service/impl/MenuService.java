package com.skyblue.sys.service.impl;
import com.skyblue.sys.entity.MenuItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service

public class MenuService implements com.skyblue.sys.service.MenuService {

    public List<MenuItem> getMenuByRole(String role) {
        // 这里是一个简化的例子，实际应用中你可能需要根据角色从数据库等地方获取菜单配置
        List<MenuItem> menuItems = new ArrayList<>();
        if ("admin".equals(role)) {
            // 添加管理员的菜单项
            menuItems.add(new MenuItem("/console/home", "home", "首页", "homeFilled", "home/index", null));
            menuItems.add(new MenuItem("/console/jobmatch", "jobmatch", "职业配对管理", "suitcaseLine", "jobmatch/index", null));
            menuItems.add(new MenuItem("/console/user", "user", "用户管理", "user", "user/index", null));
        } else if ("student".equals(role) || "company".equals(role)) {
            // 添加普通用户的菜单项
            List<MenuItem> children = new ArrayList<>();
            children.add(new MenuItem("/console/matchinfo", "matchinfo", "配对信息", "document", "matchinfo/index", null));
            children.add(new MenuItem("/console/matchresult", "matchresult", "配对结果", "messageBox", "matchresult/index", null));
            menuItems.add(new MenuItem("/console/home", "home", "首页", "homeFilled", "home/index", null));
            menuItems.add(new MenuItem(null, "jobmatch", "职业配对", "suitcaseLine", null, children));
        }
        return menuItems;
    }
}