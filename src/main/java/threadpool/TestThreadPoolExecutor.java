package threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-08-25 7:35
 */
public class TestThreadPoolExecutor {

    private static final ThreadPoolExecutor pool;
    static {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("po-detail-pool-%d").build();
        pool = new ThreadPoolExecutor(4, 8, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(512),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        pool.allowCoreThreadTimeOut(true);
    }

    public static void main(String[] args) {
        int activeCount = pool.getActiveCount();
        System.out.println(activeCount);
        System.out.println(pool.getCorePoolSize());
    }
}
