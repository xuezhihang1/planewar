package com.neuedu.planewar.common;

import com.neuedu.planewar.constant.Constant;

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
        //我方飞机的图片
        for (int i=0;i<3;i++){
            images.put("myplane0"+(i+1),FrameUtil.getImage(Constant.IMG_PATH_PRE+"myplane/0"+(i+1)+".png"));
        }
    }
}
