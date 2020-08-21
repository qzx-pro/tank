package com.qzx.observer;

import com.qzx.frame.GameObject;
import com.qzx.frame.Tank;

/**
 * @Auther: qzx
 * @Date: 2020/8/21 - 08 - 21 - 13:25
 * @Description: com.qzx.observer
 * 事件类接口
 * @version: 1.0
 */
public interface Event<T> {
    //获取事件源对象
    T getSource() ;
}
