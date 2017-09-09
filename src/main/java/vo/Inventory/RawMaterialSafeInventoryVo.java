package vo.Inventory;

/**
 * Created by 费慧通 on 2017/9/9.
 *
 * 原材料安全库存量表格的元组
 */
public class RawMaterialSafeInventoryVo {
    private String raw_material_variety;       //原材料种类
    private int safe_inventory;     //安全库存量

    public RawMaterialSafeInventoryVo(String raw_material_variety, int safe_inventory){
        this.raw_material_variety = raw_material_variety;
        this.safe_inventory = safe_inventory;
    }

    public String getRaw_material_variety() {
        return raw_material_variety;
    }

    public int getSafe_inventory() {
        return safe_inventory;
    }

    public void setRaw_material_variety(String raw_material_variety) {
        this.raw_material_variety = raw_material_variety;
    }

    public void setSafe_inventory(int safe_inventory) {
        this.safe_inventory = safe_inventory;
    }
}
