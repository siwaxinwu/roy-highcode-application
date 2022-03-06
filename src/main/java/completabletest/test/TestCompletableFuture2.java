package completabletest.test;

import java.util.concurrent.CompletableFuture;

/**
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-06-30 7:05
 */
public class TestCompletableFuture2 {
  public static void main(String[] args) {
    SmallTool.printTimeAndThread("小白进入点餐餐厅");
    SmallTool.printTimeAndThread("小白点了 番茄炒蛋 一碗米饭");
    CompletableFuture<String> cf =
        CompletableFuture.supplyAsync(
                () -> {
                  SmallTool.printTimeAndThread("厨师炒菜");
                  SmallTool.sleepMills(200);
                  return "番茄炒蛋";
                })
            .thenCombine(
                CompletableFuture.supplyAsync(
                    () -> {
                      SmallTool.printTimeAndThread("服务员蒸饭");
                      SmallTool.sleepMills(300);
                      return "蒸米饭";
                    }),
                (dish, rice) -> {
                  SmallTool.printTimeAndThread("服务员打饭");
                  SmallTool.sleepMills(100);
                  return dish + rice;
                });

    SmallTool.printTimeAndThread("小王在打王者");
    SmallTool.printTimeAndThread(String.format("%s, 小白在吃", cf.join()));
  }
}
