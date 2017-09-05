package po.InventoryList;

/**
 * Created by loohaze on 2017/9/5 下午9:36
 *
 * 生产商产品库存详细条目PO
 * ppID --> ProducerProductInventoryPO.ppID
 * rawMaterialVariety --> 原材料种类
 * produceNum --> 收入数量
 * produceAccount --> 收入金额
 * outputNum --> 发出数量
 * outputAccount --> 发出金额
 * balanceNum --> 结存数量
 * balanceAccount --> 结存金额
 */
public class ProducerProductListPO extends InventoryListSuperClass{

    private String ppID;

    private String productVariety;

    private int produceNum;

    private int produceAccount;

    private int outputNum;

    private int outputAccount;

    private int balanceNum;

    private int balanceAccount;

    public ProducerProductListPO(String ppID, String productVariety, int produceNum, int produceAccount, int outputNum, int outputAccount, int balanceNum, int balanceAccount) {
        this.ppID = ppID;
        this.productVariety = productVariety;
        this.produceNum = produceNum;
        this.produceAccount = produceAccount;
        this.outputNum = outputNum;
        this.outputAccount = outputAccount;
        this.balanceNum = balanceNum;
        this.balanceAccount = balanceAccount;
    }

    public ProducerProductListPO() {
    }

    public String getPpID() {
        return ppID;
    }

    public void setPpID(String ppID) {
        this.ppID = ppID;
    }

    public String getProductVariety() {
        return productVariety;
    }

    public void setProductVariety(String productVariety) {
        this.productVariety = productVariety;
    }

    public int getProduceNum() {
        return produceNum;
    }

    public void setProduceNum(int produceNum) {
        this.produceNum = produceNum;
    }

    public int getProduceAccount() {
        return produceAccount;
    }

    public void setProduceAccount(int produceAccount) {
        this.produceAccount = produceAccount;
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

