package com.company.io;


import java.io.*;

public class TableReader {
    public static String[][] TableReader(String name) {
        String[][] data = new String[10][3];
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(name), "utf8");
            int symbol;
            char[] buffer = new char[1024];
            int ind = 0;
            int row = 0;
            while ((symbol = reader.read()) > 0) {
                buffer[ind] = (char) symbol;
                ind++;
                if (buffer[ind - 1] == '\n' && row < 10) {
                    String data_str = String.valueOf(buffer);
                    data[row] = data_str.trim().split("\\s+");
                    ind = 0;
                    row++;
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
