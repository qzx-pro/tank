package com.qzx.chainOfRes;

import com.qzx.frame.Bullet;
import com.qzx.frame.Dir;
import com.qzx.frame.GameObject;
import com.qzx.frame.Tank;

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
            Dir dir = ((Tank) o1).dir;
            Dir dir1 = ((Tank) o2).dir;
            Dir[] values = Dir.values();
            for (int i = 0; i < values.length; i++) {
                if (dir!=values[i]){
                    ((Tank) o1).setDir(values[i]);
                }
                if (dir1!=values[i]){
                    ((Tank) o2).setDir(values[i]);
                }
            }
        }
        return true;//坦克碰撞不会消失
    }
}
