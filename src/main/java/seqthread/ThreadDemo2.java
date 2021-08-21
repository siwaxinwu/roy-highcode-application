package seqthread;

import java.util.concurrent.CompletableFuture;

/**
 * 多线程顺序执行
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-08-13 9:17
 */
public class ThreadDemo2 {
  public static void main(String[] args) {
    Thread t1 = new Thread(new Work(), "线程1");
    Thread t2 = new Thread(new Work(), "线程2");
    Thread t3 = new Thread(new Work(), "线程3");

    CompletableFuture.runAsync(() -> t1.start())
        .thenRun(() -> t2.start())
        .thenRun(() -> t3.start());
  }

  static class Work implements Runnable {
    @Override
    public void run() {
      System.out.println("执行 : " + Thread.currentThread().getName());
    }
  }
}
