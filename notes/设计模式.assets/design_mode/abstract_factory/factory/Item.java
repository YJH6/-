package design_mode.abstract_factory.factory;
/**
 * Item类是Link类和Tray类的父类（Item有“项目”的意思）。这样，Link类和Tray类就具有可替换性了
 * caption字段表示项目的标题
 * makeHTML()是抽象方法，需要子类来实现这个方法。该方法应该返回HTML文件的内容
 * @author yujh
 * @date 2022/11/21 11:16
 */
public abstract class Item {
    protected String caption;

    public Item(String caption) {
        this.caption = caption;
    }

    public abstract String makeHTML();
}
