package com.qzx.frame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Auther: qzx
 * @Date: 2020/8/10 - 08 - 10 - 21:38
 * @Description: 资源管理类,比如保存坦克和子弹的图片等资源,该类加载时将图片加载到内存
 * @version: 1.0
 */
public class ResourceManager {
    static BufferedImage MyTankL,MyTankR,MyTankU,MyTankD;//我方坦克上下左右四个朝向的图片
    static BufferedImage tankL,tankR,tankU,tankD;//坦克上下左右四个朝向的图片
    static BufferedImage bulletL,bulletR,bulletU,bulletD;//子弹上下左右四个朝向的图片
    static BufferedImage explodes[] = new BufferedImage[16];//坦克爆炸时的效果图

    static {
        try {
            //我方坦克
            MyTankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            MyTankR = ImageUtils.rotateImage(MyTankU,90);
            MyTankL = ImageUtils.rotateImage(MyTankU,-90);
            MyTankD = ImageUtils.rotateImage(MyTankU,180);
            //四个方向的坦克图片
            tankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            tankR = ImageUtils.rotateImage(tankU,90);
            tankL = ImageUtils.rotateImage(tankU,-90);
            tankD = ImageUtils.rotateImage(tankU,180);
            //四个方向的子弹图片
            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtils.rotateImage(bulletU,90);
            bulletR = ImageUtils.rotateImage(bulletU,-90);
            bulletD = ImageUtils.rotateImage(bulletU,180);
            //加载坦克爆炸的效果图片
            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
