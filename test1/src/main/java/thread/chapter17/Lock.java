package thread.chapter17;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 9:47 PM 8/4/19
 */
public interface Lock {
    void lock() throws InterruptedException;

    void unlock();
}
