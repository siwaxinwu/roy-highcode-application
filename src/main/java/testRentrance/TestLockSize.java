package testRentrance;

/**
 * @description: 演示锁粗化
 * @author: Ding Yawu
 * @create: 2022/3/16 22:14
 */
public class TestLockSize {
  public  void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      synchronized (this){
        System.out.println(Thread.currentThread().getName() + "execute" + i);
      }
    }
  }

  /*优化以后的代码*/
  public  void main1(String[] args) {
    synchronized (this){
      for (int i = 0; i < 10; i++) {
        System.out.println(Thread.currentThread().getName() + "execute" + i);
      }
    }
  }
}
