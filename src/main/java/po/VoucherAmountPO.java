package po;

/**
 * Created by loohaze on 2017/8/7.
 *
 * id --> 凭证编号
 * digest --> 摘要
 * subject --> 会计科目编号
 * debitAmount --> 借方金额
 * creditAmount --> 贷方金额
 */
public class VoucherAmountPO {

    private String id;

    private String digest;

    private String subject;

    private double debitAmount;

    private double creditAmount;

    public VoucherAmountPO(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
