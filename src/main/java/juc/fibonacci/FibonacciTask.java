package juc.fibonacci;

import java.util.concurrent.RecursiveTask;

/**
 * @description: 要继承递归任务
 *
 * @author: Ding Yawu
 * @create: 2022/1/30 08:40
 */
public class FibonacciTask extends RecursiveTask<Integer> {

    final int i;

    FibonacciTask(int i){
        this.i = i;
    }
    @Override
    protected Integer compute() {
        if (i <= 1){
            return i;
        }
        //
        FibonacciTask f1 = new FibonacciTask(i - 1);
        FibonacciTask f2 = new FibonacciTask(i - 2);
        return f1.compute() + f2.compute();
    }
}
