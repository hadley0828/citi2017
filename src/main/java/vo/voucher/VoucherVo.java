package vo.voucher;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/8/11 下午11:35
 */
public class VoucherVo {

    private String voucherId;       //凭证编号
    private String date;            //凭证日期
    private boolean isAddedReceipts;//是否附单据
    private String voucherMaker;    //制单人
    private String remark;          //备注
    private ArrayList<VoucherAmountVo> amountList;  //凭证金额的列表
    private AmountTotalVo amountTotalVo;

    public VoucherVo(){
        super();
    }

    @Override
    public String toString() {
        return "VoucherVo{" +
                "voucherId='" + voucherId + '\'' +
                ", date='" + date + '\'' +
                ", isAddedReceipts=" + isAddedReceipts +
                ", voucherMaker='" + voucherMaker + '\'' +
                ", remark='" + remark + '\'' +
                ", amountList=" + amountList +
                ", amountTotalVo=" + amountTotalVo +
                '}';
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isAddedReceipts() {
        return isAddedReceipts;
    }

    public void setAddedReceipts(boolean addedReceipts) {
        isAddedReceipts = addedReceipts;
    }

    public String getVoucherMaker() {
        return voucherMaker;
    }

    public void setVoucherMaker(String voucherMaker) {
        this.voucherMaker = voucherMaker;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ArrayList<VoucherAmountVo> getAmountList() {
        return amountList;
    }

    public void setAmountList(ArrayList<VoucherAmountVo> amountList) {
        this.amountList = amountList;
    }

    public AmountTotalVo getAmountTotalVo() {
        return amountTotalVo;
    }

    public void setAmountTotalVo(AmountTotalVo amountTotalVo) {
        this.amountTotalVo = amountTotalVo;
    }

}
