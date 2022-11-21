package design_mode.singleton.exercises1;

/**
 * @author yujh
 * @date 2022/11/12 15:38
 */
public class Main {
    public static void main(String[] args) {
        int nextTicketNumber1 = TicketMaker.getInstance().getNextTicketNumber();
        int nextTicketNumber2 = TicketMaker.getInstance().getNextTicketNumber();
        System.out.println(nextTicketNumber1);
        System.out.println(nextTicketNumber2);
    }
}
