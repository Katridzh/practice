package com.company.Model;

import com.company.View.Shoot;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    public static final int MAX_V=100;
    public static final int MAX_TOP=300;
    public static final int MAX_BOTOM=500;
    private int speed=0;
    private int dv=0;
    private int s=0;
    private int x=80;
    private int y=350;
    private int dy=0;
    private ArrayList<Shoot> shoots;

    public Player(){
        shoots=new ArrayList<>();
    }

    public void update(){
        speed+=dv;
        s+=speed;
        if(speed<=0) speed=0;
        if(speed>=MAX_V) speed=MAX_V;
        y-=dy;
        if(y<=MAX_TOP) y=MAX_TOP;
        if(y>=MAX_BOTOM) y=MAX_BOTOM;
        for(int i=0; i<shoots.size();i++){
            shoots.get(i).update();
            if(shoots.get(i).getX()>=1200)
                shoots.remove(i);
        }
    }

    public void createShoot(){
        shoots.add(new Shoot("res/voice.png", x+142, y-80, -60, 300, 275,1.5));
    }
    public boolean checkAttack(int ind, Rectangle bossRect){
        if(shoots.get(ind).getRect().intersects(bossRect)){
            shoots.remove(ind);
            return true;
        }
       return false;
    }
    public int getShootsSize(){
        return shoots.size();
    }
    public int getS(){
        return s;
    }
    public int getX() {
        return x;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int sp) {
        speed=sp;
    }
    public int getY() {
        return y;
    }
    public Shoot getShoot(int i){
        return shoots.get(i);
    }
    public void setDv(int dv) {
        this.dv = dv;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
}
