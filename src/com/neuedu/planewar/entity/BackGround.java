package com.neuedu.planewar.entity;


import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;

public class BackGround extends PlaneWarObject {
    static Image image = ImageUtil.images.get("background01");

    public BackGround(){}

    public BackGround(PlanWarClient pwc,int x,int y){
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        this.speed = 5;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    @Override
    public void move() {
        this.x -= speed;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,x,y,null);
        move();
    }
}
