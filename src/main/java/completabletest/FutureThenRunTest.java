package completabletest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture的thenRun方法，通俗点讲就是，做完第一个任务后，再做第二个任务。某个任务执行完成后， 执行回调方法；但是前后两个任务没有参数传递，第二个任务也没有返回值
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 17:10
 */
public class FutureThenRunTest {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    // 可以自定义线程池
    ExecutorService executor = Executors.newCachedThreadPool();

    CompletableFuture<String> orgFuture =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println(Thread.currentThread().getName());
              System.out.println("先执行第一个CompletableFuture方法任务");
              return "捡田螺的小男孩";
            },
            executor);

    CompletableFuture thenRunFuture =
        orgFuture.thenRun(
            () -> {
              System.out.println(Thread.currentThread().getName());
              System.out.println("接着执行第二个任务");
            });

    System.out.println(thenRunFuture.get());
  }
}
