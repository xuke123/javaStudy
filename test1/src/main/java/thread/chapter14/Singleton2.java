package thread.chapter14;

/**
 * 懒汉模式,非线程安全
 *
 * @author kekex.xu
 * @since 2:37 PM 8/4/19
 */
public final class Singleton2 {
    private byte[] data = new byte[1024];

    private static Singleton2 instance = null;

    private Singleton2(){}

    public static Singleton2 getInstance(){
        if(null == instance){
            instance = new Singleton2();
        }

        return instance;
    }
}
