package completabletest;

/**
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 14:57
 */
public class MedalService {

  public MedalInfo getMedalInfo(long userId) {
    // 模拟调用耗时
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return new MedalInfo("666", "守护勋章");
  }
}
