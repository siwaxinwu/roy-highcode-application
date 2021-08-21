import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionService;

/**
 * thenCompose方法的作用是把前面任务的结果交给下一个异步任务， 在前一个任务完成有结果后，下一个任务才会出发
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-06-30 7:05
 */
public class TestCompletableFuture1 {
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
            .thenCompose(
                dish ->
                    CompletableFuture.supplyAsync(
                        () -> {
                          SmallTool.printTimeAndThread("服务员打饭");
                          SmallTool.sleepMills(100);
                          return dish + "米饭";
                        }));

    SmallTool.printTimeAndThread("小王在打王者");
    SmallTool.printTimeAndThread(String.format("%s, 小白在吃", cf.join()));
  }
}
