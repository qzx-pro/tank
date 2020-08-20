package com.qzx.decorator;

import com.qzx.frame.GameModel;
import com.qzx.frame.GameObject;

import java.awt.*;

/**
 * @Auther: qzx
 * @Date: 2020/8/20 - 08 - 20 - 14:28
 * @Description: com.qzx.decorator
 * 在子弹上画一条线
 * @version: 1.0
 */
public class LineDecorator extends GODecorator{

    public LineDecorator(GameObject object) {
        super(object);
        GameModel.getInstance().add(this);//构建修饰器后添加到集合中，方便画出
        isAlive = true;
    }

    @Override
    public void paint(Graphics g) {
        if (!object.isAlive) {
            isAlive = false;
            GameModel.getInstance().remove(this);
            return;
        }
        // 由于这里的被修饰物体有可能会移动，所以得记录实时的位置
        this.x = object.x;
        this.y = object.y;
//        object.paint(g);//先画出被修饰物体
        //然后画出添加的部分
        Color c = g.getColor();
        g.setColor(Color.CYAN);
        g.drawLine(object.x,object.y,object.width+object.x,object.height+object.y);
        g.setColor(c);
    }
}
