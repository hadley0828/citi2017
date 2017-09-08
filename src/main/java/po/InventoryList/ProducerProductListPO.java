package po.InventoryList;

/**
 * Created by loohaze on 2017/9/5 下午9:36
 *
 * 生产商产品库存详细条目PO
 * ppID --> ProducerProductInventoryPO.ppID
 * productVariety --> 产品种类
 * produceNum --> 生产数量
 * produceUnitPrice --> 生产单价
 * produceAccount --> 生产金额
 * outputNum --> 发出数量
 * outputUnitPrice --> 发出单价
 * outputAccount --> 发出金额
 * balanceNum --> 结存数量
 */
public class ProducerProductListPO extends InventoryListSuperClass{

    private String ppID;

    private String productVariety;

    private int produceNum;

    private double produceUnitPrice;

    private double produceAccount;

    private int outputNum;

    private double outputUnitPrice;

    private double outputAccount;

    private int balanceNum;

    public ProducerProductListPO(String ppID, String productVariety, int produceNum, double produceUnitPrice, double produceAccount, int outputNum, double outputUnitPrice, double outputAccount, int balanceNum) {
        this.ppID = ppID;
        this.productVariety = productVariety;
        this.produceNum = produceNum;
        this.produceUnitPrice = produceUnitPrice;
        this.produceAccount = produceAccount;
        this.outputNum = outputNum;
        this.outputUnitPrice = outputUnitPrice;
        this.outputAccount = outputAccount;
        this.balanceNum = balanceNum;
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

    public double getProduceAccount() {
        return produceAccount;
    }

    public void setProduceAccount(double produceAccount) {
        this.produceAccount = produceAccount;
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

    public double getProduceUnitPrice() {
        return produceUnitPrice;
    }

    public void setProduceUnitPrice(double produceUnitPrice) {
        this.produceUnitPrice = produceUnitPrice;
    }

    public double getOutputUnitPrice() {
        return outputUnitPrice;
    }

    public void setOutputUnitPrice(double outputUnitPrice) {
        this.outputUnitPrice = outputUnitPrice;
    }
}

