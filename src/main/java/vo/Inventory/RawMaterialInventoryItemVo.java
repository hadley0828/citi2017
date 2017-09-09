package vo.Inventory;

import java.sql.Timestamp;

/**
 * Created by 费慧通 on 2017/9/4.
 *
 * 原材料库存录入表格的元祖
 */
public class RawMaterialInventoryItemVo {
    private String rawMaterial_variety;    //原材料种类
    private String voucher_id;  //凭证编号
    private Timestamp datetime;    //时间
    private boolean is_delivery_ontime;  //是否准时交货
    private boolean is_return;  //是否属于退货
    private int input_num;  //收⼊数量
    private double input_price; //收入单价
    private double input_account;  //收入金额
    private int out_num;    //发出数量
    private double out_price;   //发出单价
    private double out_account; //发出金额
    private int balance_num;    //结存数量

    public RawMaterialInventoryItemVo(String rawMaterial_variety, String voucher_id, String datetime, boolean is_delivery_ontime, boolean is_return, int input_num,double input_price, double input_account,
                                      int out_num, double out_price, double out_account, int balance_num) {
        this.rawMaterial_variety = rawMaterial_variety;
        this.voucher_id = voucher_id;
        this.datetime = Timestamp.valueOf(datetime);
        this.is_delivery_ontime = is_delivery_ontime;
        this.is_return = is_return;
        this.input_num = input_num;
        this.input_price = input_price;
        this.input_account = input_account;
        this.out_num = out_num;
        this.out_price = out_price;
        this.out_account = out_account;
        this.balance_num = balance_num;
    }

    public String getRawMaterial_variety() {
        return rawMaterial_variety;
    }

    public boolean isIs_delivery_ontime() {
        return is_delivery_ontime;
    }

    public boolean isIs_return() {
        return is_return;
    }

    public double getInput_account() {
        return input_account;
    }

    public double getOut_account() {
        return out_account;
    }

    public int getBalance_num() {
        return balance_num;
    }

    public int getInput_num() {
        return input_num;
    }

    public int getOut_num() {
        return out_num;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public double getInput_price() {
        return input_price;
    }

    public double getOut_price() {
        return out_price;
    }

    public void setBalance_num(int balance_num) {
        this.balance_num = balance_num;
    }

    public void setDatetime(String datetime) {
        this.datetime = Timestamp.valueOf(datetime);
    }

    public void setInput_account(double input_account) {
        this.input_account = input_account;
    }

    public void setInput_num(int input_num) {
        this.input_num = input_num;
    }

    public void setIs_delivery_ontime(boolean is_delivery_ontime) {
        this.is_delivery_ontime = is_delivery_ontime;
    }

    public void setIs_return(boolean is_return) {
        this.is_return = is_return;
    }

    public void setOut_account(double out_account) {
        this.out_account = out_account;
    }

    public void setOut_num(int out_num) {
        this.out_num = out_num;
    }

    public void setRawMaterial_variety(String rawMaterial_variety) {
        this.rawMaterial_variety = rawMaterial_variety;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }

    public void setInput_price(double input_price) {
        this.input_price = input_price;
    }

    public void setOut_price(double out_price) {
        this.out_price = out_price;
    }
}
