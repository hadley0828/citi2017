package vo.accountBook;

import java.util.ArrayList;

/**
 * 一个会计科目的明细账
 * Created by zhangzy on 2017/9/8 下午8:37
 */
public class DetailAccountVo {

    private String subjectId;   //科目编号
    private String subkectName; //科目名称
    private ArrayList<DetailAccountAmountVo> amountVoArrayList; //会计科目对应的指定期间的多条明细账


    public DetailAccountVo(){
        super();
    }

    @Override
    public String toString() {
        return "DetailAccountVo{" +
                "subjectId='" + subjectId + '\'' +
                ", subkectName='" + subkectName + '\'' +
                ", amountVoArrayList=" + amountVoArrayList +
                '}';
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubkectName() {
        return subkectName;
    }

    public void setSubkectName(String subkectName) {
        this.subkectName = subkectName;
    }

    public ArrayList<DetailAccountAmountVo> getAmountVoArrayList() {
        return amountVoArrayList;
    }

    public void setAmountVoArrayList(ArrayList<DetailAccountAmountVo> amountVoArrayList) {
        this.amountVoArrayList = amountVoArrayList;
    }
}
