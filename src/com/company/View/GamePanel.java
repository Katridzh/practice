package com.company.View;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


public class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH=1200;
    public static final int HEIGHT=600;

    private Thread thread;
    private boolean running;

    private BufferedImage image;
    private Graphics2D g;

    private GameStateManager gsm;

    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if(thread==null){
            thread=new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }
    public void init(){
        image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        g=(Graphics2D) image.getGraphics();
        running=true;
        gsm= new GameStateManager();
    }
    public void run(){
        init();
        while(running) {

            update();
            paint();
            drawToScreen();
                try {
                   if(gsm.getCurrentState()==0)
                        Thread.sleep(5);
                    else
                        Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
    private void update(){
        gsm.update();
    }
    private void paint(){
        gsm.paint(g);
    }
    private void drawToScreen(){
        Graphics g2=getGraphics();
        g2.drawImage(image,0,0,WIDTH,HEIGHT,null);
        g2.dispose();
    }
    public void keyTyped(KeyEvent e){

    }
    public void keyPressed(KeyEvent e){
        gsm.keyPressed(e);
    }
    public void keyReleased(KeyEvent e){
        gsm.keyReleased(e);
    }

}
