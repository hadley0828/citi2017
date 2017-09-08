package po.Inventory;

/**
 * Created by loohaze on 2017/9/5 下午9:15
 *
 * 生产商产品库存PO
 * ppID -->
 */
public class ProducerProductInventoryPO{

    private String ppID;

    private String companyID;

    public ProducerProductInventoryPO(String ppID, String companyID) {
        this.ppID = ppID;
        this.companyID = companyID;
    }

    public ProducerProductInventoryPO() {
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getPpID() {
        return ppID;
    }

    public void setPpID(String ppID) {
        this.ppID = ppID;
    }
}
