package callback;

/**
 * 异步任务测试类，体现的是依赖倒置的设计原则
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-08-24 8:07
 */
public class TaskCallableTest {
    public static void main(String[] args){
        TaskCallable<TaskResult> taskCallable = new TaskHandler();
        TaskExecutor taskExecutor = new TaskExecutor(taskCallable, "测试回调任务");
        new Thread(taskExecutor).start();
    }
}
