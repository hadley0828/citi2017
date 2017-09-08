package po.Inventory;

/**
 * Created by loohaze on 2017/9/5 下午8:49
 *
 * 供应商原材料库存PO
 * sID -->
 */
public class SupplierInventoryPO {

    private String sID;

    private String companyID;

    public SupplierInventoryPO() {
    }

    public SupplierInventoryPO(String sID, String companyID) {
        this.sID = sID;
        this.companyID = companyID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

}
