package com.qzx.chainOfRes;

import com.qzx.frame.Bullet;
import com.qzx.frame.Explode;
import com.qzx.frame.GameObject;
import com.qzx.frame.Tank;

import java.awt.*;

/**
 * @Auther: qzx
 * @Date: 2020/8/18 - 08 - 18 - 16:49
 * @Description: com.qzx.chainOfRes
 * 子弹与坦克碰撞器
 * @version: 1.0
 */
public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            // o1为子弹，o2为坦克
            Bullet bullet = (Bullet)o1;
            Tank tank = (Tank)o2;
            Rectangle recBullet = bullet.getRecBullet();
            Rectangle recTank = tank.getRecTank();
            if (recBullet.intersects(recTank)){
                if (bullet.getGroup() == tank.getGroup()) return true;//如果是我方发射的子弹不做碰撞检测,也就是不开启队友伤害
                //如果2个矩形相交就说明发生碰撞
                bullet.isAlive = false;
                tank.isAlive = false;
                int x = tank.getX() + Tank.TANK_WIDTH/2 - Explode.WIDTH/2;//爆炸的位置x为坦克的中心位置x
                int y = tank.getY() + Tank.TANK_HEIGHT/2 - Explode.HEIGHT/2;//爆炸的位置y为坦克的中心位置y
                bullet.getGm().add(new Explode(x,y));
                return false;//发生了碰撞,子弹和坦克会消失，所以不要进行后续的碰撞检测
            }
            return true;
        }else if (o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2,o1);
        }else {
            //说明传递来的不符合碰撞的类型，进行下一轮碰撞检测
            return true;
        }
    }
}
