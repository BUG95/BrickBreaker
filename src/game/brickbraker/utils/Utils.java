package game.brickbraker.utils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Utils {
   public static String loadFile(String path){
       StringBuilder sb = new StringBuilder();
       try {
           BufferedReader br = new BufferedReader(new FileReader(path));
           String line;
           while((line = br.readLine()) != null){
               sb.append(line).append("\n");
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return sb.toString();
   }

   public static int parseInt(String s){
       try{
           return Integer.parseInt(s);
       } catch(NumberFormatException e){e.printStackTrace();}
       return 0;
   }

   public static int getRandomNumberInRange(int availableElements){
       Random random = new Random();
       return random.nextInt(availableElements) + 1;
   }
}
