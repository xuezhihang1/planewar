package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;
import java.util.Random;

public class Boss extends PlaneWarObject{

    public static Image[] images = new Image[2];

    static{
        for (int i = 0; i < images.length; i++) {
            images[i] = ImageUtil.images.get("boss0"+(i+1));
        }
    }
    public Boss(){}

    public Boss(PlanWarClient pwc,int x,int y){
        this.pwc = pwc;
        this.x=  x;
        this.y = y;
        this.speed = 10;
        this.width = images[0].getWidth(null);
        this.height = images[0].getHeight(null);
    }

    int count = 0;

    static Random r = new Random();

    boolean good = false;

    @Override
    public void draw(Graphics g) {
        if (count > 1){
            count = 0;
        }
        g.drawImage(images[count],x,y,null);
        count++;
        move();
    }

    @Override
    public void move() {
        this.x -= speed;
        if (x == 800){
            speed = 0;
            if (speed == 0){
                this.y -= speed;
                if (y == 0){
                    this.y += speed;
                }
            }
        }
    }
//    public void shoot(){
//        Bullet bullet = new Bullet(pwc,this.x+this.width,this.y+height/2,good);
//        this.pwc.bullets.add(bullet);
//    }
}
