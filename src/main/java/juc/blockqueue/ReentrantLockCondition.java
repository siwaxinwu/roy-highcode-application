package juc.blockqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: Ding Yawu
 * @create: 2022/1/28 12:13
 */
public class ReentrantLockCondition {
    // 创建锁对象
    private final Lock lock = new ReentrantLock();
    // 相当于wait/notify
    private final Condition condition = lock.newCondition();
    // 数据集合
    private Queue<String> queue = new LinkedList<>();

    /**
     * 生产数据
     *
     * @param s 年代
     */
    public void addTask(String s) {
        // 获取锁，相当于synchronized
        lock.lock();
        try {
            queue.add(s);
            // 唤醒其他线程，相当于notify
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费数据
     *
     * @return {@link String}
     * @throws InterruptedException 中断异常
     */
    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                // 如果没数据，就卡住，等待并且释放锁，相当于await
                condition.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }
}


