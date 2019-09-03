package com.company.io;

import java.io.*;

public class LevelReader {
    private static int size;
    private static int currentLevel;
    private static boolean[] boss;
    private static String[] background;
    private static int[] bgWidth;
    private static int[] coorFinish;
    private static int[] numberOfRivals;

    public static int getSize() {
        return size;
    }
    public static int getCurrentLevel() {
        return currentLevel;
    }
    public static void setCurrentLevel(int cl){
        currentLevel=cl;
    }
    public static boolean getBoss(int i){
        return boss[i];
    }
    public static String getBackground(int i){
        return background[i];
    }
    public static int getBgWidth(int i){
        return bgWidth[i];
    }
    public static int getCoorFinish(int i){
        return coorFinish[i];
    }
    public static int getNumberOfRivals(int i){
        return numberOfRivals[i];
    }
    public static boolean LevelReader(String name) {
        boolean read=false;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(name)));
            String strLine;
            int count=0;
            int i=0;
            while ((strLine = br.readLine()) != null){
                if(count==0) {
                    size = Loader.getCurrentLevel()+Integer.valueOf(strLine);
                    boss = new boolean[size];
                    background=new String[size];
                    bgWidth=new int[size];
                    coorFinish=new int[size];
                    numberOfRivals=new int[size];
                }
                else {
                    switch (count % 5) {
                        case (1):
                            if (strLine.equals("false"))
                                boss[Loader.getCurrentLevel()+i] = false;
                            if (strLine.equals("true"))
                                boss[Loader.getCurrentLevel()+i] = true;
                            break;
                        case (2):
                            background[Loader.getCurrentLevel()+i] = new String(strLine);
                            break;
                        case (3):
                            bgWidth[Loader.getCurrentLevel()+i] = Integer.valueOf(strLine);
                            break;
                        case (4):
                            coorFinish[Loader.getCurrentLevel()+i] = Integer.valueOf(strLine);
                            break;
                        case (0):
                            numberOfRivals[Loader.getCurrentLevel()+i] = Integer.valueOf(strLine);
                            i++;
                            break;
                    }

                }
                count++;

            }
            if(count%5==1)
                read=true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return read;
    }
}
