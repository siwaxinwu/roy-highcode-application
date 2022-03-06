package juc.blockqueue;

import javax.management.QueryEval;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 基于ReentrantLock和Condition实现阻塞队列
 * @author: Ding Yawu
 * @create: 2022/1/27 22:40
 */
public class BlockQueueTest1 {
  public static void main(String[] args) throws InterruptedException {
    //创建阻塞队列
    WaitNotify waitNotify = new WaitNotify();
    List<Thread> list = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      Thread thread =
          new Thread(
              () -> {
                while (true) {
                  try {
                    // 消费数据
                    String task = waitNotify.getTask();
                    System.out.println("execute task:" + task);
                  } catch (Exception e) {
                    e.printStackTrace();
                    return;
                  }
                }
              });
      thread.start();
      list.add(thread);
    }

    Thread add =
        new Thread(
            () -> {
              for (int i = 0; i < 10; i++) {
                String s = "t-" + Math.random();
                System.out.println("add task:" + s);
                waitNotify.addTask(s);
                try {
                  Thread.sleep(100);
                } catch (Exception e) {
                  e.printStackTrace();
                }
              }
            });
    add.start();
    add.join();
    Thread.sleep(100);
    for (Thread thread: list){
      thread.interrupt();
    }
  }
}



