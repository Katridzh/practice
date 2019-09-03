package com.company.Model;

import com.company.View.Shoot;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Boss {
    public static final int MOVE=0;
    public static final int ATTACK=1;
    public ArrayList<Shoot> shoots;
    private long startTime;
    private long delay;
    private int lifeForce=10;
    private int currentAction;
    public Boss(){
        startTime = System.nanoTime();
        currentAction=MOVE;
        shoots=new ArrayList<>();
    }
   public void update(){
        createShoot();
        if (shoots.size() != 0) {
            shoots.get(0).update();
        }
    }

    public void createShoot() {
        Random rand=new Random();
        if (shoots.size() > 0) {
            if (shoots.get(0).getX() <= 0)
                shoots.remove(0);
            delay = rand.nextInt(100);
        } else if (shoots.size() == 0) {
            if (delay == -1) return;
            long elapsed = (System.nanoTime() - startTime) / 1000000;
            if (elapsed > delay) {
                shoots.add(new Shoot("res/dust.png", 600, 300, 40, 300, 275,1.5));
                startTime = System.nanoTime();
            }
        }
    }
    public boolean checkAttack(Rectangle plRect){
        if(shoots.size()!=0) {
            if (shoots.get(0).getRect().intersects(plRect)){
                shoots.remove(0);
                return true;
            }
        }
        return false;
    }
    public void setLifeForce(int i){
        lifeForce=i;
    }
    public int getLifeForce(){
        return lifeForce;
    }

    public int getShootsSize(){
        return shoots.size();
    }
    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    public int getCurrentAction() {
        return currentAction;
    }
}
