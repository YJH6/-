package design_mode.abstract_factory.listfactory;

import design_mode.abstract_factory.factory.Item;
import design_mode.abstract_factory.factory.Page;

/**
 * @author yujh
 * @date 2022/11/21 11:18
 */
public class ListPage extends Page {
    public ListPage(String title, String author) {
        super(title, author);
    }

    @Override
    public void add(Item item) {
        super.add(item);
    }

    @Override
    public void output() {
        super.output();
    }

    @Override
    public String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<html><head><title>" + title + "</title></head>\n");
        buffer.append("<body>\n");
        buffer.append("<h1>" + title + "</h1>\n");
        buffer.append("<ul>\n");
        for (Object o : content) {
            Item item = (Item) o;
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("<hr><address>" + author + "</address>");
        buffer.append("</body></html>\n");
        return buffer.toString();
    }
}
