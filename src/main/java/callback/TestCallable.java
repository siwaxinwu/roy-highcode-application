package callback;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.*;

/**
 * @description:  学习callable接口的写法，泛型的使用
 * @author: Ding Yawu
 * @create: 2022/1/22 12:59
 */
public class TestCallable {
  public static void main(String[] args) throws Exception{
    ExecutorService es = Executors.newFixedThreadPool(4);
    Future<BigDecimal> future = es.submit(new Task());
    BigDecimal result = future.get();
    System.out.println(result);
    es.shutdown();
  }
}


class Task implements Callable<BigDecimal>{

  @Override
  public BigDecimal call() throws Exception {
    Thread.sleep(1000);
    double result = 5 + Math.random() * 5;
    throw new Exception("ok");
    //return new BigDecimal(result).setScale(2, RoundingMode.HALF_DOWN);

  }
}