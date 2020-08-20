package com.qzx.frame;

import java.awt.*;

/**
 * @Auther: qzx
 * @Date: 2020/8/11 - 08 - 11 - 10:14
 * @Description: 保存爆炸效果图
 * @version: 1.0
 */
public class Explode extends GameObject {
//    private int x, y;//初始位置
    private GameModel gm = GameModel.getInstance();
    public static final int WIDTH = ResourceManager.getExplodes()[0].getWidth();//爆炸图片宽度
    public static final int HEIGHT = ResourceManager.getExplodes()[0].getHeight();//爆炸图片高度
    private int step = 0;
//    boolean isAlive = true;//爆炸效果是否消失

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        width = WIDTH;
        height = HEIGHT;
        isAlive = true;
    }

    @Override
    public void paint(Graphics g) {
        if (step >= ResourceManager.getExplodes().length) {
            isAlive = false;//画完爆炸的所有帧图片就移除爆炸效果图
            gm.remove(this);//移除爆炸效果
            return;
        }
        g.drawImage(ResourceManager.getExplodes()[step++], x, y, null);
    }
}
