package design_mode.prototype.code1.framework;

import java.util.HashMap;

/**
 * @author yujh
 * @date 2022/11/12 16:52
 */
public class Manager {
    private HashMap showcase = new HashMap();

    public void register(String name, Product proto) {
        showcase.put(name, proto);
    }

    public Product create(String name) {
        Product product = (Product) showcase.get(name);
        return product.createClone();
    }
}
