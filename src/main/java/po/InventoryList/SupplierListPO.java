package po.InventoryList;

/**
 * Created by loohaze on 2017/9/5 下午9:26
 *
 * 供应商原材料库存详细条目PO
 * sID --> 同SupplierInventoryPO.sID
 * rawMaterialVariety --> 原材料种类
 * inputNum --> 收入数量
 * inputUnitPrice --> 收入单价
 * inputAccount --> 收入金额
 * outputNum --> 发出数量
 * outputUnitPrice --> 发出单价
 * outputAccount --> 发出金额
 * balanceNum --> 结存数量
 */
public class SupplierListPO extends InventoryListSuperClass{

    private String sID;

    private String rawMaterialVariety;

    private int inputNum;

    private double inputUnitPrice;

    private double inputAccount;

    private int outputNum;

    private double outputUnitPrice;

    private double outputAccount;

    private int balanceNum;

    public SupplierListPO(String sID, String rawMaterialVariety, int inputNum, double inputUnitPrice, double inputAccount, int outputNum, double outputUnitPrice, double outputAccount, int balanceNum) {
        this.sID = sID;
        this.rawMaterialVariety = rawMaterialVariety;
        this.inputNum = inputNum;
        this.inputUnitPrice = inputUnitPrice;
        this.inputAccount = inputAccount;
        this.outputNum = outputNum;
        this.outputUnitPrice = outputUnitPrice;
        this.outputAccount = outputAccount;
        this.balanceNum = balanceNum;
    }

    public SupplierListPO() {
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public String getRawMaterialVariety() {
        return rawMaterialVariety;
    }

    public void setRawMaterialVariety(String rawMaterialVariety) {
        this.rawMaterialVariety = rawMaterialVariety;
    }

    public int getInputNum() {
        return inputNum;
    }

    public void setInputNum(int inputNum) {
        this.inputNum = inputNum;
    }

    public double getInputAccount() {
        return inputAccount;
    }

    public void setInputAccount(double inputAccount) {
        this.inputAccount = inputAccount;
    }

    public int getOutputNum() {
        return outputNum;
    }

    public void setOutputNum(int outputNum) {
        this.outputNum = outputNum;
    }

    public double getOutputAccount() {
        return outputAccount;
    }

    public void setOutputAccount(double outputAccount) {
        this.outputAccount = outputAccount;
    }

    public int getBalanceNum() {
        return balanceNum;
    }

    public void setBalanceNum(int balanceNum) {
        this.balanceNum = balanceNum;
    }

    public double getInputUnitPrice() {
        return inputUnitPrice;
    }

    public void setInputUnitPrice(double inputUnitPrice) {
        this.inputUnitPrice = inputUnitPrice;
    }

    public double getOutputUnitPrice() {
        return outputUnitPrice;
    }

    public void setOutputUnitPrice(double outputUnitPrice) {
        this.outputUnitPrice = outputUnitPrice;
    }
}
