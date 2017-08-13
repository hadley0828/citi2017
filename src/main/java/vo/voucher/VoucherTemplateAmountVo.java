package vo.voucher;

import po.VoucherTemplateAmountPO;

/**
 * Created by zhangzy on 2017/8/13 下午2:40
 */
public class VoucherTemplateAmountVo {

    private String templateId;
    private String templateAmountId;
    private String abstracts;
    private String subject;
    private double debitAmount;
    private double creditAmount;

    public VoucherTemplateAmountVo(){
        super();
    }

    public VoucherTemplateAmountVo(VoucherTemplateAmountPO po){
        try{
            this.templateId=po.getTemplateId();
            this.templateAmountId=po.getA_id();
            this.abstracts=po.getDigest();
            this.subject=po.getSubject();
            this.debitAmount=po.getDebitAmount();
            this.creditAmount=po.getCreditAmount();
        }catch (Exception e){
            System.out.println("the voucherTemplateAmountPo is not complete");
        }

    }


    @Override
    public String toString() {
        return "VoucherTemplateAmountVo{" +
                "templateId='" + templateId + '\'' +
                ", templateAmountId='" + templateAmountId + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", subject='" + subject + '\'' +
                ", debitAmount=" + debitAmount +
                ", creditAmount=" + creditAmount +
                '}';
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateAmountId() {
        return templateAmountId;
    }

    public void setTemplateAmountId(String templateAmountId) {
        this.templateAmountId = templateAmountId;
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
