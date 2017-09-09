package po;


import java.sql.Date;

/**
 * Created by loohaze on 2017/8/7.
 *
 * id --> 会计科目编号
 * name --> 会计科目名称
 * balances --> 余额
 * voucher_id --> 凭证id
 * date --> 日期
 * debitAmount --> 借方金额
 * creditAmount --> 贷方金额
 */
public class SubjectsPO {

    private String id;

    private String name;

    private Date date;

    private String voucher_id;

    private double balances;

    private double debitAmount;

    private double creditAmount;



    public SubjectsPO(){

    }

    public SubjectsPO(String id, String name, Date date, String voucher_id, double balances, double debitAmount, double creditAmount) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.voucher_id = voucher_id;
        this.balances = balances;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public double getBalances() {
        return balances;
    }

    public void setBalances(double balances) {
        this.balances = balances;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }
}
