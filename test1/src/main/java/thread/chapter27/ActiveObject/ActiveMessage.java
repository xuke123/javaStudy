package thread.chapter27.ActiveObject;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 添加描述
 *
 * @author kekex.xu
 * @since 8:53 PM 8/24/19
 */
public class ActiveMessage {
    private final Object[] objects;

    private final Method method;

    private final ActiveFuture<Object> future;

    private final Object service;

    public ActiveMessage(Builder builder) {
        this.objects= builder.objects;
        this.method = builder.method;
        this.future = builder.future;
        this.service = builder.service;
    }

    public void execute(){
            Object result = method.invoke(service, Object);
        try {
            if(future != null){
                Future<?> realFuture = (Future<?>) result;
                Object realResult = realFuture.get();
                future.finish(realResult);
            }
        } catch (Exception e) {
            if(future != null){
                future.finish(null);
            }
        }
    }

    static class Builder {
        private [] objects;
        private Method method;
        private ActiveFuture<Object> future;
        private Object service;

        public Builder userMethod(Method method){
            this.method = method;
            return this;
        }

        public Builder withObjects(Object[] objects){
            this.objects = objects;
            return this;
        }

        public  Builder forService(Object service){
            this.service = service;
            return this;
        }

        public ActiveMessage build(){
            return new ActiveMessage(this);
        }
    }
}
