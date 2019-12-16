package com.neuedu.planewar.common;

import com.neuedu.planewar.constant.Constant;
import jdk.nashorn.internal.runtime.ConsString;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 专门加载图片的类
 */
public class ImageUtil {
    /**
     * 使用Map<KEY,VALUE>容器装图片
     */
    public static Map<String,Image> images = new HashMap<>();
    static{
        //开始游戏
        images.put("start",FrameUtil.getImage(Constant.IMG_PATH_PRE+"startgame/start.png"));
        //游戏胜利
        images.put("clear",FrameUtil.getImage(Constant.IMG_PATH_PRE+"gameover/clear.png"));
        //游戏失败
        images.put("over",FrameUtil.getImage(Constant.IMG_PATH_PRE+"gameover/over.png"));
        //我方飞机的图片
        for (int i=0;i<3;i++){
            images.put("myplane0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"myplane/0"+(i+1)+".png"));
        }
        //我方子弹的图片
        for (int i=0;i<3;i++){
            images.put("bullet0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"myplane/bullet/0"+(i+1)+".png"));
        }
        //敌方飞机的图片
        for (int i=0;i<3;i++){
            images.put("EnemyPlane_0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"enemyplane/EnemyPlane_0"+(i+1)+".png"));
        }
        for (int i = 0; i < 5; i++) {
            images.put("0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"explode/0"+(i+1)+".png"));
        }
        images.put("enemyplane_bullet01.png",FrameUtil.getImage(Constant.IMG_PATH_PRE+"enemyplane/bullet/enemyplane_bullet01.png"));
        //加血
        images.put("HP",FrameUtil.getImage(Constant.IMG_PATH_PRE+"item/HP.png"));
        //加防御
        images.put("DEF",FrameUtil.getImage(Constant.IMG_PATH_PRE+"item/DEF.png"));
        //Boss图片
        for (int i = 0;i < 2;i++){
            images.put("boss0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"boss/boss0"+(i+1)+".png"));
        }
        //Boss子弹图片
        for (int i = 0; i < 3; i++) {
            images.put("boss_bullet0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"boss/bullet/boss_bullet0"+(i+1)+".png"));
        }
        //背景图片
        images.put("background01",FrameUtil.getImage(Constant.IMG_PATH_PRE+"background/background01.png"));
    }
}
