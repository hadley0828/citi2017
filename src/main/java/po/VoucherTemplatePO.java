package po;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/8/7.
 *
 * templateId --> 模板ID
 * digest --> 摘要
 * subject --> 会计科目
 */
public class VoucherTemplatePO {

    private String templateId;

    private String digest;

    private String subject;

    private ArrayList<VoucherAmountPO> templateAmountList;

    public VoucherTemplatePO(){
        super();

    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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

    public ArrayList<VoucherAmountPO> getTemplateAmountList() {
        return templateAmountList;
    }

    public void setTemplateAmountList(ArrayList<VoucherAmountPO> templateAmountList) {
        this.templateAmountList = templateAmountList;
    }




}
