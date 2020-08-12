package com.qzx.frame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: qzx
 * @Date: 2020/8/5 - 08 - 05 - 7:20
 * @Description: com.qzx.frame
 * @version: 1.0
 */
public class TankFrame extends Frame{
    static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;
    Tank tank = new Tank(200,400,Dir.UP,this,Group.ALLY);//我方坦克
    List<Bullet> bullets = new ArrayList<>();//打出的子弹集合
    List<Tank> enemies = new ArrayList<>();//敌人坦克集合
    List<Explode> explodes = new ArrayList<>();//坦克爆炸集合

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
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量:"+bullets.size(),10,60);
        g.drawString("敌方坦克数量:"+enemies.size(),10,80);
        g.drawString("坦克爆炸数量:"+explodes.size(),10,100);
        g.setColor(c);
        //画出我方坦克
        tank.paint(g);
        //画出打出的子弹
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.paint(g);
            if (!bullet.isAlive){
                //子弹飞出边界，移除子弹
                bullets.remove(bullet);
            }
        }
        //画出敌方坦克
        for (int i = 0; i < enemies.size(); i++) {
            Tank enemy = enemies.get(i);
            enemy.paint(g);
            if (!enemy.isAlive){
                //坦克遭到攻击，移除坦克
                enemies.remove(enemy);
            }
        }
        //画出坦克爆炸效果
        for (int i = 0; i < explodes.size(); i++) {
            Explode explode = explodes.get(i);
            explode.paint(g);
            if (!explode.isAlive){
                explodes.remove(explode);
            }
        }
        //碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                //子弹和敌方坦克发生碰撞
                bullet.collideWith(enemies.get(j));
            }
        }
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
