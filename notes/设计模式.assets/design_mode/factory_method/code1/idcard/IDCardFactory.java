package design_mode.factory_method.code1.idcard;

import design_mode.factory_method.code1.framework.Factory;
import design_mode.factory_method.code1.framework.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yujh
 * @date 2022/11/11 9:45
 */
public class IDCardFactory extends Factory {
    private List<String> owners = new ArrayList();
    @Override
    protected Product createProduct(String owner) {
        IDCard idCard = new IDCard(owner);
        return idCard;
    }

    @Override
    protected void registerProduct(Product product) {
        owners.add(((IDCard)product).getOwner());
    }

    public List getOwners(){
        return owners;
    }
}
