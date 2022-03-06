package threadpool;

import java.util.concurrent.*;

/**
 * @description: 获取一个线程执行结果有哪些方式
 * @author: Ding Yawu
 * @create: 2022/1/6 21:16
 */
public class GetThreadResult {
  public static void main(String[] args) throws Exception {
      test3();

  }

    private static void test1() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(() -> {
            return 10;
        });
        Integer result = future.get();
        executorService.shutdown();
        System.out.println("执行test1结果：" + result);
    }

    private static void test2() throws ExecutionException, InterruptedException {
      //传参的是一个callable
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            return 10;
        });
        new Thread(futureTask).start();
        Integer result = futureTask.get();
        System.out.println("执行test2结果："+ result);
    }


    private static void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            return 10;
        });
        Integer result = completableFuture.get();
        System.out.println("执行test3结果：" + result);
    }
}
