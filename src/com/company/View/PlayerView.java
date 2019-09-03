package com.company.View;

import com.company.Model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;

public class PlayerView {
    public static final int STAND=0;
    public static final int MOVE=1;
    public static final int ATTACK=2;
    private int currentAction;
    private ArrayList<BufferedImage[]> sprites;
    private Animation animation;
    private int width=575;
    private int height=274;
    private int[] numFrames={1,10,2};
    private Player p;
    //public ArrayList<Shoot> shoots;

    public PlayerView(){
        p=new Player();
        try {
            BufferedImage spritesheet = ImageIO.read(new FileInputStream("res/dog1.png"));
            sprites = new ArrayList<>();
            for(int i = 0; i < 3; i++) {
                BufferedImage[] bi = new BufferedImage[numFrames[i]];
                for(int j = 0; j < bi.length; j++) {
                    bi[j] = spritesheet.getSubimage(j * width,i * height,width,height);
                }
                sprites.add(bi);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        animation = new Animation();
        currentAction=STAND;
        animation.setFrames(sprites.get(STAND));
        animation.setDelay(100);
    }
    public Rectangle getRect(){
        return new Rectangle( p.getX()+45,p.getY()+5,130,48);
    }
    public void paint(Graphics2D g){
        g.drawImage(animation.getImage(), p.getX(), p.getY(), 200, 100, null);

        for(int i=0; i<p.getShootsSize();i++) {
            p.getShoot(i).paint(g);
        }
        double speed = (50.0 / Player.MAX_V) * p.getSpeed();
        g.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.ITALIC, 30);
        g.setFont(font);
        g.drawString("Скорость: " + speed + " км/ч", 90, 50);
    }
    boolean attack;
    public void update(){
        p.update();
        if(p.getSpeed()==0) {
            if (!attack) {
                currentAction=STAND;
                animation.setFrames(sprites.get(STAND));
            } else if(currentAction!=ATTACK){
                currentAction=ATTACK;
                animation.setFrames(sprites.get(ATTACK));
            }
        }
        else if(currentAction!=MOVE) {
            currentAction=MOVE;
            animation.setFrames(sprites.get(MOVE));
        }
        animation.setDelay(100-p.getSpeed());
        animation.update();
    }
    public boolean checkAttack(int ind, Rectangle bossRect){
        return p.checkAttack(ind,bossRect);
    }
    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode();
        if(key==KeyEvent.VK_RIGHT){
            p.setDv(1);
        }
        if(key==KeyEvent.VK_LEFT){
            p.setDv(-1);
        }
        if(key==KeyEvent.VK_UP){
            p.setDy(10);
        }
        if(key==KeyEvent.VK_DOWN){
            p.setDy(-10);
        }
        if(key==32){
            attack=true;
            p.createShoot();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();
        if(key==KeyEvent.VK_RIGHT||key==KeyEvent.VK_LEFT) {
            p.setDv(0);
        }
        if(key==KeyEvent.VK_UP||key==KeyEvent.VK_DOWN) {
            p.setDy(0);
        }
        if(key==32){
            attack=false;
        }
    }
    public int getS(){
        return p.getS();
    }

    public int getX() {
        return p.getX();
    }

    public int getSpeed() {
        return p.getSpeed();
    }
    public void setSpeed(int sp) {
        p.setSpeed(sp);
    }
    public int getShootsSize(){
        return p.getShootsSize();
    }

}
