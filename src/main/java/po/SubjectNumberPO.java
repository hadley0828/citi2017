package po;

/**
 * 用于获得会计科目最新的数量信息的类
 * Created by zhangzy on 2017/9/9 下午1:40
 */
public class SubjectNumberPO {
    String subjectId;   //科目编号
    double debitAmount; //借款金额
    double creditAmount;//贷款金额
    double balance;     //当前的余额

    public SubjectNumberPO(){
        super();
    }


    @Override
    public String toString() {
        return "SubjectNumberPO{" +
                "subjectId='" + subjectId + '\'' +
                ", debitAmount=" + debitAmount +
                ", creditAmount=" + creditAmount +
                ", balance=" + balance +
                '}';
    }


    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }



}
