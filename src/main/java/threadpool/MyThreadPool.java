package threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

/**
 * 手写一个线程池
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-08-24 13:56
 */
public class MyThreadPool {

    BlockingQueue<Runnable> taskQueue;  //存放任务的阻塞队列
    List<MyThread> threads; //线程列表

    MyThreadPool(BlockingQueue<Runnable> taskQueue, int threadSize) {
        this.taskQueue = taskQueue;
        threads = new ArrayList<>(threadSize);
        // 初始化线程，并定义名称
        IntStream.rangeClosed(1, threadSize).forEach((i)-> {
            MyThread thread = new MyThread("yes-task-thread-" + i);
            thread.start();
            threads.add(thread);
        });
    }
    //提交任务只是往任务队列里面塞任务
    public void execute(Runnable task) throws InterruptedException {
        taskQueue.put(task);
    }

    class MyThread extends Thread { //自定义一个线程
        public MyThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            while (true) { //死循环
                Runnable task = null;
                try {
                    task = taskQueue.take(); //不断从任务队列获取任务
                } catch (InterruptedException e) {
                    System.out.println("记录点东西....." + e);
                }
                task.run(); //执行
            }
        }
    }

    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(new LinkedBlockingQueue<>(10), 3);
        IntStream.rangeClosed(1, 5).forEach((i)-> {
            try {
                pool.execute(()-> {
                    System.out.println(Thread.currentThread().getName() + " 公众号：yes的练级攻略");
                });
            } catch (InterruptedException e) {
                System.out.println("记录点东西....." + e);
            }
        });
    }


}
