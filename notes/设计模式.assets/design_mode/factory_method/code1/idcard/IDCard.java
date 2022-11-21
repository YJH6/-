package design_mode.factory_method.code1.idcard;

import design_mode.factory_method.code1.framework.Product;

/**
 * @author yujh
 * @date 2022/11/11 9:41
 */
public class IDCard extends Product {
    private String owner;
    IDCard(String owner) {
        System.out.println("制作" + owner + "的ID卡。");
        this.owner = owner;
    }
    @Override
    public void use() {
        System.out.println("使用" + owner + "的ID卡。");
    }

    public String getOwner() {
        return owner;
    }
}
