package com.qzx.abstractFactory;

import com.qzx.frame.Dir;
import com.qzx.frame.Group;
import com.qzx.frame.TankModel;

/**
 * @Auther: qzx
 * @Date: 2020/8/16 - 08 - 16 - 10:13
 * @Description: com.qzx.abstractFactory
 * 抽象工厂，负责定义创造的产品种类
 * @version: 1.0
 */
public abstract class BaseFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, TankModel tm, Group group, int TANK_WIDTH , int TANK_HEIGHT);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, TankModel tm, Group group);
    public abstract BaseExplode createExplode(int x, int y, TankModel tm);
}
