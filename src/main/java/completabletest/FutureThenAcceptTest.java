package completabletest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture的thenAccept方法表示，第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果， 作为入参，传递到回调方法中，但是回调方法是没有返回值的。
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 17:30
 */
public class FutureThenAcceptTest {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    CompletableFuture<String> orgFuture =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println("原始CompletableFuture方法任务");
              return "捡田螺的小男孩";
            });

    CompletableFuture thenAcceptFuture =
        orgFuture.thenAccept(
            (a) -> {
              if ("捡田螺的小男孩".equals(a)) {
                System.out.println("关注了");
              }

              System.out.println("先考虑考虑");
            });

    System.out.println(thenAcceptFuture.get());
  }
}
