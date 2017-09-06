package po.Inventory;

/**
 * Created by loohaze on 2017/9/5 下午9:17
 *
 * 分销商产品库存PO
 * dID -->
 */
public class DistributorInventoryPO extends InventorySuperClass {

    private String dID;

    public DistributorInventoryPO(String dId) {
        this.dID = dId;
    }

    public DistributorInventoryPO() {
    }

    public String getdID() {
        return dID;
    }

    public void setdID(String dId) {
        this.dID = dId;
    }
}
