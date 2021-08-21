package completabletest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-07-05 15:13
 */
public class FutureTest1 {

  public static void main(String[] args)
      throws InterruptedException, ExecutionException, TimeoutException {

    UserInfoService userInfoService = new UserInfoService();
    MedalService medalService = new MedalService();
    long userId = 666L;
    long startTime = System.currentTimeMillis();

    // 调用用户服务获取用户基本信息
    CompletableFuture<UserInfo> completableUserInfoFuture =
        CompletableFuture.supplyAsync(() -> userInfoService.getUserInfo(userId));
    // 模拟主线程其它操作耗时
    Thread.sleep(300);

    CompletableFuture<MedalInfo> completableMedalInfoFuture =
        CompletableFuture.supplyAsync(() -> medalService.getMedalInfo(userId));
    // 获取个人信息结果
    UserInfo userInfo = completableUserInfoFuture.get(10, TimeUnit.SECONDS);
    // 获取勋章信息结果
    MedalInfo medalInfo = completableMedalInfoFuture.get();
    System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
  }
}
