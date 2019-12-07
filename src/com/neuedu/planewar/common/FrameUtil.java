package com.neuedu.planewar.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class FrameUtil {
    public static Image getImage(String imPath){
        URL u = FrameUtil.class.getClassLoader().getResource(imPath);
        BufferedImage img = null;
        try{
            img = ImageIO.read(u);
        }catch (Exception e){
            e.printStackTrace();
        }
        return img;
    }
}
