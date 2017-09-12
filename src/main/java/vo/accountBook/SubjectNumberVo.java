package vo.accountBook;

import po.SubjectNumberPO;

/**
 * Created by zhangzy on 2017/9/12 上午11:18
 */
public class SubjectNumberVo {

    String subjectId;   //科目编号
    double debitAmount; //借款金额
    double creditAmount;//贷款金额
    double balance;     //当前的余额

    public SubjectNumberVo(){
        super();
    }

    @Override
    public String toString() {
        return "SubjectNumberVo{" +
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
