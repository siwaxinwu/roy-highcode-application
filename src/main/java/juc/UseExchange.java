package juc;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * @description: Exchanger,俗称交换器,用于在线程之间交换数据,但是比较受限,因为只能两个线程之间交换数据
 * @author: Ding Yawu
 * @create: 2021/11/10 13:37
 */
public class UseExchange {

    private static final Exchanger<Set<String>> exchanger = new Exchanger<>();

    public static void main(String[] args) {

        new Thread(() -> {
            Set<String> aSet = new HashSet<>();
            aSet.add("A");
            aSet.add("B");
            aSet.add("C");
            try {
                Set<String> exchange = exchanger.exchange(aSet);
                for (String s : exchange) {
                    System.out.println("aSet"+s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            Set<String> bSet = new HashSet<>();
            bSet.add("1");
            bSet.add("2");
            bSet.add("3");
            try {
                Set<String> exchange = exchanger.exchange(bSet);
                for (String s : exchange) {
                    System.out.println("bSet"+s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
