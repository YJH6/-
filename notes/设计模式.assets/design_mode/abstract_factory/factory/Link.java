package design_mode.abstract_factory.factory;

/**
 * Link类是抽象地表示HTML的超链接的类
 * url字段中保存的是超链接所指向的地址
 * 由于Link类中没有实现父类（Item类）的抽象方法（makeHTML），因此它也是抽象类
 * @author yujh
 * @date 2022/11/21 11:16
 */
public abstract class Link extends Item{
    protected String url;

    public Link(String caption, String url) {
        super(caption);
        this.url = url;
    }
}
