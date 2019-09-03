package com.company.io;


import java.io.FileWriter;
import java.io.IOException;

public class Saver {
    public static void SavePlayer(String name, double time){
        try(FileWriter writer = new FileWriter("file/save.txt"))
        {
            writer.write(name);
            writer.append('\n');
            writer.append(String.valueOf(time));
            writer.append('\n');
            writer.append(String.valueOf(LevelReader.getCurrentLevel()));
            writer.append('\n');
            writer.append(String.valueOf(LevelReader.getSize()-LevelReader.getCurrentLevel()));
            writer.append('\n');
            for(int i=LevelReader.getCurrentLevel(); i<LevelReader.getSize();i++) {
                if(LevelReader.getBoss(i)==true)
                    writer.append("true");
                else
                    writer.append("false");
                writer.append('\n');
                writer.append(LevelReader.getBackground(i));
                writer.append('\n');
                writer.append(String.valueOf(LevelReader.getBgWidth(i)));
                writer.append('\n');
                writer.append(String.valueOf(LevelReader.getCoorFinish(i)));
                writer.append('\n');
                writer.append(String.valueOf(LevelReader.getNumberOfRivals(i)));
                writer.append('\n');
            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
