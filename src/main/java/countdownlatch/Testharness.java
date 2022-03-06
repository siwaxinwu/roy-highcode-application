package countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: Ding Yawu
 * @create: 2022/1/22 16:44
 */
public class Testharness {
    public static long timeTasks(int threads, Runnable task) throws Exception {
        //创建两个门
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threads);
        for (int i = 0; i < threads; ++i) {
            Thread thread = new Thread(
                    () -> {
                        try{
                            startGate.await();
                            try{
                                task.run();
                            }finally{
                                endGate.countDown();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
            );
            thread.start();
        }
        long start = System.nanoTime();
        System.out.println("主线程开始工作");
        //只有主线程准备工作完成，才允许开始执行业务线程
        startGate.countDown();
        //当所有的业务线程都完成以后，主线程再继续工作
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

  public static void main(String[] args) throws Exception {
      long result = timeTasks(3, () -> {
          System.out.println("dyw....");
      });
    System.out.println(result);
  }
}
