package thread.chapter27.ActiveObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 4:56 PM 8/24/19
 */
public class OrderServiceProxy implements OrderService {
    private final OrderService orderService;
    private final ActiveMessageQueue activeMessageQueue;

    public OrderServiceProxy(OrderService orderService, ActiveMessageQueue activeMessageQueue) {
        this.orderService = orderService;
        this.activeMessageQueue = activeMessageQueue;
    }

    @Override
    public Future<String> findOrderDetails(long orderId) {
        final ActiveFuture<String> activeFuture = new ActiveFuture<>();

        Map<String, Object> params = new HashMap<>(4);
        params.put("orderId",orderId);
        params.put("activeFuture",activeFuture);

        MethodMessage methodMessage = new FindOrderDetailsMessage(params,orderService);

        activeMessageQueue.offer(methodMessage);
        return  activeFuture;
    }

    @Override
    public void order(String account, long orderId) {
        Map<String, Object> params = new HashMap<>(3);

        params.put("account",account);

        params.put("orderId",orderId);

        MethodMessage methodMessage = new OrderMessage(params,orderService);

        activeMessageQueue.offer(methodMessage);
    }
}
