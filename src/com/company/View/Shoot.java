package com.company.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

public class Shoot {
    private int x;
    private int y;
    private int speed;
    private int width;
    private int height;
    private double scale;
    private BufferedImage shootImage;
    public Shoot(String str, int x, int y, int speed, int width, int height, double scale){
        try {
            shootImage = ImageIO.read(new FileInputStream(str));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        this.x=x;
        this.y=y;
        this.speed=speed;
        this.scale=scale;
        this.width=(int)(width/scale);
        this.height=(int)(height/scale);
    }
    public void update(){
        x-=speed;
    }
    public void paint(Graphics g){
        g.drawImage(shootImage, x, y, width, height, null);
    }
    public Rectangle getRect(){
        return new Rectangle((int)(x+(110/scale)),(int)(y+(86/scale)),(int)(70/scale),(int)(67/scale));
    }
    public int getX() {
        return x;
    }
}
