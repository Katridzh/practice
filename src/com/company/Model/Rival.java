package com.company.Model;


import java.awt.*;

public class Rival {
    public int x;
    private int y;
    private int speed;

    public Rival(int x, int y, int v){
        this.x=x;
        this.y=y;
        speed=v;
    }
    public Rectangle getRect(){
        return new Rectangle(x+90,y+80,128,45);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getSpeed() {
        return speed;
    }

    public int getY() {
        return y;
    }

    public boolean testCollisionWithPlayer(Rectangle pl_rect) {
            if(pl_rect.intersects(getRect())){
                return true;
            }
            return false;
    }
}
