package vo.accountBook;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/9/8 下午9:41
 * 一个会计科目的总账
 */
public class TotalAccountVo {
    String subjectId;
    String subjectName;
    ArrayList<TotalAccountAmountVo> amountVoArrayList;

    public TotalAccountVo(){
        super();
    }


    @Override
    public String toString() {
        return "TotalAccountVo{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", amountVoArrayList=" + amountVoArrayList +
                '}';
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public ArrayList<TotalAccountAmountVo> getAmountVoArrayList() {
        return amountVoArrayList;
    }

    public void setAmountVoArrayList(ArrayList<TotalAccountAmountVo> amountVoArrayList) {
        this.amountVoArrayList = amountVoArrayList;
    }



}
