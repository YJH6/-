package design_mode.singleton.code1;

/**
 * @author yujh
 * @date 2022/11/12 15:29
 */
public class Main {
    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getSingleton();
        Singleton singleton2 = Singleton.getSingleton();
        System.out.println(singleton1);
        System.out.println(singleton2);
        System.out.println(singleton2 == singleton1);
    }
}
