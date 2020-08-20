package com.qzx.decorator;

import com.qzx.frame.GameObject;

import java.awt.*;

/**
 * @Auther: qzx
 * @Date: 2020/8/20 - 08 - 20 - 14:20
 * @Description: com.qzx.decorator
 * @version: 1.0
 */
public abstract class GODecorator extends GameObject {
    // 聚合被修饰物体的对象,这里是抽象对象
    protected GameObject object;

    protected GODecorator(GameObject object) {
        this.object = object;
    }

    @Override
    public abstract void paint(Graphics g);//具体的画法交给装饰器自己实现
}
