package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;
import java.util.Random;

/**
 * 敌方飞机类
 */
public class EnemyPlane extends PlaneWarObject{

    public static Image[] images = new Image[6];
    static{
        for (int i = 0;i < images.length;i++){
            images[i] = ImageUtil.images.get("EnemyPlane_0"+(i+1));
        }
    }

    public EnemyPlane(){}

    public EnemyPlane(PlanWarClient pwc,int x,int y){
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        this.speed = 10;
        this.width = images[0].getWidth(null);
        this.height = images[0].getHeight(null);
    }

    int count = 0;

    static Random r =new Random();

    boolean good = false;

    @Override
    public void draw(Graphics g) {
        if (count > 2){
            count = 0;
        }
        g.drawImage(images[count],x,y,null);
        count++;

        move();
        if (r.nextInt(1000) >= 990){
            shoot();
        }
    }

    @Override
    public void move() {
        this.x -= speed;
    }

    public void shoot(){
        Bullet bullet = new Bullet(pwc,this.x,this.y+height/2-15,good);
        this.pwc.bullets.add(bullet);
    }
}
