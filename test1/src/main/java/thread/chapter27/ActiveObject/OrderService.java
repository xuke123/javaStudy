package thread.chapter27.ActiveObject;

import java.util.concurrent.Future;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 4:44 PM 8/24/19
 */
public interface OrderService {

    Future<String> findOrderDetails(long orderId);

    void order(String account,long orderId);
}
