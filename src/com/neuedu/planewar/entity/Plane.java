package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * 飞机类
 */
public class Plane extends PlaneWarObject {

    static Image[] imgs = new Image[3];

    static{
       for (int i=0;i<3;i++){
           imgs[i] = ImageUtil.images.get("myplane0"+(i+1));
       }
    }

    boolean good = true;
    /**
     * 飞机的血量
     */
    public int HP = 100;
    public double maxHP = HP;
    public int DEF = 100;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public Plane(){};

    /**
     * 初始化
     * @param x
     * @param y
     */
    public Plane(PlanWarClient pwc,int x,int y){
        this.x = x;
        this.y = y;
        this.pwc = pwc;
//        this.img = ImageUtil.images.get("myplane");
        this.width = imgs[0].getWidth(null);
        this.height = imgs[0].getHeight(null);
        this.speed = 10;
    }


    /**
     * 飞机运动
     */
    @Override
    public void move() {
        if (left) {
            x -= speed;
        }
        if (up) {
            y -= speed;
        }
        if (right){
            x += speed;
        }
        if (down) {
            y += speed;
        }
        if (shoot){
            shoot();
        }
        outOfBounds();
    }

    int count = 0;

    /**
     * 画飞机
     * @param g
     */
    @Override
    public void draw(Graphics g) {
//        g.drawImage(img,x,y,(null));
        //画图组的方法
        if (count > 2){
            count = 0;
        }
        g.drawImage(imgs[count],x,y,null);
        count++;
        move();
        if (shoot){
            shoot();
        }
        bb.draw(g);
    }

    public BloodBar bb = new BloodBar();
    /**
     * 血条的内部类
     */
    class BloodBar {
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

    /**
     * 使用开关法创建出4个表示方向的boolean变量
     */
    public boolean left,up,right,down,shoot;

    /**
     * 按键按下的方法
     * @param e
     */
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_J:
                shoot=true;
                break;
        }
    }

    /**
     * 按键弹起的方法
     * @param e
     */
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_J:
                shoot=false;
                break;
        }
    }
    /**
     * 飞机出界判断
     */
    public void outOfBounds(){
        if (x <= 0){
            x = 0;
        }
        if (y <= 30){
            y = 30;
        }
        if (x >= Constant.FRAME_WIDTH - this.width){
            x = Constant.FRAME_WIDTH - this.width;
        }
        if (y >= Constant.FRAME_HEIGHT - this.height){
            y = Constant.FRAME_HEIGHT - this.height;
        }
    }
    /**
     * 添加子弹
     */
    public void shoot(){
        Bullet bullet = new Bullet(pwc,this.x+this.width,this.y+height/2,good);
        this.pwc.bullets.add(bullet);
    }
    /**
     *  吃道具的方法
     */
    private boolean eatItem(Item item){
        if (this.getRectangle().intersects(item.getRectangle())){
            switch (item.type){
                case 0:
                    //加血
                    this.HP +=20;
                    if (this.HP >=this.maxHP){
                        this.HP = (int) this.maxHP;
                    }
                    break;
                case 1:
                    //加防御
                    this.DEF +=100;
                    break;
            }
            this.pwc.items.remove(item);
            return true;
        }
        return false;
    }
    public boolean eatItem(List<Item> items){
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (eatItem(item)){
                return true;
            }
        }
        return false;
    }
}
