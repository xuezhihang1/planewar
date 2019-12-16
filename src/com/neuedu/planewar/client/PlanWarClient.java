package com.neuedu.planewar.client;

import com.neuedu.planewar.common.CommonFrame;
import com.neuedu.planewar.common.MusicUtil;
import com.neuedu.planewar.constant.Constant;
import com.neuedu.planewar.entity.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 飞机大战的项目客户端
 */
public class PlanWarClient extends CommonFrame {
    Random r = new Random();

    public Plane myPlane = new Plane(this, 400, 400);

    public BackGround backGround = new BackGround(this, 0, 0);
    public BackGround backGround2 = new BackGround(this, backGround.x + backGround.width, 0);

    public List<Bullet> bullets = new ArrayList<>();

    public List<EnemyPlane> enemyPlanes = new ArrayList<>();

    public List<Explode> explodes = new ArrayList<>();

    public List<Item> items = new ArrayList<>();

    public List<BossBullet> bossBullets = new ArrayList<>();

    public Boss boss = new Boss(this, 1800, 400, false);

    public StartGame startGame = new StartGame(this, 0, 0);
    public GameOver gameOver = new GameOver(this, 0, 0);

    {
        int a = 0;
        while (a < 20) {
            for (int i = 0; i < 1; i++) {
                EnemyPlane enemyPlane = new EnemyPlane(this, 1400 + ((int) (1000 * Math.random())), 100 + ((int) (Constant.FRAME_HEIGHT * Math.random())));
                enemyPlanes.add(enemyPlane);
            }
            a++;
        }

    }
    static{
        //加载BGM
        new MusicUtil("com/neuedu/planewar/vedio/01.mp3",true).start();
    }


    @Override
    public void loadFrame(String title) {
        super.loadFrame(title);

        //添加键盘监听器
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                myPlane.keyPressed(e);
                keyPressed2(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                myPlane.keyReleased(e);
            }
        });

    }


    public void paint(Graphics g) {
        if (boss.getHP() > 0) {
            if (myPlane.getHP() > 0) {
                if (!start && !over) {
                    startGame.draw(g);
                }
                if (!start && over) {
                    gameOver.draw(g);
                }
                if (start && !over) {
                    //背景图片
                    backGround.draw(g);
                    backGround2.draw(g);
                    while (backGround.x < -backGround.width) {
                        backGround.x = backGround2.x + backGround2.width;
                        backGround.draw(g);
                    }
                    while (backGround2.x < -backGround2.width) {
                        backGround2.x = backGround.x + backGround.width;
                        backGround2.draw(g);
                    }
                    myPlane.draw(g);

                    //画敌方飞机
                    for (int i = 0; i < enemyPlanes.size(); i++) {
                        EnemyPlane enemyPlane = enemyPlanes.get(i);
                        enemyPlane.draw(g);
                        enemyPlane.strike(myPlane, enemyPlanes.get(i));
                        if (enemyPlane.x < 0) {
                            enemyPlane.x = Constant.FRAME_WIDTH;
                            enemyPlane.y = (int) (Math.random() * (Constant.FRAME_HEIGHT - 100));
                        }
                    }
                    //画子弹
                    for (int i = 0; i < bullets.size(); i++) {
                        Bullet b = bullets.get(i);
                        b.draw(g);
                        b.hitEnemyPlane(enemyPlanes);
                        b.hitPlane(myPlane);

                    }
                    //画爆炸
                    for (int i = 0; i < explodes.size(); i++) {
                        Explode e = explodes.get(i);
                        e.draw(g);
                    }
                    //画道具
                    for (int i = 0; i < items.size(); i++) {
                        items.get(i).draw(g);
                    }
                    //画boss
                    if (enemyPlanes.size() == 0) {
                        if (boss.getHP() > 0) {
                            boss.draw(g);
                            for (int i = 0; i < bullets.size(); i++) {
                                Bullet b = bullets.get(i);
                                b.hitBoss(boss);
                            }
                        }
                    }
                    //画boss子弹
                    for (int i = 0; i < bossBullets.size(); i++) {
                        BossBullet bb = bossBullets.get(i);
                        bb.draw(g);
                        bb.hitPlane(myPlane);
                    }
                    myPlane.eatItem(items);

                    Font f = g.getFont();
                    Color c = g.getColor();
                    g.setColor(Color.ORANGE);
                    g.setFont(new Font("微软雅黑", Font.BOLD, 30));
                    g.drawString("子弹容器大小:" + bullets.size(), 100, 100);
                    g.drawString("敌方飞机剩余:" + enemyPlanes.size(), 100, 50);
                    g.drawString("主战飞机的血量:" + myPlane.getHP(), 100, 150);
                    g.drawString("boss的血量:" + boss.getHP(), 100, 200);
                    g.drawString("主战飞机的防御值:" + myPlane.DEF, 100, 250);
                    g.setFont(f);
                    g.setColor(c);
                }
            } else {
                gameOver.draw(g);
            }
        } else {
            gameOver.draw(g);
        }
    }

    boolean start, over;

    public void keyPressed2(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_P:
                start = true;
                break;
        }
    }

    public static void main(String[] args) {
        new PlanWarClient().loadFrame("飞机大战");
    }
}
