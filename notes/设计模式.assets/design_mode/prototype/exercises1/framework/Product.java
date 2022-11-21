package design_mode.prototype.exercises1.framework;

/**
 * @author yujh
 * @date 2022/11/12 16:50
 */
public abstract class Product implements Cloneable{
    public abstract void use(String s);
    public Product createClone() {
        try {
            return (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
