package po.Inventory;

/**
 * Created by loohaze on 2017/9/5 下午9:13
 *
 * 生产商原材料库存PO
 *
 * prID -->
 */
public class ProducerRawMaterialInventoryPO{

    private String prID;

    private String companyID;

    public ProducerRawMaterialInventoryPO(String prID, String companyID) {
        this.prID = prID;
        this.companyID = companyID;
    }

    public ProducerRawMaterialInventoryPO() {
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getPrID() {
        return prID;
    }

    public void setPrID(String prID) {
        this.prID = prID;
    }
}
