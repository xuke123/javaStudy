package thread.chapter14;

import java.security.Signature;

/**
 * 饿汉模式: 长时间占用资源 ClassLoader时会直接创建
 *
 * @author kekex.xu
 * @since 2:32 PM 8/4/19
 */
public final class Singleton1 {
    private byte[] data = new byte[1024];

    private static Singleton1 instance = new Singleton1();

    private Singleton1(){};

    public static Singleton1 getInstance(){
        return instance;
    }
}
