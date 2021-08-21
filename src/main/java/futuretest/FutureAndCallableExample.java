package futuretest;

import java.util.concurrent.*;

/**
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-08-21 16:42
 */

public class FutureAndCallableExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // 使用 Callable ，可以获取返回值
        Callable<String> callable = () -> {
            System.out.println("进入 Callable 的 call 方法");
            // 模拟子线程任务，在此睡眠 2s，
            // 小细节：由于 call 方法会抛出 Exception，这里不用像使用 Runnable 的run 方法那样 try/catch 了
            Thread.sleep(5000);
            return "Hello from Callable";
        };
        System.out.println("提交 Callable 到线程池");
        Future<String> future = executorService.submit(callable);
        System.out.println("主线程等待获取 Future 结果");
        // Future.get() blocks until the result is available
        String result = future.get();
        System.out.println("主线程获取到 Future 结果" +  result);
        executorService.shutdown();
    }
}
