package design_mode.prototype.code1;

import design_mode.prototype.code1.framework.Manager;
import design_mode.prototype.code1.framework.Product;

/**
 * @author yujh
 * @date 2022/11/15 16:31
 */
public class Main {
    public static void main(String[] args) {
        //准备
        Manager manager = new Manager();
        UnderlinePen upen = new UnderlinePen('~');
        MessageBox mbox = new MessageBox('*');
        MessageBox sbox = new MessageBox('/');
        manager.register("strong message", upen);
        manager.register("warning box", mbox);
        manager.register("slash box", sbox);

        //生成
        Product p1 = manager.create("strong message");
        p1.use("Hello.");
        Product p2 = manager.create("warning box");
        p2.use("Hello.");
        Product p3 = manager.create("slash box");
        p3.use("Hellp.");
    }
}
