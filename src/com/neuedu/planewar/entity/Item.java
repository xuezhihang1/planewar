package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;

import java.awt.*;

public class Item extends PlaneWarObject {

    double theta = Math.random() * (Math.PI * 2);

    public Item() {
    }

    int type;

    public Item(PlanWarClient pwc, int x, int y, int type) {
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        this.speed = 10;
        this.type = type;
        switch (this.type){
            case 0:
                this.img = ImageUtil.images.get("HP");
                break;
            case 1:
                this.img = ImageUtil.images.get("DEF");
                break;
             default:
                break;
        }

        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, x, y, null);

        move();
    }

    @Override
    public void move() {
        x += (int) (speed * Math.cos(theta));
        y += (int) (speed * Math.sin(theta));
        //弹上下边
        if (y <= 30 || y >= Constant.FRAME_HEIGHT - this.height) {
            theta = -theta;
        }
        if (x <= 0 || x >= Constant.FRAME_WIDTH - this.width) {
            theta = Math.PI - theta;
        }
    }
}
