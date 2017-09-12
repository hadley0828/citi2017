package vo;

/**
 * Created by 费慧通 on 2017/9/12.
 *
 * 辅助信息录入第二部分
 */
public class CreditItemVo {
    private String company_name;    //公司名称
    private String borrow_time;     //借款时间
    private String deadline;        //还款期限
    private double money;           //金额
    private String policy;          //折扣政策
    private String discount_deadline;   //折扣期限
    private String remark;      //备注

    public CreditItemVo(){

    }

    public CreditItemVo(String company_name, String borrow_time, String deadline, double money, String policy, String discount_deadline, String remark){
        this.company_name = company_name;
        this.borrow_time = borrow_time;
        this.deadline = deadline;
        this.money = money;
        this.policy = policy;
        this.discount_deadline = discount_deadline;
        this.remark = remark;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getBorrow_time() {
        return borrow_time;
    }

    public String getDeadline() {
        return deadline;
    }

    public double getMoney() {
        return money;
    }

    public String getPolicy() {
        return policy;
    }

    public String getDiscount_deadline() {
        return discount_deadline;
    }

    public String getRemark() {
        return remark;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setBorrow_time(String borrow_time) {
        this.borrow_time = borrow_time;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public void setDiscount_deadline(String discount_deadline) {
        this.discount_deadline = discount_deadline;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
