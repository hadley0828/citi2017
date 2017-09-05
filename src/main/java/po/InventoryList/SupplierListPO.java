package po.InventoryList;

/**
 * Created by loohaze on 2017/9/5 下午9:26
 *
 * 供应商原材料库存详细条目PO
 * sID --> 同SupplierInventoryPO.sID
 * rawMaterialVariety --> 原材料种类
 * inputNum --> 收入数量
 * inputAccount --> 收入金额
 * outputNum --> 发出数量
 * outputAccount --> 发出金额
 * balanceNum --> 结存数量
 * balanceAccount --> 结存金额
 */
public class SupplierListPO extends InventoryListSuperClass{

    private String sID;

    private String rawMaterialVariety;

    private int inputNum;

    private int inputAccount;

    private int outputNum;

    private int outputAccount;

    private int balanceNum;

    private int balanceAccount;

    public SupplierListPO(String sID, String rawMaterialVariety, int inputNum, int inputAccount, int outputNum, int outputAccount, int balanceNum, int balanceAccount) {
        this.sID = sID;
        this.rawMaterialVariety = rawMaterialVariety;
        this.inputNum = inputNum;
        this.inputAccount = inputAccount;
        this.outputNum = outputNum;
        this.outputAccount = outputAccount;
        this.balanceNum = balanceNum;
        this.balanceAccount = balanceAccount;
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
