package po;

import vo.voucher.VoucherAmountVo;

/**
 * Created by loohaze on 2017/8/7.
 *
 * v_id --> 凭证编号
 * a_id --> amount编号
 * digest --> 摘要
 * subject --> 会计科目编号
 * debitAmount --> 借方金额
 * creditAmount --> 贷方金额
 */
public class VoucherAmountPO {

    private String v_id;

    private String a_id;

    private String digest;

    private String subject;

    private double debitAmount;

    private double creditAmount;

    public VoucherAmountPO(){
        super();
    }

    public VoucherAmountPO(VoucherAmountVo vo){
        try {
            this.v_id=vo.getVoucherId();
            this.a_id=vo.getAmountId();
            this.digest=vo.getAbstracts();
            this.subject=vo.getSubject();
            this.debitAmount=vo.getDebitAmount();
            this.creditAmount=vo.getCreditAmount();
        }catch (Exception e){
            System.out.println("the voucherAmountVo is not complete");
        }
    }

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
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
