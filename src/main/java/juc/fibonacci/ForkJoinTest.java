package juc.fibonacci;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @description: 实现拆分任务功能，即一个大任务可以拆分成李忙个小任务，
 * ----循环这个步骤，直到达到阈值， 然后停止拆分，递归计算两个子任务的和
 * 创建任务ForkJoinTask，线程池执行任务ForkJoinPool
 * @author: Ding Yawu
 * @create: 2022/1/29 17:45
 */
public class ForkJoinTest {

  static Random random = new Random();
  static long random(){
      return random.nextInt(10000);
  }

  public static void main(String[] args) {
    long[] arrays = new long[2000];
    long expectSum = 0;
    for (int i = 0; i < arrays.length; i++) {
      arrays[i] = random();
      expectSum = expectSum + arrays[i];
    }
    System.out.println("expect sum:" + expectSum);
    System.out.println();

    ForkJoinTask<Long> task = new SumTask(arrays, 0, arrays.length);
    long startTime = System.currentTimeMillis();
    Long result = ForkJoinPool.commonPool().invoke(task);
    long endTime = System.currentTimeMillis();
    System.out.println("get result：" + result + "cost time：" + (endTime - startTime));
  }
}
