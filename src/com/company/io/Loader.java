package com.company.io;

import java.io.*;

public class Loader {
    private static String name;
    private static double time;
    private static int currentLevel;
    public static boolean LoadPlayer() {
        boolean read=false;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("file/save.txt")));
            BufferedWriter writer = new BufferedWriter(new FileWriter("file/load.txt"));
            String strLine;
            int count = 0;
            while ((strLine = br.readLine()) != null) {
                if (count == 0)
                    name = new String(strLine);
                else if(count ==1)
                    time=Double.valueOf(strLine);
                else if(count==2)
                    currentLevel=Integer.valueOf(strLine);
                else{
                    writer.append(strLine);
                    writer.append('\n');
                }
                count++;
            }
            writer.close();
            br.close();
            read=true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return read;
    }
    public static String getName(){
        return name;
    }
    public static double getTime(){
        return time;
    }
    public static int getCurrentLevel(){
        return currentLevel;
    }
}
