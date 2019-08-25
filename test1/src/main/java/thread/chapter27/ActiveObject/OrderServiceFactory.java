package thread.chapter27.ActiveObject;

/**
 * orderService 工厂类
 *
 * @author kekex.xu
 * @since 8:33 PM 8/24/19
 */
public class OrderServiceFactory {
    private final static ActiveMessageQueue activeMessageQueue = new ActiveMessageQueue();

    private OrderServiceFactory() {
    }

    public static OrderService toActiveObject(OrderService orderService){
        return new OrderServiceProxy(orderService,activeMessageQueue);
    }
}
