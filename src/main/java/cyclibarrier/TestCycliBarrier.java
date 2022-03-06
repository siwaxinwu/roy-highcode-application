package cyclibarrier;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @description:  CycliBarrier的demo演示
 * @author: Ding Yawu
 * @create: 2022/1/22 15:53
 */
public class TestCycliBarrier {
  public static void main(String[] args) {
      //创建循环门对象
      CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
          System.out.println("本关卡所有的前置任务均完成，开始游戏。。。。。。");
      });
      new Thread(new PreTaskThread("加载地图数据", cyclicBarrier)).start();
      new Thread(new PreTaskThread("加载人物模型", cyclicBarrier)).start();
      new Thread(new PreTaskThread("加载背景音乐", cyclicBarrier)).start();


  }


}
class  PreTaskThread implements Runnable{

    private String task;
    private CyclicBarrier cyclicBarrier;

    public PreTaskThread(String task, CyclicBarrier cyclicBarrier){
        this.task = task;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; ++i) {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("关卡"+ i + "的任务"  + task +" 完成");
                //执行完成后，当前线程抵达循环门，当所有的线程都抵达这个门，这个门才打开，执行门自己的线程任务
                cyclicBarrier.await();

            } catch (Exception e) {
                e.printStackTrace();
            }
            cyclicBarrier.reset();
        }
    }


}