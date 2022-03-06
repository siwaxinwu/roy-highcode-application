package juc.blockqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @description: 测试ArrayBlockingqueue
 * @author: Ding Yawu
 * @create: 2022/1/27 22:40
 */
public class BlockQueueTest3 {
  public static void main(String[] args) throws InterruptedException {
    //创建阻塞队列
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);
    List<Thread> list = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
          new Thread(
              () -> {
                while (true) {
                  try {
                    // 消费数据
                    String task = blockingQueue.take();
                    System.out.println("execute task:" + task);
                  } catch (Exception e) {
                    e.printStackTrace();
                    return;
                  }
                }
              }).start();
    }

    new Thread(
            () -> {
              for (int i = 0; i < 10; i++) {
                String s = "t-" + Math.random();
                System.out.println("add task:" + s);
                blockingQueue.offer(s);
                try {
                  Thread.sleep(100);
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            }).start();
  }
}



