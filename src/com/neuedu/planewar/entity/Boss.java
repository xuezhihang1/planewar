package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;
import java.util.Random;

public class Boss extends PlaneWarObject{

    public static Image[] images = new Image[3];

    static{
        for (int i = 0; i < images.length; i++) {
            images[i] = ImageUtil.images.get("boss0"+(i+1));
        }
    }
    public Boss(){}
    int count = 0;

    static Random r = new Random();

    boolean good = false;

    /**
     * Boss的血量
     * @param g
     */
    public int HP = 1000;
    public double maxHP = HP;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Boss(PlanWarClient pwc,int x,int y){
        this.pwc = pwc;
        this.x=  x;
        this.y = y;
        this.speed = 10;
        this.width = images[0].getWidth(null);
        this.height = images[0].getHeight(null);
    }



    @Override
    public void draw(Graphics g) {
        if (count > 1){
            count = 0;
        }
        g.drawImage(images[count],x,y,null);
        count++;
        move();
        if (r.nextInt(1000)>=900){
            shoot2();
        }
        bb.draw(g);
    }
    @Override
    public void move() {
        this.x -= speed;
        if (x == 1150){
            speed = 0;
        }
    }
    public void shoot2(){
        BossBullet bossBullet = new BossBullet(pwc,this.x,this.y+this.height/2,good);
        this.pwc.bossBullets.add(bossBullet);
    }

    public BossBloodBar bb = new BossBloodBar();
    /**
     * 血条的内部类
     */
    class BossBloodBar {
        public void draw(Graphics g) {
            Color c = g.getColor();
            if (HP > (maxHP * 0.7) && HP<=maxHP){
                g.setColor(Color.GREEN);
            }else if(HP > (maxHP * 0.3) && HP<=(maxHP * 0.7)){
                g.setColor(Color.ORANGE);
            }else{
                g.setColor(Color.RED);
            }
            g.drawRect(x,y-10,width,10);
            g.fillRect(x,y-10,(int)(width * (HP / maxHP)),10);
            g.setColor(c);
        }
    }

}
