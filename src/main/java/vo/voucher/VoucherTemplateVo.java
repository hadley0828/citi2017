package vo.voucher;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/8/11 下午11:37
 */
public class VoucherTemplateVo {

    private String templateId;  //模板编号
    private String category;    //类别
    private String templateName;//模板名称
    private ArrayList<VoucherAmountVo> amountList;  //金额列表

    public VoucherTemplateVo(){
        super();
    }

    @Override
    public String toString() {
        return "VoucherTemplateVo{" +
                "templateId='" + templateId + '\'' +
                ", category='" + category + '\'' +
                ", templateName='" + templateName + '\'' +
                ", amountList=" + amountList +
                '}';
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public ArrayList<VoucherAmountVo> getAmountList() {
        return amountList;
    }

    public void setAmountList(ArrayList<VoucherAmountVo> amountList) {
        this.amountList = amountList;
    }




}
