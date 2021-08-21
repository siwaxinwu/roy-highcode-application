/**
 * @description: 死锁的演示
 * @author: dingyawu
 * @date: created in 9:42 2021-01-31
 * @history:
 */
public class DeadLockDemo {
  private static Object resourceA = new Object();
  private static Object resourceB = new Object();

  public static void main(String[] args) {
    Thread threadA = createTheadA();
    Thread threadB = createTheadB();
    threadA.start();
    threadB.start();
  }

  private static Thread createTheadA() {
    Thread threadA =
        new Thread(
            () -> {
              synchronized (resourceA) {
                System.out.println(Thread.currentThread() + "get resourceA");
                try {
                  Thread.sleep(1000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "wait get resourceB");
                synchronized (resourceB) {
                  System.out.println(Thread.currentThread() + "get resourceB");
                }
              }
            },
            "threadA");
    return threadA;
  }

  private static Thread createTheadB() {
    Thread threadA =
        new Thread(
            () -> {
              synchronized (resourceB) {
                System.out.println(Thread.currentThread() + "get resourceB");
                try {
                  Thread.sleep(1000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "wait get resourceA");
                synchronized (resourceA) {
                  System.out.println(Thread.currentThread() + "get resourceA");
                }
              }
            },
            "threadB");
    return threadA;
  }
}
