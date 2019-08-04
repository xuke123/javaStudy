package functionInterface;

public class FunctionInteface {
    public String printString(String ab){
        System.out.println("str:"+ab);
        return ab;
    }

    public String printInt(int a){
        System.out.println("int:"+a);
        return String.valueOf(a);
    }

    public static void main(String[] args) {
        FuncInteface<String,String> fi1= new
                FunctionInteface()::printString;

        fi1.print("hello");

        FuncInteface<String,Integer> fi2 = new FunctionInteface()::printInt;

        fi2.print(2);
    }
}
