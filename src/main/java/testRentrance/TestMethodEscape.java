package testRentrance;

/**
 * @description: 演示锁消除， 临界资源没有逃逸出方法，属于线程私有
 * @author: Ding Yawu
 * @create: 2022/3/16 22:14
 */
public class TestMethodEscape {
  public static void main(String[] args) {
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; i < 10; i++) {
      stringBuffer.append(i);
    }
    System.out.println(stringBuffer.toString());
  }
}
