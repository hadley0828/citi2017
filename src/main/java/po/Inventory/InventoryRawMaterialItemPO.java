package po.Inventory;

import java.sql.Timestamp;

/**
 * Created by loohaze on 2017/9/8 下午8:06
 */
public class InventoryRawMaterialItemPO {

    private String sheetID;  //表id

    private String listID; // 条目id

    private String companyID; // 公司id

    private String variety; // 原材料种类

    private String voucherID; // 凭证id

    private Timestamp date; // 时间

    private boolean isDeliveryOntime; // 是否准时发货

    private boolean isReturn; // 是否属于退货

    private int inputNum; // 收入数量

    private double inputUnitPrice; // 收入单价

    private double inputTotalPrice; // 收入总价

    private int outputNum; // 发出数量

    private double outputUnitPrice; // 发出单价

    private double outputTotalPrice; // 发出总价

    private int remainNum; // 结存量

    public InventoryRawMaterialItemPO() {
    }

    public InventoryRawMaterialItemPO(String sheetID, String listID, String companyID, String variety, String voucherID, Timestamp date, boolean isDeliveryOntime, boolean isReturn, int inputNum, double inputUnitPrice, double inputTotalPrice, int outputNum, double outputUnitPrice, double outputTotalPrice, int remainNum) {
        this.sheetID = sheetID;
        this.listID = listID;
        this.companyID = companyID;
        this.variety = variety;
        this.voucherID = voucherID;
        this.date = date;
        this.isDeliveryOntime = isDeliveryOntime;
        this.isReturn = isReturn;
        this.inputNum = inputNum;
        this.inputUnitPrice = inputUnitPrice;
        this.inputTotalPrice = inputTotalPrice;
        this.outputNum = outputNum;
        this.outputUnitPrice = outputUnitPrice;
        this.outputTotalPrice = outputTotalPrice;
        this.remainNum = remainNum;
    }

    public String getSheetID() {
        return sheetID;
    }

    public void setSheetID(String sheetID) {
        this.sheetID = sheetID;
    }

    public String getListID() {
        return listID;
    }

    public void setListID(String listID) {
        this.listID = listID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(String voucherID) {
        this.voucherID = voucherID;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public boolean isDeliveryOntime() {
        return isDeliveryOntime;
    }

    public void setDeliveryOntime(boolean deliveryOntime) {
        isDeliveryOntime = deliveryOntime;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public void setReturn(boolean aReturn) {
        isReturn = aReturn;
    }

    public int getInputNum() {
        return inputNum;
    }

    public void setInputNum(int inputNum) {
        this.inputNum = inputNum;
    }

    public double getInputUnitPrice() {
        return inputUnitPrice;
    }

    public void setInputUnitPrice(double inputUnitPrice) {
        this.inputUnitPrice = inputUnitPrice;
    }

    public double getInputTotalPrice() {
        return inputTotalPrice;
    }

    public void setInputTotalPrice(double inputTotalPrice) {
        this.inputTotalPrice = inputTotalPrice;
    }

    public int getOutputNum() {
        return outputNum;
    }

    public void setOutputNum(int outputNum) {
        this.outputNum = outputNum;
    }

    public double getOutputUnitPrice() {
        return outputUnitPrice;
    }

    public void setOutputUnitPrice(double outputUnitPrice) {
        this.outputUnitPrice = outputUnitPrice;
    }

    public double getOutputTotalPrice() {
        return outputTotalPrice;
    }

    public void setOutputTotalPrice(double outputTotalPrice) {
        this.outputTotalPrice = outputTotalPrice;
    }

    public int getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(int remainNum) {
        this.remainNum = remainNum;
    }
}
