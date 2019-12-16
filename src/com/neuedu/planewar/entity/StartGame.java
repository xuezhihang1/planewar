package com.neuedu.planewar.entity;

import com.neuedu.planewar.client.PlanWarClient;
import com.neuedu.planewar.common.ImageUtil;

import java.awt.*;

public class StartGame extends PlaneWarObject{
    static Image[] images = new Image[2];
    static{
        images[0] = ImageUtil.images .get("start");
        images[1] = ImageUtil.images.get("background01");
    }

    public StartGame(){}

    public StartGame(PlanWarClient pwc,int x,int y){
        this.pwc = pwc;
        this.x = x;
        this.y = y;
    }


    @Override
    public void draw(Graphics g) {
        Font f = g.getFont();
        Color c = g.getColor();
        g.drawImage(images[1],0,0,null);
        g.drawImage(images[0],450,100,null);
        g.setColor(Color.GREEN);
        g.setFont(new Font("仿宋",Font.BOLD,30));
        g.drawString("请按P键开始游戏",580,700);
        g.setColor(c);
    }
}
