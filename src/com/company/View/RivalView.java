package com.company.View;

import com.company.Model.Rival;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class RivalView {
    private Rival r;
    private BufferedImage[] sprites;
    private Animation animation;
    private int width=320;
    private int height=233;
    LevelState l;

    public RivalView(int x, int y, int v, LevelState l){
        this.l=l;
        r=new Rival(x,y,v);
        try {

            BufferedImage spritesheet = ImageIO.read(new FileInputStream("res/rival.png"));

            sprites = new BufferedImage[7];

            for(int j = 0; j < 7; j++) {
                sprites[j]=spritesheet.getSubimage(j * width, 0, width,height);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(100-v);
    }

    public void update(){
        r.setX(r.getX()-l.p.getSpeed()+r.getSpeed());
        animation.update();
    }
    public boolean testCollisionWithPlayer(Rectangle pl_rect){
        return r.testCollisionWithPlayer(pl_rect);
    }
    public void paint(Graphics2D g){
        g.drawImage(animation.getImage(),r.getX(),r.getY(),null);
    }

    public int getX() {
        return r.getX();
    }
}
