package design_mode.adapter.code2;

/**
 * @author yujh
 * @date 2022/11/10 15:17
 */
public class PrintBanner extends Print {
    private Banner banner;
    public PrintBanner(String string) {
        banner = new Banner(string);
    }

    @Override
    public void printWeak() {
        banner.showWithParen();
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}
