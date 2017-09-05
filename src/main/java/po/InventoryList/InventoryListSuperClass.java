package po.InventoryList;

import java.sql.Timestamp;

/**
 * Created by loohaze on 2017/9/5 下午9:20
 *
 * 各个商库存详细条目父类
 * listID --> 条目编号
 * voucherID --> 凭证编号
 * datetime --> 时间(注意数据类型)
 * isDeliveryOntime --> 是否准时交货
 * isReturn --> 是否属于退货
 */
public class InventoryListSuperClass {

    private String listID;

    private String voucherID;

    private Timestamp datetime;

    private boolean isDeliveryOntime;

    private boolean isReturn;

    public InventoryListSuperClass() {
    }

    public InventoryListSuperClass(String listID, String voucherID, Timestamp datetime, boolean isDeliveryOntime, boolean isReturn) {
        this.listID = listID;
        this.voucherID = voucherID;
        this.datetime = datetime;
        this.isDeliveryOntime = isDeliveryOntime;
        this.isReturn = isReturn;
    }

    public String getListID() {
        return listID;
    }

    public void setListID(String listID) {
        this.listID = listID;
    }

    public String getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(String voucherID) {
        this.voucherID = voucherID;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public boolean isDeliveryOntime() {
        return isDeliveryOntime;
    }

    public void setDeliveryOntime(boolean deliveryOntime) {
        isDeliveryOntime = deliveryOntime;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public void setReturn(boolean aReturn) {
        isReturn = aReturn;
    }
}
