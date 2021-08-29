package callback;

/**
 * 定义回调接口
 * @author dingyawu
 * @version 1.0
 * @date created in 2021-08-24 8:05
 */
public interface TaskCallable<T> {
    T callable(T t);
}