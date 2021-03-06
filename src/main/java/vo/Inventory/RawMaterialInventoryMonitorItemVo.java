package vo.Inventory;

/**
 * Created by 费慧通 on 2017/9/8.
 *
 * 原材料库存监控表格的元祖
 */
public class RawMaterialInventoryMonitorItemVo {
    String raw_material_variety;     //产品种类
    int inventory;      //库存量
    int safe_inventory;     //安全库存量
    String punctual_delivery_rate;      //准时交货率(百分比形式)
    String refund_rate;     //退货率（百分比形式）

    public RawMaterialInventoryMonitorItemVo(String raw_material_variety, int inventory, int safe_inventory, String punctual_delivery_rate, String refund_rate){
        this.raw_material_variety = raw_material_variety;
        this.inventory = inventory;
        this.safe_inventory = safe_inventory;
        this.punctual_delivery_rate = punctual_delivery_rate;
        this.refund_rate = refund_rate;
    }

    public String getRaw_material_variety() {
        return raw_material_variety;
    }

    public void setRaw_material_variety(String raw_material_variety) {
        this.raw_material_variety = raw_material_variety;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getSafe_inventory() {
        return safe_inventory;
    }

    public void setSafe_inventory(int safe_inventory) {
        this.safe_inventory = safe_inventory;
    }

    public String getPunctual_delivery_rate() {
        return punctual_delivery_rate;
    }

    public void setPunctual_delivery_rate(String punctual_delivery_rate) {
        this.punctual_delivery_rate = punctual_delivery_rate;
    }

    public String getRefund_rate() {
        return refund_rate;
    }

    public void setRefund_rate(String refund_rate) {
        this.refund_rate = refund_rate;
    }
}
