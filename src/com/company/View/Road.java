package com.company.View;

import javax.swing.*;
import java.awt.*;

public class Road {
    private Image image;
    private Image image1;
    private int x;
    private int x1;
    private int y;
    private int dx;
    private int dy;
    private int imagWidth;
    private boolean move;
    public Road(String str, int iw, boolean m){
        image = new ImageIcon(str).getImage();
        move = m;
        imagWidth = iw;
        if (move) {
            image1 = new ImageIcon(str).getImage();
            x1 = imagWidth;
        }
    }
    public void setVector(int dx, int dy){
        this.dx=dx;
        this.dy=dy;
    }
    public void update(){
        if(x1+dx<=0){
            x=0;
            x1=imagWidth;
        }else {
            x += dx;
            x1+=dx;
        }
    }
    public void paint(Graphics2D g){
        g.drawImage(image, x, y,imagWidth,600, null);
        if(move)
            g.drawImage(image1, x1, y,imagWidth,600, null);
    }
}
