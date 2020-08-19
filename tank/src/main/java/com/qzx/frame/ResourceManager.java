package com.qzx.frame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Auther: qzx
 * @Date: 2020/8/10 - 08 - 10 - 21:38
 * @Description: 资源管理类, 比如保存坦克和子弹的图片等资源, 该类加载时将图片加载到内存,
 * 使用单例设计模式保证ResourceManager对象只能通过类名.属性获得对象，并且只有一个，同时使用
 * 内部类实现懒加载，在需要对象的时候才会实例化对象
 * @version: 1.0
 */
public class ResourceManager {
    static BufferedImage MyTankL, MyTankR, MyTankU, MyTankD;//我方坦克上下左右四个朝向的图片
    static BufferedImage tankL, tankR, tankU, tankD;//坦克上下左右四个朝向的图片
    static BufferedImage bulletL, bulletR, bulletU, bulletD;//子弹上下左右四个朝向的图片
    static BufferedImage explodes[];//坦克爆炸时的效果图

    private ResourceManager() {
    }

    private static class Resource {
        static BufferedImage MyTankL, MyTankR, MyTankU, MyTankD;//我方坦克上下左右四个朝向的图片
        static BufferedImage tankL, tankR, tankU, tankD;//坦克上下左右四个朝向的图片
        static BufferedImage bulletL, bulletR, bulletU, bulletD;//子弹上下左右四个朝向的图片
        static BufferedImage explodes[] = new BufferedImage[16];//坦克爆炸时的效果图

        static {
            try {
                //我方坦克
                MyTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
                MyTankR = ImageUtils.rotateImage(MyTankU, 90);
                MyTankL = ImageUtils.rotateImage(MyTankU, -90);
                MyTankD = ImageUtils.rotateImage(MyTankU, 180);
                //四个方向的坦克图片
                tankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
                tankR = ImageUtils.rotateImage(tankU, 90);
                tankL = ImageUtils.rotateImage(tankU, -90);
                tankD = ImageUtils.rotateImage(tankU, 180);
                //四个方向的子弹图片
                bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
                bulletL = ImageUtils.rotateImage(bulletU, 90);
                bulletR = ImageUtils.rotateImage(bulletU, -90);
                bulletD = ImageUtils.rotateImage(bulletU, 180);
                //加载坦克爆炸的效果图片
                for (int i = 0; i < 16; i++) {
                    explodes[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static BufferedImage getMyTankL() {
        return Resource.MyTankL;
    }

    public static BufferedImage getMyTankR() {
        return Resource.MyTankR;
    }

    public static BufferedImage getMyTankU() {
        return Resource.MyTankU;
    }

    public static BufferedImage getMyTankD() {
        return Resource.MyTankD;
    }

    public static BufferedImage getTankL() {
        return Resource.tankL;
    }

    public static BufferedImage getTankR() {
        return Resource.tankR;
    }

    public static BufferedImage getTankU() {
        return Resource.tankU;
    }

    public static BufferedImage getTankD() {
        return Resource.tankD;
    }

    public static BufferedImage getBulletL() {
        return Resource.bulletL;
    }

    public static BufferedImage getBulletR() {
        return Resource.bulletR;
    }

    public static BufferedImage getBulletU() {
        return Resource.bulletU;
    }

    public static BufferedImage getBulletD() {
        return Resource.bulletD;
    }

    public static BufferedImage[] getExplodes() {
        return Resource.explodes;
    }
}
