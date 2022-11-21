package design_mode.factory_method.code1;

import design_mode.factory_method.code1.framework.Factory;
import design_mode.factory_method.code1.framework.Product;
import design_mode.factory_method.code1.idcard.IDCardFactory;

/**
 * @author yujh
 * @date 2022/11/11 9:48
 */
public class Main {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("yujh");
        Product card2 = factory.create("liuzw");
        Product card3 = factory.create("pengy");
        Product card4 = factory.create("lij");
        card1.use();
        card2.use();
        card3.use();
        card4.use();
    }
}
