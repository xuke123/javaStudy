package thread;

import thread.TemplateMethod;

public class Main {
    public static void main(String[] args) {
        TemplateMethod method0 = new TemplateMethod();
        method0.print("hello");
        TemplateMethod method1 = new TemplateMethod(){
            @Override
            protected void overridedPrint(String msg){
                System.out.println("method1:"+msg);
            }
        };
        method1.print("hello");
    }
}
