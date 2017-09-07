package po.InventoryList;

/**
 * Created by loohaze on 2017/9/5 下午9:33
 *
 * 生产商原材料库存详细条目PO
 * prID --> ProduceRawMaterialInventoryPO.prID
 * rawMaterialVariety --> 原材料种类
 * inputNum --> 收入数量
 * inputAccount --> 收入金额
 * usedNum --> 使用数量
 * usedAccount --> 使用金额
 * balanceNum --> 结存数量
 * balanceAccount --> 结存金额
 */
public class ProducerRawMaterialListPO extends  InventoryListSuperClass{

    private String prID;

    private String rawMaterialVariety;

    private int inputNum;

    private double inputAccount;

    private int usedNum;

    private double usedAccount;

    private int balanceNum;

    private double balanceAccount;

    public ProducerRawMaterialListPO(String prID, String rawMaterialVariety, int inputNum, double inputAccount, int usedNum, double usedAccount, int balanceNum, double balanceAccount) {
        this.prID = prID;
        this.rawMaterialVariety = rawMaterialVariety;
        this.inputNum = inputNum;
        this.inputAccount = inputAccount;
        this.usedNum = usedNum;
        this.usedAccount = usedAccount;
        this.balanceNum = balanceNum;
        this.balanceAccount = balanceAccount;
    }

    public ProducerRawMaterialListPO() {
    }

    public String getPrID() {
        return prID;
    }

    public void setPrID(String prID) {
        this.prID = prID;
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

    public int getUsedNum() {
        return usedNum;
    }

    public void setUsedNum(int usedNum) {
        this.usedNum = usedNum;
    }

    public double getUsedAccount() {
        return usedAccount;
    }

    public void setUsedAccount(double usedAccount) {
        this.usedAccount = usedAccount;
    }

    public int getBalanceNum() {
        return balanceNum;
    }

    public void setBalanceNum(int balanceNum) {
        this.balanceNum = balanceNum;
    }

    public double getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(double balanceAccount) {
        this.balanceAccount = balanceAccount;
    }
}
