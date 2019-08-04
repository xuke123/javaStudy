package thread.chapter14;

/**
 * 懒汉+ 同步方法
 *
 * @author kekex.xu
 * @since 2:41 PM 8/4/19
 */
public class Singleton3 {
    private byte[] data = new byte[1024];

    private static Singleton3 instance = null;

    private Singleton3(){}

    public static synchronized Singleton3 getInstance(){
        if(null == instance){
            instance = new Singleton3();
        }

        return instance;
    }
}
