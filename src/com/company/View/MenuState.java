package com.company.View;


import com.company.io.LevelReader;
import com.company.io.Loader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuState extends GameState {
    private Road road;
    private BufferedImage up;
    private BufferedImage down;
    private BufferedImage left;
    private BufferedImage right;
    private BufferedImage space;
    private int currentChoice=0;
    private boolean help;
    private String[] options={
            "Start", "Help", "Continue","Rating table","Quit"
    };
    private String[] helpStr={
            "Ускорение", "Торможение","Вверх","Вниз","Атака"
    };
    private Color titleColor;
    private Font titleFont;
    private Font textFont;
    private Font font;
    public MenuState(GameStateManager gsm){
        this.gsm=gsm;
        try{
            road=new Road("res/menu.gif", 1200, false);
            titleColor=new Color(255,255,255);
            titleFont=new Font("Century Gothic", Font.BOLD,42);
            textFont=new Font("Century Gothic", Font.BOLD,24);
            font=new Font("Arial", Font.PLAIN,24);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void init() {
        LevelReader.setCurrentLevel(0);
    }

    @Override
    public void update() {

    }

    @Override
    public void paint(Graphics2D g) {
        road.paint(g);
        if(!help) {
            g.setFont(titleFont);
            g.setColor(Color.BLACK);
            for (int shiftY = -1; shiftY <= 1; shiftY++) {
                for (int shiftX = -3; shiftX <= 3; shiftX++) {
                    g.drawString("Dog Race", 500 + shiftX, 225 + shiftY);
                }
            }
            g.setColor(titleColor);
            g.drawString("Dog Race", 500, 225);
            g.setFont(font);
            for (int i = 0; i < options.length; i++) {
                if (i == currentChoice) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(titleColor);
                }
                if(i==2)
                    g.drawString(options[i], 540, 280 + i * 30);
                else if (i == 3)
                    g.drawString(options[i], 530, 280 + i * 30);
                else
                    g.drawString(options[i], 560, 280 + i * 30);
            }
        }
        else{
            g.setFont(textFont);
            g.drawString(helpStr[0], 200, 50);
            g.drawImage(right, 185, 75,163,134, null);
            g.drawString(helpStr[1], 200, 250);
            g.drawImage(left, 185, 275,163,134,null);
            g.drawString(helpStr[2], 810, 50);
            g.drawImage(up, 795, 75,163,134,null);
            g.drawString(helpStr[3], 810, 250);
            g.drawImage(down, 795, 275,163,134,null);
            g.drawString(helpStr[4], 520,400 );
            g.drawImage(space, 375, 420,395,150,null);
            g.setFont(font);
            g.drawString("Чтобы выйти, нажмите 'esc'", 20,20 );
        }
    }
    private void select(){
        if(currentChoice==0){
            if(LevelReader.LevelReader("file/level.txt")) {
                if(!LevelReader.getBoss(0)){
                    LevelReader.setCurrentLevel(LevelReader.getCurrentLevel()+1);
                    gsm.gameStates.add(new Level1State(gsm));
                }
                else{
                    LevelReader.setCurrentLevel(LevelReader.getCurrentLevel()+1);
                    gsm.gameStates.add(new Level2State(gsm));
                }
                gsm.setName( JOptionPane.showInputDialog("Введите имя"));
                gsm.setState(gsm.getCurrentState()+1);
            }
            else
                JOptionPane.showMessageDialog(null,"Ошибка в заполнении файла level");
        }
        if(currentChoice==2){
            if(Loader.LoadPlayer()) {
               if (LevelReader.LevelReader("file/load.txt")) {
                    if (!LevelReader.getBoss(Loader.getCurrentLevel())) {
                        LevelReader.setCurrentLevel(Loader.getCurrentLevel()+1);
                        gsm.gameStates.add(new Level1State(gsm));
                    } else {
                        LevelReader.setCurrentLevel(Loader.getCurrentLevel()+1);
                        gsm.gameStates.add(new Level2State(gsm));
                    }
                    gsm.setName(Loader.getName());
                    gsm.setTime(Loader.getTime());
                    gsm.setState(gsm.getCurrentState() + 1);
              } else
                    JOptionPane.showMessageDialog(null, "Нет данных о сохранении");
            }else
                JOptionPane.showMessageDialog(null, "Нет данных о сохранении");
        }
        if(currentChoice==1){
            help=true;
            try{
                up= ImageIO.read(new FileInputStream("res/up.png"));
                down= ImageIO.read(new FileInputStream("res/down.png"));
                left= ImageIO.read(new FileInputStream("res/left.png"));
                right= ImageIO.read(new FileInputStream("res/right.png"));
                space= ImageIO.read(new FileInputStream("res/space.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(currentChoice==3){
            new RatingTable();
        }
        if(currentChoice==4){
            System.exit(0);
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int k=e.getKeyCode();
        if(k== KeyEvent.VK_ENTER){
            select();
        }
        if(k== KeyEvent.VK_UP){
            currentChoice--;
            if(currentChoice==-1){
                currentChoice=options.length-1;
            }
        }
        if(k== KeyEvent.VK_DOWN){
            currentChoice++;
            if(currentChoice==options.length){
                currentChoice=0;
            }
        }
        if(help&&k==27){
            help=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
