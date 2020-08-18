package com.qzx.frame;

import java.awt.*;

/**
 * @Auther: qzx
 * @Date: 2020/8/18 - 08 - 18 - 14:37
 * @Description: com.qzx.frame
 * 所有物体的抽象父类
 * @version: 1.0
 */
public abstract class GameObject {
    int x,y;//物体的位置
    boolean isAlive;//物体是否还需要显示在屏幕上
    // 每一个物体的paint方法,实现自己的绘画功能
    public abstract void paint(Graphics g);
}
