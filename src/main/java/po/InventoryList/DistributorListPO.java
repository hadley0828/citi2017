package po.InventoryList;

/**
 * Created by loohaze on 2017/9/5 下午9:38
 *
 * 分销商产品库存详细条目PO
 * dID --> DistributorInventoryPO.dID
 * productVariety --> 原材料种类
 * inputNum --> 收入数量
 * inputAccount --> 收入金额
 * outputNum --> 发出数量
 * outputAccount --> 发出金额
 * balanceNum --> 结存数量
 * balanceAccount --> 结存金额
 */
public class DistributorListPO extends InventoryListSuperClass{

    private String dID;

    private String productVariety;

    private int inputNum;

    private int inputAccount;

    private int outputNum;

    private int outputAccount;

    private int balanceNum;

    private int balanceAccount;

    public DistributorListPO(String dID, String productVariety, int inputNum, int inputAccount, int outputNum, int outputAccount, int balanceNum, int balanceAccount) {
        this.dID = dID;
        this.productVariety = productVariety;
        this.inputNum = inputNum;
        this.inputAccount = inputAccount;
        this.outputNum = outputNum;
        this.outputAccount = outputAccount;
        this.balanceNum = balanceNum;
        this.balanceAccount = balanceAccount;
    }

    public DistributorListPO() {
    }

    public String getdID() {
        return dID;
    }

    public void setdID(String dID) {
        this.dID = dID;
    }

    public String getProductVariety() {
        return productVariety;
    }

    public void setProductVariety(String productVariety) {
        this.productVariety = productVariety;
    }

    public int getInputNum() {
        return inputNum;
    }

    public void setInputNum(int inputNum) {
        this.inputNum = inputNum;
    }

    public int getInputAccount() {
        return inputAccount;
    }

    public void setInputAccount(int inputAccount) {
        this.inputAccount = inputAccount;
    }

    public int getOutputNum() {
        return outputNum;
    }

    public void setOutputNum(int outputNum) {
        this.outputNum = outputNum;
    }

    public int getOutputAccount() {
        return outputAccount;
    }

    public void setOutputAccount(int outputAccount) {
        this.outputAccount = outputAccount;
    }

    public int getBalanceNum() {
        return balanceNum;
    }

    public void setBalanceNum(int balanceNum) {
        this.balanceNum = balanceNum;
    }

    public int getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(int balanceAccount) {
        this.balanceAccount = balanceAccount;
    }
}
