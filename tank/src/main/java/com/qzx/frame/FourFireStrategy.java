package com.qzx.frame;

/**
 * @Auther: qzx
 * @Date: 2020/8/15 - 08 - 15 - 15:57
 * @Description: com.qzx.frame
 * 朝着4个方向发射子弹策略
 * @version: 1.0
 */
public class FourFireStrategy implements FireStrategy {
    @Override
    public void fire(Tank tank) {
        int x = tank.x + Tank.TANK_WIDTH/2-ResourceManager.getBulletU().getWidth()/2;//发射子弹的初始位置x
        int y = tank.y + Tank.TANK_HEIGHT/2-ResourceManager.getBulletU().getHeight()/2;//发射子弹的初始位置y
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(x,y,dir,tank.tf,tank.group);
        }
    }
}
