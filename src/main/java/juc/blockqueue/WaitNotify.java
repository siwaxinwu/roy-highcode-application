package juc.blockqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: Ding Yawu
 * @create: 2022/1/28 12:11
 */
public class WaitNotify{

    // 数据集合
    private Queue<String> queue = new LinkedList<>();

    /**
     * 生产数据
     *
     * @param s 年代
     */
    public synchronized void addTask(String s) {

        this.queue.add(s);
        //唤醒其他线程
        this.notifyAll();
    }

    /**
     * 消费数据
     *
     * @return {@link String}
     * @throws InterruptedException 中断异常
     */
    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            //1.当线程执行到这一行，会卡住并且释放锁，
            // 2.其他线程调用notify才会被唤醒，然后和所有被唤醒的线程一起抢同一把锁，（前提是生产线程要释放锁，生产数据的方法执行了，）
            //1)如果抢到了锁，就再次while判断，如果有数据，就跳出while循环，再次获取数据
            //2)如果没有抢到锁，就会急速等待和卡住。
            this.wait();
        }
        return queue.remove();
    }

}