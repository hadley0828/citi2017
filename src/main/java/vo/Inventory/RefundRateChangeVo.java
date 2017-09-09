package vo.Inventory;

/**
 * Created by 费慧通 on 2017/9/9.
 *
 * 折线图——退货率随着时间的变化
 */
public class RefundRateChangeVo {
    private String time;    //时间
    private double refund_rate;  //退货率

    public RefundRateChangeVo(String time, double refund_rate){
        this.time = time;
        this.refund_rate = refund_rate;
    }

    public String getTime() {
        return time;
    }

    public double getRefund_rate() {
        return refund_rate;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setRefund_rate(double refund_rate) {
        this.refund_rate = refund_rate;
    }
}
