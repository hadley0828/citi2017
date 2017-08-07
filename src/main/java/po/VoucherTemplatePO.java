package po;

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

    public VoucherTemplatePO(){


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
}
