package juc;

import com.sun.media.jfxmedia.events.PlayerEvent;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @description: semaphere常见用法
 *
 * 在现实很多场景下，无限等待会造成线程大量积压和阻塞，
 * 必须设置一个超时时间，超过等待时间后信号中断线程的等待，可以降低服务器的压力
 * @author: Ding Yawu
 * @create: 2021/12/20 21:32
 */
public class SemaphereExample {
  public static void main(String[] args) {
      ExecutorService threadPool = Executors.newCachedThreadPool();
      Semaphore semaphore = new Semaphore(5);
    for (int i = 0; i < 20; i++) {
        final int index = i;
      threadPool.execute(
          new Runnable() {
            @Override
            public void run() {
              try {
                if (semaphore.tryAcquire(6, TimeUnit.SECONDS)) {
                  play();
                  semaphore.release();
                } else {
                  System.out.println(Thread.currentThread().getName() + "对不起，服务器已满，请稍后再试");
                }
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
          });
    }
    threadPool.shutdown();
  }

    private static void play() {
      try{
          System.out.println(new Date() + " " + Thread.currentThread().getName() + "获得进入游戏资格");
          Thread.sleep(2000);
          System.out.println(new Date() + " " + Thread.currentThread().getName() + "退出服务器");
          Thread.sleep(500);
      }catch (InterruptedException e){

      }
    }
}
