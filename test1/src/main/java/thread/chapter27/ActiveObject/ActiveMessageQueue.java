package thread.chapter27.ActiveObject;

import java.util.LinkedList;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 5:24 PM 8/24/19
 */
public class ActiveMessageQueue {
    private final LinkedList<MethodMessage> messages = new LinkedList<>();

    public ActiveMessageQueue(){
        new ActiveDaemonThread(this).start();
    }

    public void offer(MethodMessage methodMessage){
        synchronized (this){
            messages.addLast(methodMessage);
            this.notify();
        }
    }

    protected  MethodMessage take(){
        synchronized (this) {
            while (messages.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return messages.removeFirst();
        }
    }

}
