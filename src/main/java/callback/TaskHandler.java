package callback;

/**
 * 创建回调接口的实现类
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-08-24 8:06
 */
public class TaskHandler implements TaskCallable<TaskResult> {
    @Override
    public TaskResult callable(TaskResult taskResult) {
        //TODO 拿到结果数据后进一步处理
        System.out.println(taskResult.toString());
        return taskResult;
    }
}
