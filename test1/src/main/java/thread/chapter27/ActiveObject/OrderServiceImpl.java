package thread.chapter27.ActiveObject;

import java.util.concurrent.Future;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 4:53 PM 8/24/19
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public Future<String> findOrderDetails(long orderId) {
        return null;
    }

    @Override
    public void order(String account, long orderId) {

    }
}
