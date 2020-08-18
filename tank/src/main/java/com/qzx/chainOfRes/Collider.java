package com.qzx.chainOfRes;

import com.qzx.frame.GameObject;

/**
 * @Auther: qzx
 * @Date: 2020/8/18 - 08 - 18 - 16:47
 * @Description: com.qzx.chainOfRes
 * 实现碰撞的接口
 * @version: 1.0
 */
public interface Collider {
    /**
     * 处理2个物体碰撞逻辑
     * @param o1
     * @param o2
     * @return true表示继续执行，false表示停止
     */
    boolean collide(GameObject o1,GameObject o2);
}
