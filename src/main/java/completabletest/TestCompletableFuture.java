package completabletest;

import org.testng.annotations.Test;

import java.util.Locale;
import java.util.concurrent.*;

import static org.testng.Assert.*;

/**
 * 创建一个完成的CompletableFuture CompletableFuture的方法如果以Async结尾，它会异步的执行(没有指定executor的情况下)，
 * 异步执行通过ForkJoinPool实现， 它使用守护线程去执行任务。
 *
 * @author xmly
 * @date 2021/09/25
 */
public class TestCompletableFuture {
  static ExecutorService executor =
      Executors.newFixedThreadPool(
          3,
          new ThreadFactory() {
            int count = 1;

            @Override
            public Thread newThread(Runnable runnable) {
              return new Thread(runnable, "custom-executor-" + count++);
            }
          });

  public static void main(String[] args) throws InterruptedException {
    // completedFutureExample();
    // runAsyncExample();
    // thenApplyExample();
    // thenApplyAsyncExample();
    // thenApplyAsyncWithExecutorExample();
    // thenAcceptExample();
    // thenAcceptAsyncExample();

  }

  /** 创建一个完成的CompletableFuture */
  static void completedFutureExample() {
    CompletableFuture cf = CompletableFuture.completedFuture("message");
    assertTrue(cf.isDone());
    assertEquals("message", cf.getNow(null));
  }

  /**
   * 运行一个简单的异步阶段
   *
   * @throws InterruptedException 中断异常
   */
  static void runAsyncExample() throws InterruptedException {
    CompletableFuture cf =
        CompletableFuture.runAsync(
            () -> {
              System.out.println(Thread.currentThread().isDaemon());
              try {
                Thread.sleep(5000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });
    System.out.println(cf.isDone());
    Thread.sleep(10000);
    System.out.println(cf.isDone());
  }

  /** 在前一个阶段上应用函数 */
  static void thenApplyExample() {
    CompletableFuture cf =
        CompletableFuture.completedFuture("message")
            .thenApply(
                s -> {
                  System.out.println(Thread.currentThread().isDaemon());
                  System.out.println(Thread.currentThread().getName());
                  return s.toUpperCase();
                });
    System.out.println(cf.getNow(null));
    System.out.println(Thread.currentThread().getName());
  }

  /** 在前一个阶段上异步应用函数 */
  static void thenApplyAsyncExample() {
    CompletableFuture cf =
        CompletableFuture.completedFuture("message")
            .thenApplyAsync(
                s -> {
                  System.out.println(
                      Thread.currentThread().getName() + "-" + Thread.currentThread().isDaemon());
                  try {
                    Thread.sleep(10000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  return s.toUpperCase();
                });
    System.out.println(cf.getNow(null));
    System.out.println(cf.join());
  }

  static void thenApplyAsyncWithExecutorExample() {
    CompletableFuture cf =
        CompletableFuture.completedFuture("message")
            .thenApplyAsync(
                s -> {
                  System.out.println(
                      Thread.currentThread().getName() + "-" + Thread.currentThread().isDaemon());
                  try {
                    Thread.sleep(5000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  return s.toUpperCase();
                },
                executor);

    System.out.println(cf.getNow(null));
    System.out.println(cf.join());
  }

  /** 消费前一阶段的结果 */
  static void thenAcceptExample() {
    StringBuilder result = new StringBuilder();
    CompletableFuture.completedFuture("thenAccept message")
        .thenAccept(
            s -> {
              System.out.println(Thread.currentThread().getName());
              result.append(s);
            });
    System.out.println(result.toString());
  }

  /** 异步地消费迁移阶段的结果 */
  static void thenAcceptAsyncExample() {
    StringBuilder result = new StringBuilder();
    CompletableFuture cf =
        CompletableFuture.completedFuture("thenAcceptAsync message")
            .thenAcceptAsync(
                s -> {
                  System.out.println(Thread.currentThread().getName());
                  System.out.println(result.append(s));
                });
    cf.join();
    System.out.println(result.toString());
  }

  @Test
  public void test2() throws Exception {
    // 创建异步执行任务，有返回值
    CompletableFuture<Double> cf =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println(
                  Thread.currentThread() + " start,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              if (true) {
                throw new RuntimeException("test");
              } else {
                System.out.println(
                    Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
                return 1.2;
              }
            });
    System.out.println("main thread start,time->" + System.currentTimeMillis());
    // 等待子任务执行完成
    System.out.println("run result->" + cf.get());
    System.out.println("main thread exit,time->" + System.currentTimeMillis());
  }

  @Test
  public void test4() throws Exception {
    // 创建异步执行任务，无返回值
    CompletableFuture cf =
        CompletableFuture.runAsync(
            () -> {
              System.out.println(
                  Thread.currentThread() + " start,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              if (false) {
                throw new RuntimeException("test");
              } else {
                System.out.println(
                    Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
              }
            });
    System.out.println("main thread start,time->" + System.currentTimeMillis());
    // 等待子任务执行完成
    System.out.println("run result->" + cf.get());
    System.out.println("main thread exit,time->" + System.currentTimeMillis());
  }

  @Test
  public void test5() throws Exception {
    ForkJoinPool pool = new ForkJoinPool();
    // 创建异步执行任务:
    CompletableFuture<Double> cf =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println(
                  Thread.currentThread() + " start,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              if (true) {
                throw new RuntimeException("test");
              } else {
                System.out.println(
                    Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
                return 1.2;
              }
            },
            pool);
    System.out.println("main thread start,time->" + System.currentTimeMillis());
    // 等待子任务执行完成
    System.out.println("run result->" + cf.get());
    System.out.println("main thread exit,time->" + System.currentTimeMillis());
  }

  @Test
  public void test6() throws Exception {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    // 创建异步执行任务:
    CompletableFuture<Integer> cf =
        CompletableFuture.supplyAsync(
            () -> {
              System.out.println(
                  Thread.currentThread() + " start,time->" + System.currentTimeMillis());
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
              if (false) {
                throw new RuntimeException("test");
              } else {
                System.out.println(
                    Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
              }
              return 23;
            },
            executorService);
    System.out.println("main thread start,time->" + System.currentTimeMillis());
    // 等待子任务执行完成
    System.out.println("run result->" + cf.get());
    System.out.println("main thread exit,time->" + System.currentTimeMillis());
  }
}
