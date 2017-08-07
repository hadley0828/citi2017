package vopo;

/**
 * Created by 费慧通 on 2017/8/7.
 *
 * 会计科目信息
 */
public class VoucherCourseVo {
    String course_id;   //科目编号
    double debit;   //借方金额
    double credit;  //贷方金额

    public VoucherCourseVo(String course_id, double debit, double credit){
        this.course_id = course_id;
        this.debit = debit;
        this.credit = credit;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
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
