package seqthread;

/**
 * 多线程顺序执行
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-08-13 8:53
 */
public class ThreadDemo {
  public static void main(String[] args) {

    final Thread t1 = new Thread(() -> System.out.println("线程1"));

    final Thread t2 = new Thread(
                () -> {
                  try {
                    // 等待线程t1执行完成后
                    // 本线程t2 再执行
                    t1.join();
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  System.out.println("线程2");
                });

    Thread t3 =
        new Thread(
                () -> {
                  try {
                    // 等待线程t2执行完成后
                    // 本线程t3 再执行
                    t2.join();
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                  System.out.println("线程3");
                });
    t3.start();
    t2.start();
    t1.start();
  }
}
