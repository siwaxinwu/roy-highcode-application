/**
 * 测试线程的状态
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-06-30 6:46
 */
public class TestThreadState {
  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread();
    System.out.println("线程状态" + thread.getState());

    thread.start();
    System.out.println("线程状态" + thread.getState());
    Thread.sleep(1000);
    System.out.println("线程状态" + thread.getState());
  }
}
