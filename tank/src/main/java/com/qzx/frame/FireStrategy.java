package com.qzx.frame;

/**
 * @Auther: qzx
 * @Date: 2020/8/15 - 08 - 15 - 15:42
 * @Description: com.qzx.frame
 * 开火策略
 * @version: 1.0
 */
public interface FireStrategy {
    /**
     * 参数tank方便知道发射的子弹初始在哪个位置上
     *
     * @param tank
     */
    void fire(Tank tank);
}
