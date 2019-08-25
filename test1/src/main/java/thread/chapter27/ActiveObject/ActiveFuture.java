package thread.chapter27.ActiveObject;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 4:58 PM 8/24/19
 */
public class ActiveFuture<T>  extends FutureTask<T>{
    public ActiveFuture(Callable<T> callable) {
        super(callable);
    }

    public void finish(T result){
        System.out.println(result);
    }
}
