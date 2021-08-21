package completabletest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture的thenApply方法表示，第一个任务执行完成后，执行第二个回调方法任务， 会将该任务的执行结果，作为入参，传递到回调方法中，并且回调方法是有返回值的。
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 17:34
 */
public class FutureThenApplyTest {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    CompletableFuture<String> orgFuture =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println("原始CompletableFuture方法任务");
              return "捡田螺的小男孩";
            });

    CompletableFuture<String> thenApplyFuture =
        orgFuture.thenApply(
            (a) -> {
              if ("捡田螺的小男孩".equals(a)) {
                return "关注了";
              }
              return "先考虑考虑";
            });

    System.out.println(thenApplyFuture.get());
  }
}
