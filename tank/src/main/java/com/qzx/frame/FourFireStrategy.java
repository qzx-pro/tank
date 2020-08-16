package com.qzx.frame;

import com.qzx.abstractFactory.BaseFactory;
import com.qzx.abstractFactory.DefaultFactory;

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
        //为了方便在其他类使用工厂，并且符合开闭原则，将工厂做成单例
        BaseFactory factory = DefaultFactory.getFactory();//生成坦克、子弹和爆炸效果的工厂
        for (Dir dir : dirs) {
            //使用工厂产生子弹
            factory.createBullet(x,y,dir,tank.tf,tank.group);
        }
    }
}
