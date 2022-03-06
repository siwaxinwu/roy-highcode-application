package atomicreference;

import com.google.errorprone.annotations.Var;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 利用atomicInteger来实现自增
 * @author: Ding Yawu
 * @create: 2022/1/23 16:09
 */
public class AtomicIntegerTest {
  private static final AtomicInteger atomicInteger = new AtomicInteger(0);

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new Thread(
              () -> {
                int result = atomicInteger.getAndIncrement();
                int result1 = incrementAndGet(atomicInteger);
                System.out.println(result);
                System.out.println(result1);
              })
          .start();
    }
  }

  public static int incrementAndGet(AtomicInteger var) {
    int expect, next;
    do {
      expect = var.get();
      next = expect + 1;
    } while (!var.compareAndSet(expect, next));
    return next;
  }
}
