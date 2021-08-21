package seqthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程池
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-08-13 9:07
 */
public class ThreadDemo1 {
  public static void main(String[] args) {
    Thread t1 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                System.out.println("线程1");
              }
            },
            "线程1");

    Thread t2 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                System.out.println("线程2");
              }
            },
            "线程2");

    Thread t3 =
        new Thread(
            new Runnable() {
              @Override
              public void run() {
                System.out.println("线程3");
              }
            });

    ExecutorService executor = Executors.newSingleThreadExecutor();
    // 将线程依次加入到线程池中
    executor.submit(t1);
    executor.submit(t2);
    executor.submit(t3);
    // 及时将线程池关闭

    executor.shutdown();
  }
}
