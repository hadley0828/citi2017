package po.Inventory;

/**
 * Created by loohaze on 2017/9/5 下午9:15
 *
 * 生产商产品库存PO
 * ppID -->
 */
public class ProducerProductInventoryPO extends InventorySuperClass{

    private String ppID;

    public ProducerProductInventoryPO(String ppID) {
        this.ppID = ppID;
    }

    public ProducerProductInventoryPO() {
    }

    public String getPpID() {
        return ppID;
    }

    public void setPpID(String ppID) {
        this.ppID = ppID;
    }
}
