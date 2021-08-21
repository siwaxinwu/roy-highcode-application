package completabletest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture的exceptionally方法表示，某个任务执行异常时， 执行的回调方法;并且有抛出异常作为参数，传递到回调方法。
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 17:35
 */
public class FutureExceptionTest {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    CompletableFuture<String> orgFuture =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println("当前线程名称：" + Thread.currentThread().getName());
              throw new RuntimeException();
            });

    CompletableFuture<String> exceptionFuture =
        orgFuture.exceptionally(
            (e) -> {
              e.printStackTrace();
              return "你的程序异常啦";
            });

    System.out.println(exceptionFuture.get());
  }
}
