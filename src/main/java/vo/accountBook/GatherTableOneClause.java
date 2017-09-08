package vo.accountBook;

/**
 * Created by zhangzy on 2017/9/8 下午9:12
 * 科目汇总表中的一条
 */
public class GatherTableOneClause {
    private String subjectId;   //科目编码
    private String subjectName; //科目名称
    private double debitTotal;  //借方总计金额
    private double creditTotal; //贷方总计金额


    public GatherTableOneClause(){
        super();
    }

    @Override
    public String toString() {
        return "GatherTableOneClause{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", debitTotal=" + debitTotal +
                ", creditTotal=" + creditTotal +
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

    public double getDebitTotal() {
        return debitTotal;
    }

    public void setDebitTotal(double debitTotal) {
        this.debitTotal = debitTotal;
    }

    public double getCreditTotal() {
        return creditTotal;
    }

    public void setCreditTotal(double creditTotal) {
        this.creditTotal = creditTotal;
    }



}
