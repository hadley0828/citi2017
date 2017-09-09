package po.Inventory;

/**
 * Created by 费慧通 on 2017/9/9.
 *
 * 产品安全库存量表
 */
public class ProductSafeInventoryPo {
    private String company_id;      //公司id
    private String product_variety;     //产品种类
    private int safe_inventory;     //安全库存量

    public ProductSafeInventoryPo(String company_id, String product_variety, int safe_inventory){
        this.company_id = company_id;
        this.product_variety = product_variety;
        this.safe_inventory = safe_inventory;
    }

    public String getCompany_id() {
        return company_id;
    }

    public String getProduct_variety() {
        return product_variety;
    }

    public int getSafe_inventory() {
        return safe_inventory;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public void setProduct_variety(String product_variety) {
        this.product_variety = product_variety;
    }

    public void setSafe_inventory(int safe_inventory) {
        this.safe_inventory = safe_inventory;
    }
}
