package design_mode.singleton.code1;

/**
 * @author yujh
 * @date 2022/11/12 15:26
 */
public class Singleton {
    private static Singleton singleton = new Singleton();
    private Singleton() {
        System.out.println("生成了一个实例");
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}
