package design_mode.builder.code1;

/**
 * 一个声明了编写文档的方法的抽象类
 * @author yujh
 * @date 2022/11/15 20:17
 */
public abstract class Builder {
    public abstract void makeTitle(String title);
    public abstract void makeString(String str);
    public abstract void makeItems(String[] items);
    public abstract void close();
}
