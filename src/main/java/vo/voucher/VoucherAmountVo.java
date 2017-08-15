package vo.voucher;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import po.VoucherAmountPO;
import po.VoucherTemplateAmountPO;

/**
 * Created by zhangzy on 2017/8/11 下午11:36
 */
public class VoucherAmountVo {

    private String voucherId;   //凭证编号
    private String amountId;    //金额编号
    private String abstracts;   //摘要
    private String subject;     //会计科目
    private double debitAmount; //借方金额
    private double creditAmount;//贷方金额


    public VoucherAmountVo(){
        super();
    }

    public VoucherAmountVo(VoucherAmountPO po){
        try{
            this.voucherId=po.getV_id();
            this.amountId=po.getA_id();
            this.abstracts=po.getDigest();
            this.subject=po.getSubject();
            this.debitAmount=po.getDebitAmount();
            this.creditAmount=po.getCreditAmount();
        }catch (Exception e){
            System.out.println("po is not complete");
        }
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

    public String getAmountId() {
        return amountId;
    }

    public void setAmountId(String amountId) {
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
