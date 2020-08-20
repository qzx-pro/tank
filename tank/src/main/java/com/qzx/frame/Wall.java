package com.qzx.frame;

import com.qzx.chainOfRes.Collider;

import java.awt.*;

/**
 * @Auther: qzx
 * @Date: 2020/8/19 - 08 - 19 - 9:30
 * @Description: com.qzx.frame
 * @version: 1.0
 */
public class Wall extends GameObject {
    public Rectangle rectWall;//墙在屏幕上的图形
    private GameModel gm = GameModel.getInstance();

    public Rectangle getRectWall() {
        return rectWall;
    }

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rectWall = new Rectangle(x, y, width, height);
        gm.add(this);//创造的时候被添加到GameModel的集合中
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }

    public static void main(String[] args) {
        System.out.println(GameModel.getInstance());
    }
}
