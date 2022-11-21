package design_mode.abstract_factory.factory;

/**
 * @author yujh
 * @date 2022/11/21 11:16
 */
public abstract class Factory {
    public static Factory getFactory(String className) {
        Factory factory = null;
        try {
            factory = (Factory)Class.forName(className).newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("没有找到" + className + "类。");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return factory;
    }

    public abstract Link createLink(String caption, String url);
    public abstract Tray createTray(String caption);
    public abstract Page createPage(String title, String author);
}
