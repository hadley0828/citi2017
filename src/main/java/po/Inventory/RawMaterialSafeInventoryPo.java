package po.Inventory;

/**
 * Created by 费慧通 on 2017/9/9.
 *
 * 原材料安全库存量表
 */
public class RawMaterialSafeInventoryPo {
    private String company_id;      //公司id
    private String raw_material_variety;     //原材料种类
    private int safe_inventory;     //安全库存量

    public RawMaterialSafeInventoryPo() {
    }

    public RawMaterialSafeInventoryPo(String company_id, String raw_material_variety, int safe_inventory){
        this.company_id = company_id;
        this.raw_material_variety = raw_material_variety;
        this.safe_inventory = safe_inventory;
    }

    public String getCompany_id() {
        return company_id;
    }

    public String getRaw_material_variety() {
        return raw_material_variety;
    }

    public int getSafe_inventory() {
        return safe_inventory;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public void setRaw_material_variety(String raw_material_variety) {
        this.raw_material_variety = raw_material_variety;
    }

    public void setSafe_inventory(int safe_inventory) {
        this.safe_inventory = safe_inventory;
    }
}
