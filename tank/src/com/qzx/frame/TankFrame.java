package com.qzx.frame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Auther: qzx
 * @Date: 2020/8/5 - 08 - 05 - 7:20
 * @Description: com.qzx.frame
 * @version: 1.0
 */
public class TankFrame extends Frame{
    private static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;
    Tank tank = new Tank(200,200,Dir.DOWN);
    Bullet bullet = new Bullet(250,250,Dir.DOWN);

    public TankFrame() {
        this.setLocation(800,400);//设定初始Frame的位置
        this.setSize(GAME_WIDTH, GAME_HEIGHT);//初始大小
        this.setResizable(false);//设置大小不可变
        this.setVisible(true);//设置可见
        this.setTitle("tank war");//设置标题
        //设置关闭窗口响应事件
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //响应键盘事件
        this.addKeyListener(new MyKey());
    }
    //利用双缓冲解决屏幕闪烁问题
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        tank.paint(g);
        bullet.paint(g);
    }

    class MyKey extends KeyAdapter {
        boolean bL = false;//向左,按下为true,释放为false
        boolean bR = false;//向右,按下为true,释放为false
        boolean bU = false;//向上,按下为true,释放为false
        boolean bD = false;//向下,按下为true,释放为false

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            //根据标记改变坦克的方向
            setTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            //根据标记改变坦克的方向
            setTankDir();
        }
        //设置坦克的方向
        private void setTankDir() {
            if (!bL&!bR&!bU&!bD){
                tank.setMoving(false);//按键松开就停止
            }else {
                tank.setMoving(true);//按下表示开始移动
                if (bL) {
                    tank.setDir(Dir.LEFT);
                }
                if (bR) {
                    tank.setDir(Dir.RIGHT);
                }
                if (bU) {
                    tank.setDir(Dir.UP);
                }
                if (bD) {
                    tank.setDir(Dir.DOWN);
                }
            }
        }
    }
}
