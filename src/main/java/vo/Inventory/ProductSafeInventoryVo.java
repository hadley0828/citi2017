package vo.Inventory;

/**
 * Created by 费慧通 on 2017/9/9.
 *
 * 产品安全库存量表格的元组
 */
public class ProductSafeInventoryVo {
    private String product_variety;     //产品种类
    private int safe_inventory;     //安全库存量

    public ProductSafeInventoryVo(String product_variety, int safe_inventory){
        this.product_variety = product_variety;
        this.safe_inventory = safe_inventory;
    }

    public String getProduct_variety() {
        return product_variety;
    }

    public int getSafe_inventory() {
        return safe_inventory;
    }

    public void setProduct_variety(String product_variety) {
        this.product_variety = product_variety;
    }

    public void setSafe_inventory(int safe_inventory) {
        this.safe_inventory = safe_inventory;
    }
}
