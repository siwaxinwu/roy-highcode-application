package futuretest;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-08-21 20:27
 */
public class MakeTeaExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 创建线程1的FutureTask
        FutureTask<String> ft1 = new FutureTask<String>(new T1Task());
        // 创建线程2的FutureTask
        FutureTask<String> ft2 = new FutureTask<String>(new T2Task());

        executorService.submit(ft1);
        executorService.submit(ft2);

        System.out.println(ft1.get() + ft2.get());
        System.out.println("开始泡茶");

        executorService.shutdown();
    }

    static class T1Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("T1:洗水壶...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("T1:烧开水...");
            TimeUnit.SECONDS.sleep(15);

            return "T1:开水已备好";
        }
    }

    static class T2Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T2:洗茶壶...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("T2:洗茶杯...");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("T2:拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return "T2:福鼎白茶拿到了";
        }
    }
}