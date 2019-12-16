package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;

import java.awt.*;
import java.util.Random;

public class BossBullet extends PlaneWarObject{

    public BossBullet(){}
    //表示敌我的boolean变量
    boolean good;
    Random r = new Random();
    public BossBullet(PlanWarClient pwc,int x,int y,boolean good){
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        this.good = good;
        this.img=ImageUtil.images.get("boss_bullet01");
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.speed = 20;
    }

    @Override
    public void draw(Graphics g) {
            g.drawImage(img,x,y,null);
            move();
    }

    @Override
    public void move() {
        x -= speed;

        outOfBounds();
    }
    public void outOfBounds(){
        if (x < -500 || x > Constant.FRAME_WIDTH + 500 || y < -500 || y >Constant.FRAME_HEIGHT){
            //移除
            this.pwc.bullets.remove(this);
        }
    }
    public boolean hitPlane(Plane myplane){
            if (this.good != myplane.good && this.getRectangle().intersects(myplane.getRectangle())){
                //当打到我方飞机是掉血
                myplane.setHP(myplane.getHP()-20);
                if (myplane.getHP() <= 0){
                    //我方飞机死亡时，爆炸效果出现
                    Explode e = new Explode(pwc,myplane.x,myplane.y);
                    this.pwc.explodes.add(e);
                }
                this.pwc.bossBullets.remove(this);
                return true;
            }
        return false;
    }
}
