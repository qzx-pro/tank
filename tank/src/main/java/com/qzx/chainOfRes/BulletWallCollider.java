package com.qzx.chainOfRes;

import com.qzx.frame.*;

import java.awt.*;

/**
 * @Auther: qzx
 * @Date: 2020/8/18 - 08 - 18 - 16:49
 * @Description: com.qzx.chainOfRes
 * 子弹与坦克碰撞器
 * @version: 1.0
 */
public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall){
            // o1为子弹，o2为坦克
            Bullet bullet = (Bullet)o1;
            Wall wall = (Wall)o2;
            Rectangle recBullet = bullet.getRecBullet();
            Rectangle recWall = wall.getRectWall();
            if (recBullet.intersects(recWall)){
                //如果2个矩形相交就说明发生碰撞
                bullet.isAlive = false;
                int x = bullet.getX() + Bullet.BULLET_WIDTH/2 - Explode.WIDTH/2;//爆炸的位置x为坦克的中心位置x
                int y = bullet.getY() + Bullet.BULLET_WIDTH/2 - Explode.HEIGHT/2;//爆炸的位置y为坦克的中心位置y
                bullet.getGm().add(new Explode(x,y,bullet.getGm()));
                return true;//发生了碰撞,子弹会消失，但是墙壁不会，所以接着往下进行碰撞检测
            }
            return true;
        }else if (o1 instanceof Wall && o2 instanceof Bullet){
            return collide(o2,o1);
        }else {
            //说明传递来的不符合碰撞的类型，进行下一轮碰撞检测
            return true;
        }
    }
}
