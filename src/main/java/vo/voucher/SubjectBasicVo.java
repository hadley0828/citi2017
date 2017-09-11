package vo.voucher;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/9/11 下午7:25
 * 和科目基本级别信息相关的类
 */
public class SubjectBasicVo {


    String subjectId;      //科目编号
    String subjectName; //科目名称
    ArrayList<SubjectBasicVo> lowLevelList; //低级科目的列表 如果没有低级科目则为空

    public SubjectBasicVo(){
        super();
    }

    @Override
    public String toString() {
        return "SubjectBasicVo{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", lowLevelList=" + lowLevelList +
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

    public ArrayList<SubjectBasicVo> getLowLevelList() {
        return lowLevelList;
    }

    public void setLowLevelList(ArrayList<SubjectBasicVo> lowLevelList) {
        this.lowLevelList = lowLevelList;
    }




}
