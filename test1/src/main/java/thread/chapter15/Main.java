package thread.chapter15;

import java.util.concurrent.TimeUnit;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 3:33 PM 8/4/19
 */
public class Main {
    public static void main(String[] args) {
        final TaskLifecycle<String> lifecycle  = new TaskLifecycle.EmptyLifecycle<String>(){
            @Override
            public void onFinish(Thread thread,String result){
                System.out.println("The result is "+ result);
            }
        };

        Observable obhread = new ObservableThread<>(lifecycle,  ()->{
            try{
                TimeUnit.SECONDS.sleep(10);
            }catch (InterruptedException e){
                System.out.println(e);
            }
            System.out.println("finish");
            return "hello Observer";
        });
        obhread.start();
    }


}
