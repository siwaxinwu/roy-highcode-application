package completabletest;

/**
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 14:55
 */
public class UserInfoService {

  public UserInfo getUserInfo(Long userId) {
    // 模拟调用耗时
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // 一般是查数据库，或者远程调用返回的
    return new UserInfo("666", "捡田螺的小男孩", 27);
  }
}
