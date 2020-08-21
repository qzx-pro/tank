package com.qzx.observer;

import com.qzx.frame.GameObject;
import com.qzx.frame.Tank;

/**
 * @Auther: qzx
 * @Date: 2020/8/21 - 08 - 21 - 13:20
 * @Description: com.qzx.observer
 * 坦克开火事件
 * @version: 1.0
 */
public class TankFireEvent implements Event<GameObject> {
    // 持有事件源对象
    private GameObject object;

    public TankFireEvent(GameObject object) {
        this.object = object;
    }

    //获取事件源对象
    @Override
    public GameObject getSource() {
        return object;
    }
}
