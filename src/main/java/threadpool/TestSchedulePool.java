package threadpool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description: 测试定时任务线程池
 * @author: Ding Yawu
 * @create: 2022/2/5 17:07
 */
public class TestSchedulePool {
  public static void main(String[] args) {
      ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
      //开始定时执行任务，每隔三秒执行一次
      executorService.scheduleAtFixedRate( new Task2("fixed rate"), 0, 3, TimeUnit.SECONDS);
  }
}

class Task2 implements Runnable{
    private final String name;

    public Task2(String name) {
        this.name = name;
    }


    @Override
    public void run() {
        System.out.println("start task " + new Date());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end task " + new Date());
        System.out.println();
    }
}
