package design_mode.abstract_factory;

import design_mode.abstract_factory.factory.Factory;
import design_mode.abstract_factory.factory.Link;
import design_mode.abstract_factory.factory.Page;
import design_mode.abstract_factory.factory.Tray;

/**
 * 使用工厂将零件组装成为产品
 * Main类使用抽象工厂生产零件并将零件组装成产品
 * Main类中只引入了factory包，从这一点可以看出，该类并没有使用任何具体零件、产品和工厂。
 * @author yujh
 * @date 2022/11/21 13:39
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main class.name.of.ConcreteFactory");
            System.out.println("Example 1: java Main listfactory.ListFactory");
            System.out.println("Example 1: java Main tablefactory.TableFactory");
            System.exit(0);
        }
        Factory factory = Factory.getFactory(args[0]);
        Link people = factory.createLink("人民日报","http://www.people.com.cn/");
        Link gmw = factory.createLink("光明网","http://www.gmw.cn/");

        Link us_yahoo = factory.createLink("Yahoo","http://www.yahoo.com/");
        Link jp_yahoo = factory.createLink("Yahoo_Japan","http://www.yahoo.co.jp/");
        Link baidu = factory.createLink("Baidu","http://www.baidu.com/");
        Link google = factory.createLink("Google","http://www.google.com/");

        Tray trayNews = factory.createTray("日报");
        trayNews.add(people);
        trayNews.add(gmw);

        Tray trayYahoo = factory.createTray("Yahoo");
        trayYahoo.add(us_yahoo);
        trayYahoo.add(jp_yahoo);

        Tray traySearch = factory.createTray("搜索引擎");
        traySearch.add(trayYahoo);
        traySearch.add(baidu);
        traySearch.add(google);

        Page page = factory.createPage("LinkPage","yujh");
        page.add(trayNews);
        page.add(traySearch);
        page.output();
    }
}
