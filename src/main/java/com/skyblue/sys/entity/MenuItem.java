package com.skyblue.sys.entity;

import java.util.List;

public class MenuItem {
    private String path;
    private String name;
    private String label;
    private String icon;
    private String url;
    private List<MenuItem> children;

    public MenuItem(String path, String name, String label, String icon, String url, List<MenuItem> children) {
        this.path = path;
        this.name = name;
        this.label = label;
        this.icon = icon;
        this.url = url;
        this.children = children;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItem> children) {
        this.children = children;
    }
}
