package com.company;

import com.company.View.GamePanel;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	JFrame f=new JFrame("Dog race");
	f.setContentPane(new GamePanel());
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setResizable(false);
	f.pack();
	f.setVisible(true);
    }
}
