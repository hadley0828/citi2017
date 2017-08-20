package vo;

/**
 * Created by 费慧通 on 2017/8/20.
 */
public class TrialTableItemVo {
    private String id;  //科目编号
    private String name;    //科目名称
    private double debit;   //借方余额
    private double credit;  //贷方余额

    public TrialTableItemVo(String id, String name, double debit, double credit){
        this.id = id;
        this.name = name;
        this.debit = debit;
        this.credit = credit;
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

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
