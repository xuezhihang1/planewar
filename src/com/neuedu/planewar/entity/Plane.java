package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;
import com.neuedu.planewar.constant.Constant;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends PlaneWarObject {

    static Image[] imgs = new Image[50];
    public PlanWarClient pwc;
    static{
        for (int i=0;i<3;i++){
            imgs[i] = ImageUtil.images.get("myplane0"+(i+1));
        }
    }

    public Plane(){};

    /**
     * 初始化
     * @param x
     * @param y
     */
    public Plane(int x,int y){
        this.pwc = pwc;
        this.x = x;
        this.y = y;
//        this.img = ImageUtil.images.get("myplane");
        this.width = imgs[0].getWidth(null);
        this.height = imgs[0].getHeight(null);
        this.speed = 10;
    }
    public Plane(int x,int y,String imgPath){
        this(x,y);
//        this.img = FrameUtil.getImage(imgPath);
    }

    /**
     * 飞机运动
     */
    @Override
    public void move() {
        if (left) x -= speed;
        if (up) y -= speed;
        if (right) x += speed;
        if (down) y += speed;
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
        g.drawImage(imgs[count],x,y,null);
        if (count > 2){
            count = 0;
        }
        count++;
        move();
    }

    /**
     * 使用开关法创建出4个表示方向的boolean变量
     */
    public boolean left,up,right,down;

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
        Bullet bullet = new Bullet(this.x+this.width/2-8,this.y-50);
        this.pwc.bullets.add(bullet);
    }

}
