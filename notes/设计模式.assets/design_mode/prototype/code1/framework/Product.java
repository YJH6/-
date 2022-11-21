package design_mode.prototype.code1.framework;

/**
 * @author yujh
 * @date 2022/11/12 16:50
 */
public interface Product extends Cloneable{
    public abstract void use(String s);
    public abstract Product createClone();
}
