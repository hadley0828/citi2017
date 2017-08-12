package vo.voucher;

import po.VoucherAmountPO;

/**
 * Created by zhangzy on 2017/8/11 下午11:36
 */
public class VoucherAmountVo {

    private String voucherId;   //凭证编号
    private int amountId;       //金额编号
    private String abstracts;   //摘要
    private String subject;     //会计科目
    private double debitAmount; //借方金额
    private double creditAmount;//贷方金额


    public VoucherAmountVo(){
        super();
    }

    @Override
    public String toString() {
        return "VoucherAmountVo{" +
                "voucherId='" + voucherId + '\'' +
                ", amountId=" + amountId +
                ", abstracts='" + abstracts + '\'' +
                ", subject='" + subject + '\'' +
                ", debitAmount=" + debitAmount +
                ", creditAmount=" + creditAmount +
                '}';
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public int getAmountId() {
        return amountId;
    }

    public void setAmountId(int amountId) {
        this.amountId = amountId;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }




}
