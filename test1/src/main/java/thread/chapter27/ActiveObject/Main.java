package thread.chapter27.ActiveObject;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 8:38 PM 8/24/19
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = OrderServiceFactory.toActiveObject(new OrderServiceImpl());

        orderService.order("hello",453453);

        System.out.println("Return immediately");
    }
}
