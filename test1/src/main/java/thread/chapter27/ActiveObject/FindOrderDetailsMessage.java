package thread.chapter27.ActiveObject;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 5:04 PM 8/24/19
 */
public class FindOrderDetailsMessage extends MethodMessage {
    public FindOrderDetailsMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        Future<String> realFuture = orderService.findOrderDetails((Long)params.get("orderId"));

        ActiveFuture<String> activeFuture = (ActiveFuture<String>) params.get("activeFuture");


        try {
            String result = realFuture.get();
            activeFuture.finish(result);
        } catch (InterruptedException e) {
          activeFuture.finish(null);
        }
    }
}
