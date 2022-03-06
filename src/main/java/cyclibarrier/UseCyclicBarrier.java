package cyclibarrier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: 使用cycclibarrier来综合N个线程的结果，继续执行业务
 * @author: Ding Yawu
 * @create: 2022/1/22 16:24
 */
public class UseCyclicBarrier {
    /*创建一个CyclicBarrier实例,屏障数据设为3,处理完之后执行CollectThread类的run方法*/
    private static CyclicBarrier barrier = new CyclicBarrier(4, new CollectThread());

    /*创建一个ConcurrentHashMap,用来保存每个线程的id*/
    private static ConcurrentHashMap<String, Long> resultMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(new SubThread());
            thread.start();
        }

    }

    /*汇总的任务*/
    private static class CollectThread implements Runnable {
        /*等到所有的线程到达屏障*/
        @Override
        public void run() {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, Long> workResult : resultMap.entrySet()) {
                result.append("[" + workResult.getValue() + "]");
            }
            System.out.println(" the result = " + result);
            System.out.println("do other business........");
        }
    }

    /*相互等待的子线程*/
    private static class SubThread implements Runnable {

        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            resultMap.put(Thread.currentThread().getId() + "", id);
            try {
                Thread.sleep(1000 + id);
                System.out.println("Thread_" + id + " ....do something ");
                /*线程完成工作后调用await 设置屏障*/
                barrier.await();
//                Thread.sleep(1000 + id);
//                System.out.println("Thread_" + id + " ....do its business ");
//                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
