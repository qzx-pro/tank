package com.qzx.frame;

/**
 * @Auther: qzx
 * @Date: 2020/8/15 - 08 - 15 - 15:44
 * @Description: com.qzx.frame
 * 默认的开火策略，实现朝着坦克的方向发射一颗子弹
 * @version: 1.0
 */
public class DefaultStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int x = tank.x + Tank.TANK_WIDTH/2-ResourceManager.getBulletU().getWidth()/2;//发射子弹的初始位置x
        int y = tank.y + Tank.TANK_HEIGHT/2-ResourceManager.getBulletU().getHeight()/2;//发射子弹的初始位置y
        new Bullet(x,y,tank.dir,tank.gm,tank.group);
    }
}
