package com.code.chang.dagger2studydemo.entity;

/**
 * Created by Administrator on 2017/3/21 11:09
 * mail：changliugang@sina.com
 */
public class Cloth {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color + "布料";
    }
}
