package completabletest;

import java.util.concurrent.*;

/**
 * 测试future
 *
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 14:59
 */
public class FutureTest {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    UserInfoService userInfoService = new UserInfoService();
    MedalService medalService = new MedalService();
    long userId = 666L;
    long startTime = System.currentTimeMillis();

    // 调用用户服务获取用户基本信息
    FutureTask<UserInfo> userInfoFutureTask =
        new FutureTask<>(
            new Callable<UserInfo>() {
              @Override
              public UserInfo call() throws Exception {
                return userInfoService.getUserInfo(userId);
              }
            });
    executorService.submit(userInfoFutureTask);
    // 模拟主线程其它操作耗时
    Thread.sleep(300);

    FutureTask<MedalInfo> medalInfoFutureTask =
        new FutureTask<>(
            new Callable<MedalInfo>() {
              @Override
              public MedalInfo call() throws Exception {
                return medalService.getMedalInfo(userId);
              }
            });
    executorService.submit(medalInfoFutureTask);
    // 获取个人信息结果
    UserInfo userInfo = userInfoFutureTask.get();
    // 获取勋章信息结果
    MedalInfo medalInfo = medalInfoFutureTask.get();

    System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
  }
}
