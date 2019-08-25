package thread.chapter27.ActiveObject;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 5:26 PM 8/24/19
 */
public class ActiveDaemonThread extends Thread {
    private final ActiveMessageQueue queue;

    public ActiveDaemonThread( ActiveMessageQueue queue) {
        super("activeDaemonThread");
        this.queue = queue;
        setDaemon(true);
    }

    @Override
    public void run() {
        for(;;){
            MethodMessage methodMessage = this.queue.take();
            methodMessage.execute();
        }
    }
}
