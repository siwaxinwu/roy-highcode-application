package completabletest.test;

import java.util.concurrent.CompletableFuture;

/**
 * applyToEither的作用是上个任务和这个任务一起运行，哪个先运行完成，就把哪个任务交给Function
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-06-30 7:05
 */
public class TestCompletableFuture4 {
  public static void main(String[] args) {
    SmallTool.printTimeAndThread("张三走出餐厅，来到公交站");
    SmallTool.printTimeAndThread("等待 700路 或者 800路 公交到来");

    CompletableFuture<String> bus =
        CompletableFuture.supplyAsync(
                () -> {
                  SmallTool.printTimeAndThread("700路公交正在赶来");
                  SmallTool.sleepMills(100);
                  return "700路到了";
                })
            .applyToEither(
                CompletableFuture.supplyAsync(
                    () -> {
                      SmallTool.printTimeAndThread("800路公交正在赶来");
                      SmallTool.sleepMills(200);
                      return "800路到了";
                    }),
                firstComeBus -> firstComeBus);

    SmallTool.printTimeAndThread(String.format("%s,小白坐车回家", bus.join()));
  }
}
