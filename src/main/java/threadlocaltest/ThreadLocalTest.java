package threadlocaltest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用ThreadLocal，给每个线程分配自己的sdf对象，保证了线程安全，高效利用内存
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-26 13:31
 */
public class ThreadLocalTest {

  public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

  public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal =
      ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 1000; i++) {
      int finalI = i;
      threadPool.submit(
          () -> {
            // SimpleDateFormat sdf = ThreadLocalTest.dateFormatThreadLocal.get();
            String date = sdf.format(new Date(1000 * finalI));
            System.out.println(date);
          });
    }
    threadPool.shutdown();
  }
}
