package thread.chapter15;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 3:10 PM 8/4/19
 */
public interface Observable {
    enum Cycle{
        STARTED,RUNNING,DONE,ERROR
    }

    Cycle getCycle();

    void start();

    void interrupt();
}
