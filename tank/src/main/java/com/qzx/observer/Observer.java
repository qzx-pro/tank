package com.qzx.observer;

import com.qzx.frame.GameObject;

/**
 * @Auther: qzx
 * @Date: 2020/8/21 - 08 - 21 - 13:23
 * @Description: com.qzx.observer
 * 观察者接口
 * @version: 1.0
 */
public interface Observer {
    void actionOnEvent(Event<GameObject> event);
}
