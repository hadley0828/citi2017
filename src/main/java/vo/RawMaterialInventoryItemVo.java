package vo;

/**
 * Created by 费慧通 on 2017/9/4.
 */
public class RawMaterialInventoryItemVo {
    private String id;  //条目id
    private String rawMaterial_variety;    //原材料种类
    private String voucher_id;  //凭证编号
    private String datetime;    //时间
    private boolean is_delivery_ontime;  //是否准时交货
    private boolean is_return;  //是否属于退货
    private int input_num;  //收⼊数量
    private double input_account;  //收入金额
    private int out_num;    //发出数量
    private double out_account; //发出金额
    private int balance_num;    //结存数量
    private double balance_account; //结存金额

    public RawMaterialInventoryItemVo(String id, String rawMaterial_variety, String voucher_id, String datetime, boolean is_delivery_ontime, boolean is_return, int input_num, double input_account,
                                      int out_num, double out_account, int balance_num, double balance_account){
        this.id = id;
        this.rawMaterial_variety = rawMaterial_variety;
        this.voucher_id = voucher_id;
        this.is_delivery_ontime = is_delivery_ontime;
        this.is_return = is_return;
        this.input_num = input_num;
        this.input_account = input_account;
        this.out_num = out_num;
        this.out_account = out_account;
        this.balance_num = balance_num;
        this.balance_account = balance_account;
    }

    public RawMaterialInventoryItemVo() {
    }

    public String getId() {
        return id;
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

    public double getBalance_account() {
        return balance_account;
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

    public String getDatetime() {
        return datetime;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBalance_account(double balance_account) {
        this.balance_account = balance_account;
    }

    public void setBalance_num(int balance_num) {
        this.balance_num = balance_num;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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
}
