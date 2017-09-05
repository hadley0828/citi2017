package po.Inventory;

/**
 * Created by loohaze on 2017/9/5 下午9:13
 *
 * 生产商原材料库存PO
 *
 * prID -->
 */
public class ProducerRawMaterialInventoryPO extends InventorySuperClass{

    private String prID;

    public ProducerRawMaterialInventoryPO(String prID) {
        this.prID = prID;
    }

    public ProducerRawMaterialInventoryPO() {
    }

    public String getPrID() {
        return prID;
    }

    public void setPrID(String prID) {
        this.prID = prID;
    }
}
