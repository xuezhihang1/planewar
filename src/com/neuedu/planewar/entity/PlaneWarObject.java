package com.neuedu.planewar.entity;

import java.awt.*;

/**
 * 项目中所有类的父类
 */
public abstract class PlaneWarObject implements Moveable,Drawable{
    //x坐标
    public int x;
    //y坐标
    public int y;
    //图片
    public Image img;
    //宽度
    public int width;
    //高度
    public int height;
    //速度
    public int speed;

    /**
     * 重写Moveable接口中的move方法
     */
    @Override
    public void move(){

    }
    /**
     * 重写Drawable接口中的draw方法
     */
    @Override
    public void draw(Graphics g){

    }
}
