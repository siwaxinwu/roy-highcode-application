package test1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Ding Yawu
 * @create: 2022/3/12 11:33
 */
public class TestThreadSeq1 {
  public static void main(String[] args) {
      Thread thread1 = new Thread(() -> {

          System.out.println("thread1执行了");
      });
      Thread thread2 = new Thread(() -> {
          try {
              thread1.join();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          System.out.println("thread2执行了");
      });
      Thread thread3 = new Thread(() -> {
          try {
              thread2.join();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          System.out.println("thread3执行了");
      });

      CompletableFuture.runAsync(thread3).thenRun(thread2).thenRun(thread1);
  }
}
