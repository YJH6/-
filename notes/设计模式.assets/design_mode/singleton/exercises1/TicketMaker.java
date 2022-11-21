package design_mode.singleton.exercises1;

/**
 * @author yujh
 * @date 2022/11/12 15:37
 */
public class TicketMaker {
    private int ticket = 1000;
    private static TicketMaker ticketMaker = new TicketMaker();
    private TicketMaker(){
        System.out.println("生成了一个实例");
    }

    public static TicketMaker getInstance() {
        return ticketMaker;
    }
    public synchronized int getNextTicketNumber() {
        return ticket++;
    }
}
