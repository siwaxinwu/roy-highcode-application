package callback;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:
 * @author: Ding Yawu
 * @create: 2022/2/13 09:18
 */
public class ReentrantReadWriteLockTest1 implements Runnable{

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        lock.lock();
        System.out.println("执行任务线程");
        lock.unlock();
    }

  public static void main(String[] args) {
    new Thread(new ReentrantReadWriteLockTest1()).start();
  }
}
