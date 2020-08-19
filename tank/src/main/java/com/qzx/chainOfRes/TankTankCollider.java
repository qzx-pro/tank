package com.qzx.chainOfRes;

import com.qzx.frame.Bullet;
import com.qzx.frame.Dir;
import com.qzx.frame.GameObject;
import com.qzx.frame.Tank;

import java.awt.*;
import java.util.Random;

/**
 * @Auther: qzx
 * @Date: 2020/8/18 - 08 - 18 - 17:23
 * @Description: com.qzx.chainOfRes
 * 坦克之间发生碰撞
 * @version: 1.0
 */
public class TankTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            Tank tank = (Tank) o1;
            Tank tank1 = (Tank) o2;
            Rectangle recTank = tank.getRecTank();
            Rectangle recTank1 = tank1.getRecTank();
            Dir[] values = Dir.values();
            Random random = new Random();
            if (recTank.intersects(recTank1)){
                //2坦克相撞回到之前的位置,并且随机改变方向。
                tank.setX(tank.oldX);
                tank.setY(tank.oldY);
                tank.setDir(values[random.nextInt(4)]);
                tank1.setX(tank1.oldX);
                tank1.setY(tank1.oldY);
                tank1.setDir(values[random.nextInt(4)]);
            }
        }
        return true;//坦克碰撞不会消失
    }
}
