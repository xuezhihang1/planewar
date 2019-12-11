package com.neuedu.planewar.client;

import com.neuedu.planewar.common.CommonFrame;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.entity.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 飞机大战的项目客户端
 */
public class PlanWarClient extends CommonFrame {

    public Plane myPlane = new Plane(this,400,400);


    public List<Bullet> bullets=new ArrayList<>();

    public List<EnemyPlane> enemyPlanes = new ArrayList<>();

    public List<Explode> explodes = new ArrayList<>();

    public List<Item> items = new ArrayList<>();

    public List<Boss> bosses = new ArrayList<>();

    public Boss boss=new Boss(this,1500,400);

    {
        for(int i = 0;i < 10;i++){
            EnemyPlane enemyPlane = new EnemyPlane(this,Constant.FRAME_WIDTH,100+(i*80));
            enemyPlanes.add(enemyPlane);
        }
        bosses.add(boss);
    }


    @Override
    public void loadFrame(String title){
        super.loadFrame(title);

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

//        EnemyPlane enemyPlane = new EnemyPlane(this,600,400);
//        enemyPlanes.add(enemyPlane);
    }




    public void paint(Graphics g) {
        myPlane.draw(g);
        //画子弹
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.draw(g);
            b.hitEnemyPlane(enemyPlanes);
            b.hitPlane(myPlane);
        }

        for (int i = 0; i < enemyPlanes.size(); i++) {
            enemyPlanes.get(i).draw(g);
        }

        for (int i = 0;i<explodes.size();i++){
            Explode e = explodes.get(i);
            e.draw(g);
        }

        for (int i = 0; i < items.size(); i++) {
            items.get(i).draw(g);
        }
        for (int i = 0; i < bosses.size(); i++) {
            bosses.get(i).draw(g);
        }
        myPlane.eatItem(items);

        Font f = g.getFont();

        g.setFont(new Font("微软雅黑",Font.BOLD,30));
        g.drawString("子弹容器大小:"+bullets.size(),100,100);
        g.drawString("主战飞机的血量:"+myPlane.getHP(),100,150);
        g.drawString("主战飞机的防御值:"+myPlane.DEF,100,200);
        g.setFont(f);
    }


    public static void main(String[] args){
        new PlanWarClient().loadFrame("飞机大战");
    }
}
