import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * futuretask的使用方法
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-06-30 6:50
 */
public class TestFutureTask {
  public static void main(String[] args) {
    Callable<String> callable =
        () -> {
          System.out.println("i am sub task");
          return "sub task game over";
        };
    FutureTask<String> futureTask = new FutureTask(callable);
    Thread thread = new Thread(futureTask);
    thread.start();
    System.out.println("sub task start");
    String result = null;
    try {
      // 主线程不停地询问子线程的执行情况，子线程执行完毕，或者抛出异常，主线程才能得知结果，继续运行
      // 调用了get，主线程一直等待结果，可以设置超时时间
      result = futureTask.get();
      System.out.println("sub task get result" + result);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      Throwable cause = e.getCause();
    }
    System.out.println("main game over");
  }
}
