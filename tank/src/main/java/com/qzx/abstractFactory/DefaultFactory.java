package com.qzx.abstractFactory;

import com.qzx.frame.*;

/**
 * @Auther: qzx
 * @Date: 2020/8/16 - 08 - 16 - 10:19
 * @Description: com.qzx.abstractFactory
 * @version: 1.0
 */
public class DefaultFactory extends BaseFactory {
    private static final DefaultFactory FACTORY = new DefaultFactory();
    private DefaultFactory(){}

    public static DefaultFactory getFactory(){
        //由于该项目是单机版本的就不存在线程安全问题
        return FACTORY;
    }
    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group, int TANK_WIDTH, int TANK_HEIGHT) {
        return new Tank(x,y,dir,tf,group,TANK_WIDTH,TANK_HEIGHT);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new Bullet(x,y,dir,tf,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x,y,tf);
    }
}
