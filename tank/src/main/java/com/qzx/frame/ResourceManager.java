package com.qzx.frame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Auther: qzx
 * @Date: 2020/8/10 - 08 - 10 - 21:38
 * @Description: 资源管理类,保存坦克和子弹的图片,该类加载时将图片加载到内存
 * @version: 1.0
 */
public class ResourceManager {
    static BufferedImage tankL,tankR,tankU,tankD;//坦克上下左右四个朝向的图片
    static BufferedImage bulletL,bulletR,bulletU,bulletD;//子弹上下左右四个朝向的图片

    static {
        try {
            //四个方向的坦克图片
            tankL = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankR = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankD = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            //四个方向的子弹图片
            bulletL = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletR = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            bulletD = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
