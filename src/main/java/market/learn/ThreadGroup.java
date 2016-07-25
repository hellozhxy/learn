package market.learn;

import java.io.PrintWriter;

public class ThreadGroup implements Runnable {

  private static int count = 0;
  private static Object lock = new Object();
  public static final int EVEN = 0;
  public static final int ODD = 1;

  private int type;
  private int records[];
  private PrintWriter writer;
  private int oddPoint = 0;
  private int evenPoint = 0;

  public ThreadGroup(int[] records, PrintWriter writer) {
    this.records = records;
    this.writer = writer;
    this.type = EVEN;
  }

  @Override
  public void run() {
    while (print());
  }

  private synchronized boolean print() {
    for(int i = 0; i < 10;){
      if(oddPoint >= records.length && evenPoint >= records.length){
        notifyAll();
        return false;
      }
      if((oddPoint >= records.length && type == ODD) || (evenPoint >= records.length && type == EVEN)){
        break;
      }
      if(type == EVEN){
        if(records[evenPoint] % 2 == 0){
          i++;
          writer.print(records[evenPoint] + ",");
          writer.flush();
          
          synchronized (lock) {
            count++;
            if(count % 1000 == 0){
              System.out.println("当前完成数量：" + count);
              if(count == 10000){
                System.out.println("Done!");
              }
            }
          }
        }
        evenPoint++;
      }else{
        if(records[oddPoint] % 2 == 1){
          i++;
          writer.print(records[oddPoint] + ",");
          writer.flush();
          
          synchronized (lock) {
            count++;
            if(count % 1000 == 0){
              System.out.println("当前完成数量：" + count);
              if(count == 10000){
                System.out.println("Done!");
              }
            }
          }
        }
        oddPoint++;
      }
    }
    type = ~type;
    notifyAll();
    try{
      wait();
    } catch (Exception e){
      e.printStackTrace();
    }
    return false;
  }
}
