package com.company.View;


import com.company.io.LevelReader;
import com.company.io.Saver;
import com.company.io.TableReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Level1State extends LevelState implements Runnable {

    Image imgFinish=new ImageIcon("res/finish.png").getImage();
    Thread rivalFactory=new Thread(this);
    private boolean running;
    int finalSpeed;
    @Override
    public void init() {
        road=new Road(LevelReader.getBackground(LevelReader.getCurrentLevel()-1), LevelReader.getBgWidth(LevelReader.getCurrentLevel()-1),true);
        p=new PlayerView();
        rivals=new ArrayList<>();
        running=true;
    }

    @Override
    public void update() {
            p.update();
            road.update();
        for(int ind=0; ind<rivals.size();ind++){
            rivals.get(ind).update();
        }
            //testCollisionWithRival();
            testWin();
    }
    public Level1State(GameStateManager gsm){
            this.gsm=gsm;
            init();
            rivalFactory.start();
    }
    private int coorFinish=800;
    public void paint(Graphics2D g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0, GamePanel.WIDTH,GamePanel.HEIGHT);
        road.setVector(-p.getSpeed(),0);
        road.paint(g);
        Font font = new Font("Arial", Font.ITALIC, 40);
        g.setFont(font);
        g.drawString("Уровень "+LevelReader.getCurrentLevel(), 500,50);
        if(p.getS()>LevelReader.getCoorFinish(LevelReader.getCurrentLevel()-1)){
            g.drawImage(imgFinish,coorFinish,100, 200,300, null );
            coorFinish-=p.getSpeed();
            if(p.getX() > coorFinish+300){
                finish=true;
            }
        }
        else {
            for(int ind=0; ind<rivals.size();ind++){
                rivals.get(ind).paint(g);
            }
        }
        p.paint(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        p.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        p.keyReleased(e);
    }

    @Override
    public void run() {
        while(running) {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(2000));
                int currentLevel=LevelReader.getCurrentLevel();
                if(currentLevel!=0) {
                    if(p.getS()>=LevelReader.getCoorFinish(LevelReader.getCurrentLevel()-1)&&rivals.size()!=0)
                        rivals.clear();
                    else if (rivals.size() < LevelReader.getNumberOfRivals(currentLevel - 1)) {
                        rivals.add(new RivalView(1200, 260 + rand.nextInt(160), rand.nextInt(30), this));

                    }
                    else if (rivals.size() == LevelReader.getNumberOfRivals(currentLevel - 1) && (rivals.get(0).getX() >= 1200 || rivals.get(0).getX() <= -1200)) {
                        rivals.remove(0);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    private boolean finish=false;
    private void testWin() {
        if(finish==true){
            Saver.SavePlayer(gsm.getName(),(Math.round(System.nanoTime()-gsm.getStart())/100000000 * 1.0) / 100.0);
            finalSpeed=p.getSpeed()/2;
            if(LevelReader.getCurrentLevel()!=LevelReader.getSize()){
                int result=JOptionPane.showConfirmDialog(null,"Вы выиграли \n Перейти на следующий уровень?");
                if(result==JOptionPane.YES_OPTION) {
                    if(LevelReader.getBoss(LevelReader.getCurrentLevel())) {
                        LevelReader.setCurrentLevel(LevelReader.getCurrentLevel()+1);
                        gsm.gameStates.add(new Level2State(gsm));
                    }
                    else{
                        LevelReader.setCurrentLevel(LevelReader.getCurrentLevel()+1);
                        gsm.gameStates.add(new Level1State(gsm));
                    }
                    gsm.setState(gsm.getCurrentState());
                }
                else if(result==JOptionPane.NO_OPTION)
                    gsm.setState(GameStateManager.MENUSTATE);
                else if(result==JOptionPane.CANCEL_OPTION)
                    System.exit(0);

            }
            else {
                JOptionPane.showMessageDialog(null,"Вы выиграли");
                try
                {
                    String[][] data = TableReader.TableReader("file/rating.txt");
                    FileWriter file = new FileWriter("file/rating.txt");
                    file.write("");
                    file.close();
                    double time=(System.nanoTime()-gsm.getStart())/1000000000;
                    double roundOff = (Math.round(time * 1.0) / 100.0)+gsm.getTime();
                    for(int i=0; i<10;i++) {
                        if (Double.valueOf(data[i][1]) > roundOff) {
                            for(int j=i;j<9;j++) {
                                String tmpStr = data[j+1][0];
                                String tmpTime = data[j+1][1];
                                String tmpSpeed = data[j+1][2];
                                data[j+1][0] = data[i][0];
                                data[j+1][1] = data[i][1];
                                data[j+1][2] = data[i][2];
                                data[i][0] = tmpStr;
                                data[i][1] = tmpTime;
                                data[i][2] = tmpSpeed;
                            }
                            data[i][0]=gsm.getName();
                            data[i][1]=Double.toString(roundOff);
                            data[i][2]=Integer.toString(finalSpeed);
                            break;
                        }
                    }
                    FileWriter fw = new FileWriter("file/rating.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw);
                    for(int i=0;i<10;i++) {
                        out.print(data[i][0] + " ");
                        out.print(data[i][1] + " ");
                        out.println(data[i][2]);
                    }
                    out.close();
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
                gsm.setState(GameStateManager.MENUSTATE);
            }

        }
    }

    private void testCollisionWithRival() {
        for (int ind=0;ind<rivals.size();ind++)
            if(rivals.get(ind).testCollisionWithPlayer(p.getRect())){
                JOptionPane.showMessageDialog(null,"Вы проиграли");
                gsm.setState(GameStateManager.MENUSTATE);
            }
    }

}
