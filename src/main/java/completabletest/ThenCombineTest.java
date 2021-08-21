package completabletest;

import java.util.concurrent.*;

/**
 * thenCombine / thenAcceptBoth / runAfterBoth都表示：将两个CompletableFuture组合起来， 只有这两个都正常执行完了，才会执行某个任务。
 * thenCombine：会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值 thenAcceptBoth: 会将两个任务的执行结果作为方法入参，传递到指定方法中，且无返回值
 * runAfterBoth 不会把执行结果当做方法入参，且没有返回值。
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 17:55
 */
public class ThenCombineTest {
  public static void main(String[] args)
      throws InterruptedException, ExecutionException, TimeoutException {

    CompletableFuture<String> first = CompletableFuture.completedFuture("第一个异步任务");
    ExecutorService executor = Executors.newFixedThreadPool(10);
    CompletableFuture<String> future =
        CompletableFuture
            // 第二个异步任务
            .supplyAsync(() -> "第二个异步任务", executor)
            // (w, s) -> System.out.println(s) 是第三个任务
            .thenCombineAsync(
                first,
                (s, w) -> {
                  System.out.println(w);
                  System.out.println(s);
                  return "两个异步任务的组合";
                },
                executor);
    System.out.println(future.join());
    executor.shutdown();
  }
}
