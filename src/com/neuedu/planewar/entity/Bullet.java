package com.neuedu.planewar.entity;

import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;

public class Bullet extends PlaneWarObject {
    public Bullet(){}
    public Bullet(int x,int y){
        this.x=x;
        this.y=y;
        this.img=ImageUtil.images.get("bullet01");
        this.width=img.getWidth(null);
        this.height=img.getHeight(null);
        this.speed=30;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,x,y,null);
        move();
    }

    @Override
    public void move() {
        x+=speed;
    }
}