package thread.chapter27.ActiveObject;

import java.util.Map;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 5:12 PM 8/24/19
 */
public class OrderMessage extends  MethodMessage {
    public OrderMessage(Map<String, Object> params, OrderService orderService) {
        super(params, orderService);
    }

    @Override
    public void execute() {
        String account = (String) params.get("account");

        long orderId = (long) params.get("orderId");

        orderService.order(account, orderId);
    }
}
