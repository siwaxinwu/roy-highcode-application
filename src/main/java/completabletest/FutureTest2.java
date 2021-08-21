package completabletest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示runAsync、supplyAsync的用法
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 16:57
 */
public class FutureTest2 {
  public static void main(String[] args) {
    // 可以自定义线程池
    ExecutorService executor = Executors.newCachedThreadPool();
    // runAsync的使用
    CompletableFuture<Void> runFuture =
        CompletableFuture.runAsync(() -> System.out.println("run,关注公众号:捡田螺的小男孩"), executor);
    // supplyAsync的使用
    CompletableFuture<String> supplyFuture =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.print("supply,关注公众号:捡田螺的小男孩");
              return "捡田螺的小男孩";
            },
            executor);
    // runAsync的future没有返回值，输出null
    System.out.println(runFuture.join());
    // supplyAsync的future，有返回值
    System.out.println(supplyFuture.join());
    // 线程池需要关闭
    executor.shutdown();
  }
}
