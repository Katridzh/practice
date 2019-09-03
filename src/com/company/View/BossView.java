package com.company.View;

import com.company.Model.Boss;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;

public class BossView {
    private LevelState l;
    public Boss b;
    private ArrayList<BufferedImage[]> sprites;
    private Animation animation;
    private BufferedImage bomb;
    private int width=148;
    private int height=82;
    public BossView(LevelState l){
        this.l=l;
        b=new Boss();
        try {
            BufferedImage spritesheet = ImageIO.read(new FileInputStream("res/boss.png"));
            bomb=ImageIO.read(new FileInputStream("res/bomb.png"));
            sprites = new ArrayList<>();
            for(int i = 0; i < 2; i++) {
                BufferedImage[] bi = new BufferedImage[6];
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
        b.setCurrentAction(b.MOVE);
        animation.setFrames(sprites.get(b.MOVE));
        animation.setDelay(300);
    }
    public int getLifeForce(){
        return b.getLifeForce();
    }
    public void setLifeForce(int i){
        b.setLifeForce(i);
    }
    public Rectangle getRect(){
        return new Rectangle(950, 300, (int)(width/1.5), height+20);
    }
    public boolean checkAttack(Rectangle plRect){
        return b.checkAttack(plRect);
    }
    public void update(){
        b.update();
        if(b.getCurrentAction()==b.ATTACK ){
            b.setCurrentAction(b.MOVE);
            animation.setFrames(sprites.get(b.MOVE));
        }
        animation.update();
    }

    public void paint(Graphics2D g){
        if(b.getLifeForce()>0){
            g.drawImage(animation.getImage(), 800, 290, (int)(2.5*width), (int)(2.5*height), null);
            g.setColor(Color.WHITE);
            Font font = new Font("Arial", Font.ITALIC, 20);
            g.setFont(font);
            g.drawString("HP: " + b.getLifeForce() + " /10", 950, 280);
            if(b.shoots.size()!=0) {
                b.shoots.get(0).paint(g);
            }
        }
        else
            g.drawImage(bomb,800, 290, (int)(2.5*width), (int)(2.5*height),null);
    }

    public int getShootsSize(){
        return b.shoots.size();
    }
    public Shoot getShoot(int ind){
        return b.shoots.get(ind);
    }
    public void removeShoot(int ind){
        b.shoots.remove(ind);
    }
}
