package design_mode.abstract_factory.factory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Page类是抽象地表示HTML页面的类。如果将Link和Tray比喻成抽象的“零件”，那么Page类就是抽象的“产品”。
 * title和author分别是表示页面标题和页面作者的字段。作者名字通过参数传递给Page类的构造函数。
 * @author yujh
 * @date 2022/11/21 11:17
 */
public abstract class Page {
    protected String title;
    protected String author;
    protected ArrayList content = new ArrayList();
    public Page(String title, String author) {
        this.title = title;
        this.author = author;
    }
    public void add(Item item) {
        content.add(item);
    }
    public abstract String makeHTML();
    public void output() {
        try {
            String fileName = title + ".html";
            FileWriter writer = new FileWriter(fileName);
            writer.write(this.makeHTML());
            writer.close();
            System.out.println(fileName + "编写完成");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
