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

    private String catagory;

    private String templateName;

    public VoucherTemplatePO(){

    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
