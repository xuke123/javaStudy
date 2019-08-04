package thread;

public class TicketWindow extends Thread {

    private final String name;

    private static final int Max = 50;

    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while(index <= Max) {
            System.out.println(name + "号码："+ index++);
        }
    }

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("二号");
        ticketWindow2.start();
    }
}
