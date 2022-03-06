import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @description: 红包算法，每个人平均抢到的金额是相等的
 * @author: Ding Yawu
 * @create: 2022/1/30 12:22
 */
public class RedPacketTest {
  public static void main(String[] args) {
      List<Integer> amountList = divideRedPackage(5000, 30);
    amountList.forEach(
        amount -> {
          System.out.println("抢到" + new BigDecimal(amount).divide(new BigDecimal(100)) + "元");
        });
    System.out.println(amountList.size());
  }

  public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum){
    Integer restAmount = totalAmount;
    Integer restPeopleNum = totalPeopleNum;
    List<Integer> amountlist = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < totalPeopleNum - 1; i++) {
        int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
        restAmount = restAmount - amount;
        restPeopleNum --;
        amountlist.add(amount);
    }
    amountlist.add(restAmount);
    return amountlist;
  }
}
