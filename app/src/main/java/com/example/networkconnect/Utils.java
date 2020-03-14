package com.example.networkconnect;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils {
    public static String inputstreamtoString(InputStream inputStream){
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder strvalue=new StringBuilder();
        String line="";
        try {
            while ((line =bufferedReader.readLine()) !=null){
                strvalue.append(line)
                        .append("\n");
            }
            return strvalue.toString();
        }
        catch (IOException IOEX){
            return null;
        }

    }
}
