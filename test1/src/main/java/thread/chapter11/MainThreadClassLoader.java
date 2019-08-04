package thread.chapter11;

import static java.lang.Thread.currentThread;
/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 10:49 AM 8/4/19
 */
public class MainThreadClassLoader {
    public static void main(String[] args){
        System.out.println(currentThread().getContextClassLoader());
    }
}
