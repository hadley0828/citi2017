package vo.accountBook;

/**
 * Created by zhangzy on 2017/9/8 下午9:42
 */
public class TotalAccountAmountVo {
    String subjectId;   //科目编码
    String subjectName; //科目名称
    String period;      //期间
    String abstracts;   //摘要
    double debitAmount; //借方金额
    double creditAmount;//贷方金额
    String direction;   //方向
    double balance;     //余额


    public TotalAccountAmountVo(){
        super();
    }

    @Override
    public String toString() {
        return "TotalAccountAmountVo{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", period='" + period + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", debitAmount=" + debitAmount +
                ", creditAmount=" + creditAmount +
                ", direction='" + direction + '\'' +
                ", balance=" + balance +
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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
