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
    public static final int GAME_WIDTH = Integer.parseInt((String)PropertyManager.get("GAME_WIDTH"));
    public static final int GAME_HEIGHT = Integer.parseInt((String)PropertyManager.get("GAME_HEIGHT"));

    static final int LOCATION_X = Integer.parseInt((String)PropertyManager.get("LOCATION_X"));
    static final int LOCATION_Y = Integer.parseInt((String)PropertyManager.get("LOCATION_Y"));

    TankModel tm = new TankModel(); // 该项目中的数据集合对象
    Tank tank = tm.tank;//我方坦克

    public TankFrame() {
        this.setLocation(LOCATION_X,LOCATION_Y);//设定初始Frame的位置
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
        tm.paint(g); // 调用model层的数据进行绘图
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
                case KeyEvent.VK_SPACE:
                    tank.fire();//按下空格键发射子弹
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
