package juc.fibonacci;

import java.util.concurrent.RecursiveTask;

/**
 * @description:
 * @author: Ding Yawu
 * @create: 2022/1/29 17:52
 */
public class SumTask extends RecursiveTask<Long> {

    static final int THREADSHOLD = 500;
    long[] arrays;
    int start;
    int end;

    public SumTask(long[] arrays, int start, int end){
        this.arrays = arrays;
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        if (end - start <= THREADSHOLD){
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum = sum + this.arrays[i];
                try{
                    Thread.sleep(2);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return sum;
        }
        int middle = (start + end) / 2;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start,end, start, middle, middle, end));
        SumTask subTask1 = new SumTask(this.arrays, start, middle);
        SumTask subtask2 = new SumTask(this.arrays, middle, end);
        invokeAll(subTask1, subtask2);
        Long subResult1 = subTask1.join();
        Long subResult2 = subtask2.join();
        long result = subResult1 + subResult2;
        System.out.println("result = " + subResult1 + " + " + subResult2 + " ==> " + result);
        return result;
    }
}
