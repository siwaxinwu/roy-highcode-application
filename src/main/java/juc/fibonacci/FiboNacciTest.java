package juc.fibonacci;

/**
 * @description:
 * @author: Ding Yawu
 * @create: 2022/1/30 08:27
 */
public class FiboNacciTest {
  static int i = 6;

  public static void main(String[] args) {
    int value = fibonacci(i);
    System.out.println(value);
    //
  }

  /**
   * 递归计算
   *
   * @param i 我
   * @return int
   */
  public static int fibonacci(int i) {
    System.out.println("当前是第几个数：" + i);
    if (i < 2) {
      return i;
    } else {
      // 递归调用
      return fibonacci(i - 1) + fibonacci(i - 2);
    }
  }
}
