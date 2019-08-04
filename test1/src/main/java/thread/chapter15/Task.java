package thread.chapter15;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 3:15 PM 8/4/19
 */
@FunctionalInterface
public interface Task<T> {
    T call();
}
