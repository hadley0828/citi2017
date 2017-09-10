package vo.accountBook;

/**
 * Created by zhangzy on 2017/9/8 下午9:32
 * 科目余额表中的一条
 */
public class BalanceTableOneClause {

    String subjectId;   //科目编码
    String subjectName; //科目名称
    double beginDebit;  //期初余额  借方
    double beginCredit; //期初余额  贷方
    double currentDebit;//本期发生额 借方
    double currentCredit;//本期发生额 贷方
    double endDebit;    //期末余额  借方
    double endCredit;   //期末余额  贷方

    public BalanceTableOneClause(){
        super();
    }

    @Override
    public String toString() {
        return "BalanceTableOneClause{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", beginDebit=" + beginDebit +
                ", beginCredit=" + beginCredit +
                ", currentDebit=" + currentDebit +
                ", currentCredit=" + currentCredit +
                ", endDebit=" + endDebit +
                ", endCredit=" + endCredit +
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

    public double getBeginDebit() {
        return beginDebit;
    }

    public void setBeginDebit(double beginDebit) {
        this.beginDebit = beginDebit;
    }

    public double getBeginCredit() {
        return beginCredit;
    }

    public void setBeginCredit(double beginCredit) {
        this.beginCredit = beginCredit;
    }

    public double getCurrentDebit() {
        return currentDebit;
    }

    public void setCurrentDebit(double currentDebit) {
        this.currentDebit = currentDebit;
    }

    public double getCurrentCredit() {
        return currentCredit;
    }

    public void setCurrentCredit(double currentCredit) {
        this.currentCredit = currentCredit;
    }

    public double getEndDebit() {
        return endDebit;
    }

    public void setEndDebit(double endDebit) {
        this.endDebit = endDebit;
    }

    public double getEndCredit() {
        return endCredit;
    }

    public void setEndCredit(double endCredit) {
        this.endCredit = endCredit;
    }



}
