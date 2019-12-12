package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Bullet extends PlaneWarObject {

    public Bullet(){}

    /**
     * 表示敌我的boolean 变量 true为我方 false为敌方
     */
    boolean good;

    public Bullet(PlanWarClient pwc, int x, int y,boolean good){
        this.pwc = pwc;
        this.x=x;
        this.y=y;
        this.good = good;
        if (good){
            this.img=ImageUtil.images.get("bullet01");
        }else{
            this.img=ImageUtil.images.get("enemyplane_bullet01.png");
        }
        this.width=img.getWidth(null);
        this.height=img.getHeight(null);
        this.speed=20;

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,x,y,null);
        move();
    }

    @Override
    public void move() {
        if (good){
            x +=speed;
        }else{
            x -=speed;
        }
        outOfBounds();
    }

    private void outOfBounds(){
        if (x < -500 || x > Constant.FRAME_WIDTH + 500 || y < -500 || y >Constant.FRAME_HEIGHT){
            //移除
            this.pwc.bullets.remove(this);
        }
    }

    static Random r = new Random();


    /**
     * 击打敌方飞机
     * @param enemyPlane
     * @return
     */

    private boolean hitEnemyPlane(EnemyPlane enemyPlane){
        if (this.good != enemyPlane.good && this.getRectangle().intersects(enemyPlane.getRectangle())){
            //打到之后 该enemyplane 死掉，同时子弹销毁
            this.pwc.enemyPlanes.remove(enemyPlane);
            //敌方飞机死亡时，爆炸效果出现
            Explode e = new Explode(pwc,enemyPlane.x,enemyPlane.y);
            this.pwc.explodes.add(e);

            if (r.nextInt(100)>30){
                //爆炸之后出道具
                Item item = new Item(pwc,enemyPlane.x,enemyPlane.y,r.nextInt(2));
                this.pwc.items.add(item);
            }

            this.pwc.bullets.remove(this);
            return true;
        }
        return false;
    }

    /**
     * 子弹击打主战飞机
     * @param myplane
     * @return
     */

    public boolean hitPlane(Plane myplane){
        if (this.good != myplane.good && this.getRectangle().intersects(myplane.getRectangle())){
            //当打到我方飞机是掉血
            myplane.setHP(myplane.getHP()-10);
            if (myplane.getHP() <= 0){
                //我方飞机死亡时，爆炸效果出现
                Explode e = new Explode(pwc,myplane.x,myplane.y);
                this.pwc.explodes.add(e);
            }
            this.pwc.bullets.remove(this);
            return true;
        }
        return false;
    }

    public boolean hitEnemyPlane(List<EnemyPlane> enemyPlanes){
        for (int i = 0;i<enemyPlanes.size();i++){
            EnemyPlane ep = enemyPlanes.get(i);
            if (hitEnemyPlane(ep)){
                return true;
            }
        }
        return false;
    }
    public boolean hitBoss(Boss boss){
        if (this.good != boss.good && this.getRectangle().intersects(boss.getRectangle())){
            //当打到boss时掉血
            boss.setHP(boss.getHP()-10);
            if (boss.getHP() >= 0){
                //boss死亡时，爆炸效果出现
                Explode e = new Explode(pwc,boss.x,boss.y);
                this.pwc.explodes.add(e);
//                this.pwc.bosses.remove(boss);
            }
            this.pwc.bullets.remove(this);
            return true;
        }
        return false;
    }

}