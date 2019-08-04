package thread.chapter2;

public class TemplateMethod {
    public  final void print(String msg){
        System.out.println("this is a print test");
        overridedPrint(msg);
    }

    protected void overridedPrint(String msg){
        System.out.println("base:"+msg);
    }
}

