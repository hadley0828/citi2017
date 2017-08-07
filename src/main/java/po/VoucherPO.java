package po;

import java.sql.Date;

/**
 * Created by loohaze on 2017/8/7.
 *
 * id --> 凭证编号
 * date --> 凭证日期
 * isAddReceipts --> 是否附单据
 * voucher_maker --> 制单人
 * remark --> 备注
 *
 */
public class VoucherPO {

    private String id;

    private Date date;

    private boolean isAddReceipts;

    private String voucher_maker;

    private String remark;

    public VoucherPO(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAddReceipts() {
        return isAddReceipts;
    }

    public void setAddReceipts(boolean addReceipts) {
        isAddReceipts = addReceipts;
    }

    public String getVoucher_maker() {
        return voucher_maker;
    }

    public void setVoucher_maker(String voucher_maker) {
        this.voucher_maker = voucher_maker;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
