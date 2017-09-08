package vo.accountBook;

/**
 * Created by zhangzy on 2017/9/8 下午8:39
 */
public class DetailAccountAmountVo {
    private String date;        //日期
    private String voucherId;   //凭证字号
    private String subject;     //科目编号+科目名称
    private String abstracts;   //摘要
    private double debitAmount; //借方金额
    private double creditAmount;//贷方金额
    private String direction;   //方向 借 贷 平 借还是贷取决于科目编号 平取决于之前是否有数据
    private double balance;     //余额 借：借方-贷方    贷：贷方-借方

    public DetailAccountAmountVo(){
        super();
    }

    @Override
    public String toString() {
        return "DetailAccountAmountVo{" +
                "date='" + date + '\'' +
                ", voucherId='" + voucherId + '\'' +
                ", subject='" + subject + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", debitAmount=" + debitAmount +
                ", creditAmount=" + creditAmount +
                ", direction='" + direction + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
