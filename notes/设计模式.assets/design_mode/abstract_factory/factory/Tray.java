package design_mode.abstract_factory.factory;

import java.util.ArrayList;

/**
 * Tray类表示的是一个含有多个Link类和Tray类的容器（Tray有托盘的意思）
 * Tray类使用add方法将Link类和Tray类集合在一起。为了表示集合的对象是“Link类和Tray类”，
 * 我们设置add方法的参数为Link类和Tray类的父类Item类。
 * @author yujh
 * @date 2022/11/21 11:17
 */
public abstract class Tray extends Item{
    protected ArrayList tray = new ArrayList();
    public Tray(String caption) {
        super(caption);
    }
    public void add(Item item) {
        tray.add(item);
    }
}
