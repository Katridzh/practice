package com.company.View;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameStateManager {

    ArrayList<GameState> gameStates;
    private int currentState;
    private String name;
    private double time;
    public static final int MENUSTATE=0;

    public GameStateManager(){
        gameStates=new ArrayList<>();
        currentState=MENUSTATE;
        gameStates.add(new MenuState(this));
    }

    public int getCurrentState(){
        return currentState;
    }
    private long start;
    public void setState(int state){
        if(currentState==MENUSTATE) {
            currentState=state;
            gameStates.get(currentState).init();
            start = System.nanoTime();
        }
        else{
            int tmp=currentState;
            currentState=state;
            gameStates.remove(tmp);
            gameStates.get(currentState).init();
        }
    }
    public long getStart(){
        return start;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void update(){
        gameStates.get(currentState).update();
    }
    public void paint(Graphics2D g){
        gameStates.get(currentState).paint(g);
    }
    public void keyPressed(KeyEvent e){
        gameStates.get(currentState).keyPressed(e);
    }
    public void keyReleased(KeyEvent e){
        gameStates.get(currentState).keyReleased(e);
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
