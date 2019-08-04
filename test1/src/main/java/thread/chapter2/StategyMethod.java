package thread.chapter2;

import functionInterface.FuncInteface;
import functionInterface.FunctionInteface;

public class StategyMethod {

    public <T,T0>T print(FuncInteface<T,T0> method, T0 param){
        System.out.println(param.getClass());
        return method.print(param);
    }

    public static void main(String[] args) {
        StategyMethod stringIntegerStategyMethod
                = new StategyMethod();

        stringIntegerStategyMethod.print(new FunctionInteface()::printInt,2);
    }
}
