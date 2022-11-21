package design_mode.adapter.code1;

/**
 * 使用继承的适配器
 * @author yujh
 * @date 2022/11/10 15:19
 */
public class Main {
    public static void main(String[] args) {
        Print p = new PrintBanner("Hello");
        p.printStrong();
        p.printWeak();
    }
}
