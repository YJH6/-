package design_mode.abstract_factory.listfactory;

import design_mode.abstract_factory.factory.Factory;
import design_mode.abstract_factory.factory.Link;
import design_mode.abstract_factory.factory.Page;
import design_mode.abstract_factory.factory.Tray;

/**
 * ListFactory实现了Factory类的抽象方法，当然各个方法内部只是分别简单的new出了对应的实例
 * 根据实际需要，这里可能需要用prototype模式来进行clone
 * @author yujh
 * @date 2022/11/21 11:17
 */
public class ListFactory extends Factory {
    @Override
    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }

    @Override
    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}
