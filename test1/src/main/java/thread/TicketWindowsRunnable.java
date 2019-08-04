package thread;

public class TicketWindowsRunnable implements Runnable{
    private int index = 1;

    private final static int Max = 50;

    @Override
    public void run() {
        while (index <=Max){
            System.out.println(Thread.currentThread() + ":"+ index++);

            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                System.out.println("exception:"+ e);
            }
        }
    }

    public static void main(String[] args) {
        final TicketWindowsRunnable task = new TicketWindowsRunnable();

        Thread windows1 = new Thread(task,"一号");
        Thread windows2 = new Thread(task,"二号");

        windows1.start();
        windows2.start();
    }
}
