package juc.testStop;

/**
 * @description:  线程.interrupt是要等线程执行完才能停止的，知识给thread这个线程去设置一个中断标识位为true，并没有真正的
 * 去终止我们的线程thread，threa的终止还得看他自己，需要自己用Thread.currentThread().isInterrupted()这个方法来获取中断标识位
 *
 * 线程.stop会打断线程，造成数据的不一致，属于强制停止线程
 * @author: Ding Yawu
 * @create: 2022/2/28 20:50
 */
public class TestInterrupt {

  public static void main(String[] args) throws InterruptedException {
      Thread thread = new Thread(() -> {
          System.out.println("start inner thread");
          while (!Thread.interrupted()) {
              System.out.println("execute ......");
          }
      });
      thread.start();
      Thread.sleep(10000);
      thread.interrupt();


  }
}
