package po.Inventory;

/**
 * Created by loohaze on 2017/9/5 下午8:49
 *
 * 供应商原材料库存PO
 * sID -->
 */
public class SupplierInventoryPO extends InventorySuperClass{

    private String sID;

    public SupplierInventoryPO() {
    }

    public SupplierInventoryPO(String sID) {
        this.sID = sID;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

}
