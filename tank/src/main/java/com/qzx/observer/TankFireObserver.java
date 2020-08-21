package com.qzx.observer;

import com.qzx.frame.GameObject;
import com.qzx.frame.Tank;

/**
 * @Auther: qzx
 * @Date: 2020/8/21 - 08 - 21 - 13:27
 * @Description: com.qzx.observer
 * @version: 1.0
 */
public class TankFireObserver implements Observer {
    @Override
    public void actionOnEvent(Event<GameObject> event) {
        Tank tank = (Tank) event.getSource();
        tank.fire();
    }
}
