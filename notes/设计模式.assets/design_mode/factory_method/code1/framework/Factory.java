package design_mode.factory_method.code1.framework;

/**
 * @author yujh
 * @date 2022/11/11 9:38
 */
public abstract class Factory {
    public final Product create(String owner) {
        Product product = createProduct(owner);
        registerProduct(product);
        return product;
    }

    protected abstract Product createProduct(String owner);

    protected abstract void registerProduct(Product product);
}
