package vo.Inventory;

/**
 * Created by 费慧通 on 2017/9/9.
 *
 * 折线图——准时交货率随着时间的变化的数据
 */
public class PunctualDeliveryRateChangeVo {
    private String time;    //时间
    private double punctual_delivery_rate;      //准时交货率

    public PunctualDeliveryRateChangeVo(String time, double punctual_delivery_rate){
        this.time = time;
        this.punctual_delivery_rate = punctual_delivery_rate;
    }

    public String getTime() {
        return time;
    }

    public double getPunctual_delivery_rate() {
        return punctual_delivery_rate;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPunctual_delivery_rate(double punctual_delivery_rate) {
        this.punctual_delivery_rate = punctual_delivery_rate;
    }
}
