package com.qzx.frame;

import java.awt.*;

/**
 * @Auther: zhaoss
 * @Date: 2020/8/11 - 08 - 11 - 10:14
 * @Description: 保存爆炸效果图
 * @version: 1.0
 */
public class Explode {
    private int x,y;//初始位置
    private TankFrame tf;
    static final int WIDTH = ResourceManager.explodes[0].getWidth();//爆炸图片宽度
    static final int HEIGHT = ResourceManager.explodes[0].getHeight();//爆炸图片高度
    private int step = 0;
    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if (step>=ResourceManager.explodes.length){
            return;
        }
        g.drawImage(ResourceManager.explodes[step++],x,y,null);
    }
}
