package com.neuedu.planewar.client;

import com.neuedu.planewar.common.CommonFrame;
import com.neuedu.planewar.entity.Bullet;
import com.neuedu.planewar.entity.Plane;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 飞机大战的项目客户端
 */
public class PlanWarClient extends CommonFrame {

    public Plane myPlane = new Plane(400,500);

    @Override
    public void loadFrame(String title){
        super.loadFrame(title);
//        this.setIconImage(FrameUtil.getImage(""));


        //添加键盘监听器
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                myPlane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
               myPlane.keyReleased(e);
            }
        });
    }

    public List<Bullet> bullets=new ArrayList<>();
    public void paint(Graphics g) {
        myPlane.draw(g);
        //画子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
        }
    }

    public static void main(String[] args){
        new PlanWarClient().loadFrame("飞机大战");
    }
}
