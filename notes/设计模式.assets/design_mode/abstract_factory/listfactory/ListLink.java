package design_mode.abstract_factory.listfactory;

import design_mode.abstract_factory.factory.Link;

/**
 *
 * @author yujh
 * @date 2022/11/21 11:17
 */
public class ListLink extends Link {
    public ListLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHTML() {
        return "  <li><a href=\"" + url + "\">" + caption + "</a></li>\n";
    }
}
