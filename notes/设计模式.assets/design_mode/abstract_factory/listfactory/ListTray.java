package design_mode.abstract_factory.listfactory;

import design_mode.abstract_factory.factory.Item;
import design_mode.abstract_factory.factory.Tray;

import java.util.Iterator;

/**
 * @author yujh
 * @date 2022/11/21 11:17
 */
public class ListTray extends Tray {
    public ListTray(String caption) {
        super(caption);
    }

    @Override
    public void add(Item item) {
        super.add(item);
    }

    @Override
    public String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<li>\n");
        buffer.append(caption + "\n");
        buffer.append("<ul>\n");
        for (Object t : tray) {
            Item item = (Item) t ;
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("</li>\n");
        return buffer.toString();
    }
}
