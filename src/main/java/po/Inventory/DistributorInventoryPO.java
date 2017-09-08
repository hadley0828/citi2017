package po.Inventory;

/**
 * Created by loohaze on 2017/9/5 下午9:17
 *
 * 分销商产品库存PO
 * dID -->
 */
public class DistributorInventoryPO {

    private String dID;

    private String companyID;

    public DistributorInventoryPO(String dID, String companyID) {
        this.dID = dID;
        this.companyID = companyID;
    }

    public DistributorInventoryPO() {
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getdID() {
        return dID;
    }

    public void setdID(String dId) {
        this.dID = dId;
    }
}
