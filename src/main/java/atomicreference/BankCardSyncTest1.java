package atomicreference;

import java.util.concurrent.TimeUnit;

/**
 * 每个线程内加锁来保证线程安全
 * @description:
 * @author: Ding Yawu
 * @create: 2021-10-14 22:52
 */
public class BankCardSyncTest1 {

    private static volatile BankCard bankCard = new BankCard("cxuan",100);

    public static void main(String[] args) {
        for(int i = 0;i < 10;i++){
            new Thread(() -> {
                synchronized (BankCardSyncTest1.class) {
                    // 先读取全局的引用
                    final BankCard card = bankCard;
                    // 构造一个新的账户，存入一定数量的钱
                    BankCard newCard = new BankCard(card.getAccountName(), card.getMoney() + 100);
                    System.out.println(newCard);
                    // 最后把新的账户的引用赋给原账户
                    bankCard = newCard;
                    try {
                        TimeUnit.MICROSECONDS.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}