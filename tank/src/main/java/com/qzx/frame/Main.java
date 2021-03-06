package com.qzx.frame;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: qzx
 * @Date: 2020/8/5 - 08 - 05 - 7:22
 * @Description: com.qzx.frame
 * @version: 1.0
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int initTankCount = Integer.parseInt((String)PropertyManager.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.enemies.add(new Tank(50+i*70,150,Dir.DOWN,tankFrame,Group.ENEMY));
        }
        while(true) {
            TimeUnit.MILLISECONDS.sleep(50);
            tankFrame.repaint();
        }
    }
}
