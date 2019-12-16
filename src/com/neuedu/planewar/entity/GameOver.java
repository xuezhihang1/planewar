package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;

public class GameOver extends PlaneWarObject{
    static Image[] images = new Image[3];
    static{
        images[0] = ImageUtil.images.get("clear");
        images[1] = ImageUtil.images.get("over");
        images[2] = ImageUtil.images.get("background01");
    }

    public GameOver(){}

    public GameOver(PlanWarClient pwc,int x,int y){
        this.pwc = pwc;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics g) {
        Font f = g.getFont();
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.setFont(new Font("宋体",Font.BOLD,150));
        g.drawImage(images[2],0,0,null);
        if (pwc.boss.getHP() <= 0){
            g.drawString("VICTORY",400,700);
            g.drawImage(images[0],450,100,null);
        }else{
            g.drawString("DEFEAT",450,700);
            g.drawImage(images[1],450,100,null);
        }
        g.setColor(c);
        g.setFont(f);
    }
}
