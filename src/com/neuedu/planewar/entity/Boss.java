package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;

import java.awt.*;
import java.util.Random;

public class Boss extends PlaneWarObject {

    public static Image[] images = new Image[3];

    static {
        for (int i = 0; i < images.length; i++) {
            images[i] = ImageUtil.images.get("boss0" + (i + 1));
        }
    }

    public Boss() {
    }

    double theta = Math.random() * Math.PI;

    int count = 0;

    static Random r = new Random();

    boolean good = false;

    /**
     * Boss的血量
     *
     * @param g
     */
    public int HP = 3000;
    public double maxHP = HP;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Boss(PlanWarClient pwc, int x, int y, boolean good) {
        this.pwc = pwc;
        this.x = x;
        this.y = y;
        this.speed = 10;
        this.width = images[0].getWidth(null);
        this.height = images[0].getHeight(null);
        this.good = good;
    }


    @Override
    public void draw(Graphics g) {
        if (count > 1) {
            count = 0;
        }
        g.drawImage(images[count], x, y, null);
        count++;
        move();
        if (r.nextInt(1000) >= 900) {
            shoot();
        }
        bb.draw(g);
    }

    @Override
    public void move() {
        if (x > 1000){
            x -= speed;
        }else {
            y += (int) (speed * Math.sin(theta));
            // 弹上下边
            if (y <= 30 || y >= Constant.FRAME_HEIGHT - this.height){
                theta = -theta;
            }
            theta = Math.PI - theta;
        }
    }

    public void shoot() {
        BossBullet bossBullet = new BossBullet(pwc, this.x, this.y + this.height / 2, good);
        this.pwc.bossBullets.add(bossBullet);
    }

    public BossBloodBar bb = new BossBloodBar();

    /**
     * 血条的内部类
     */
    class BossBloodBar {
        public void draw(Graphics g) {
            Color c = g.getColor();
            if (HP > (maxHP * 0.7) && HP <= maxHP) {
                g.setColor(Color.GREEN);
            } else if (HP > (maxHP * 0.3) && HP <= (maxHP * 0.7)) {
                g.setColor(Color.ORANGE);
            } else {
                g.setColor(Color.RED);
            }
            g.drawRect(x, y - 10, width, 10);
            g.fillRect(x, y - 10, (int) (width * (HP / maxHP)), 10);
            g.setColor(c);
        }
    }

    /**
     * 判断是否活着
     */
    public boolean life() {
        if (this.HP > 0) {
            return true;
        }
        return false;
    }

}
