package daemonthread;

import java.io.IOException;

/**
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-08-06 10:28
 */
public class TestDaemonThread {
  public static void main(String[] args) throws IOException {
    Thread thread =
        new Thread(
            () -> {
              execute();
            });
    thread.setDaemon(true);
    thread.start();
    System.in.read();
  }

  public static void execute() {
    for (int i = 0; ; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(i);
    }
  }
}
