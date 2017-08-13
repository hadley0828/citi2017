package po;

/**
 * Created by loohaze on 2017/8/12.
 *
 * templateId --> 模板ID
 * a_id --> amount编号
 * digest --> 摘要
 * subject --> 会计科目
 * debitAmount --> 借方金额
 * creditAmount --> 贷方金额
 */
public class VoucherTemplateAmountPO {

    private String templateId;

    private String a_id;

    private String digest;

    private String subject;

    private double debitAmount;

    private double creditAmount;

    public VoucherTemplateAmountPO(){

    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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
