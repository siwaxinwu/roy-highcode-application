package juc.fibonacci;

import com.sun.org.apache.bcel.internal.generic.NEW;
import juc.fibonacci.FibonacciTask;

import java.util.concurrent.ForkJoinPool;

/**
 * @description:
 * @author: Ding Yawu
 * @create: 2022/1/30 08:27
 */
public class FiboNacciTest1 {
  static int i = 6;

  public static void main(String[] args) {
    //创建任务线程
    FibonacciTask fibonacciTask = new FibonacciTask(i);
    //执行任务线程
    Integer values = ForkJoinPool.commonPool().invoke(fibonacciTask);
    System.out.println();
    System.out.println(values);
  }


}
