package thread.chapter2;

import java.util.stream.IntStream;

public class Mingming {
    public static void main(String[] args) {
        IntStream.range(0,5).boxed().map(i ->
                new Thread(
                        ()-> System.out.println(Thread.currentThread().getName())
                )).forEach(Thread::start);
    }
}
