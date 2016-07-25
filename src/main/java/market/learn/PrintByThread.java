package market.learn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class PrintByThread {

  public static void main(String[] args) {
    try{
      PrintWriter writer = new PrintWriter(new FileWriter(new File("input.txt")), true);
      Random random = new Random();
      for(int i = 0; i < 10000; i++){
        writer.print(Math.abs(random.nextInt()) % 100 + ",");
      }
      writer.flush();
      writer.close();
      
      BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
      String str = reader.readLine();
      reader.close();
      String[] strs = str.split(",");
      int j = 0;
      for(int i = 0; i < 5; i++){
        int records[] = new int[2000];
        for(int k = 0;k < 2000; k++){
          records[k] = Integer.parseInt(strs[j]);
          j++;
        }
        PrintWriter out = new PrintWriter(new FileWriter(new File("output" + i)));
        ThreadGroup group = new ThreadGroup(records, out);
        new Thread(group).start();
        new Thread(group).start();
      }
    } catch (Exception e){
      e.printStackTrace();
    }
  }
}
