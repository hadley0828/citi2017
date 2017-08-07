package dataservice;

import po.VoucherAmountPO;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/8/7.
 */
public interface CourseMessageService {
    /**
     * 根据凭证id得到其会计科目信息
     * @param voucher_id 凭证id
     */
    public ArrayList<VoucherAmountPO> CourseMessageService(String voucher_id);
}
