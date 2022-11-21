package design_mode.builder.code1;


/**
 * @author yujh
 * @date 2022/11/15 20:19
 */
public class Director {
    private Builder builder;
    public Director(Builder builder) {
        this.builder = builder;
    }

    //使用Builder类中声明的方法来编写文档
    public void construct() {
        builder.makeTitle("Greeting");
        builder.makeString("从早上至下午");
        builder.makeItems(new String[]{
                "早上好。",
                "下午好。"
        });
        builder.makeString("晚上");
        builder.makeItems(new String[]{"晚上好。","晚安。","再见。"});
        builder.close();
    }
}
