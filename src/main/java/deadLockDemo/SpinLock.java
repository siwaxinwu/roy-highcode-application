package deadLockDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自旋锁的实现
 * @description:
 * @author: Ding Yawu
 * @create: 2021-10-04 05:22
 */
public class SpinLock {

    //lockValue 默认值1
    private AtomicInteger lockValue = new AtomicInteger(1);

    //自旋获取锁
    public void lock(){

        // 循环检测尝试获取锁
        while (!tryLock()){
            // 空转
        }

    }

    //获取锁
    public boolean tryLock(){
        // 期望值1，更新值0，更新成功返回true，更新失败返回false
        return lockValue.compareAndSet(1,0);
    }

    //释放锁
    public void unLock(){
        if(!lockValue.compareAndSet(1,0)){
            throw new RuntimeException("释放锁失败");
        }
    }

}
