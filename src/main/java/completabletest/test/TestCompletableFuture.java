package completabletest.test;

import java.util.concurrent.CompletableFuture;

/**
 * cf.join()方法返回的就是CompletableFuture的泛型 ，join方法会等到任务执行结束，然后返回任务的结果 join不需要我们catch， 但是也会抛出异常，
 * 抛出的都是运行时异常，方法注释里都有写
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-06-30 7:05
 */
public class TestCompletableFuture {
  public static void main(String[] args) {
    SmallTool.printTimeAndThread("小白进入点餐餐厅");
    SmallTool.printTimeAndThread("小白点了 番茄炒蛋 一碗米饭");
    CompletableFuture<String> cf =
        CompletableFuture.supplyAsync(
            () -> {
              SmallTool.printTimeAndThread("厨师炒菜");
              SmallTool.sleepMills(200);
              SmallTool.printTimeAndThread("厨师打饭");
              SmallTool.sleepMills(100);
              return "番茄炒蛋 一碗米饭 做好了";
            });
    SmallTool.printTimeAndThread("小王在打王者");
    SmallTool.printTimeAndThread(String.format("%s, 小白在吃", cf.join()));
  }
}
