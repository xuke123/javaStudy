package thread.chapter27.ActiveObject;

import java.util.Map;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 5:02 PM 8/24/19
 */
public abstract class MethodMessage {
    protected final Map<String,Object> params;

    protected  final OrderService orderService;

    public MethodMessage(Map<String, Object> params, OrderService orderService) {
        this.params = params;
        this.orderService = orderService;
    }

    public abstract void execute();
}
