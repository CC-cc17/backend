package com.skyblue.sys.dto;

public class BarDataDTO {
    private String date;
    private Integer newCount;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNewCount() {
        return newCount;
    }

    public void setNewCount(Integer newCount) {
        this.newCount = newCount;
    }

    public BarDataDTO(String date, Integer newCount) {
        this.date = date;
        this.newCount = newCount;
    }
}
