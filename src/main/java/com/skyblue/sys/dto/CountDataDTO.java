package com.skyblue.sys.dto;

public class CountDataDTO {
    private String name;
    private int value;
    private String icon;
    private String color;

    public CountDataDTO(String name, int value, String icon, String color) {
        this.name = name;
        this.value = value;
        this.icon = icon;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}