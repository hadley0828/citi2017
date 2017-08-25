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
    public ArrayList<VoucherAmountPO> getCourseMessageById(String voucher_id);

    /**
     * 根据时间（类似于2017-01这样）获得所有会计科目信息
     * @param period
     * @return
     */
    public ArrayList<VoucherAmountPO> getCourseMessageByTime(String period);

    /**
     *  根据科目id得到科目名称
     * @param id
     * @return
     */
    public String getCourseNameById(String id);

    /**
     * 得到所有凭证数量
     * @return
     */
    public int getVoucherNumber();

    /**
     * 得到凭证最早时间
     * @return
     */
    public String getEarliestTime();

    /**
     * 得到凭证最晚时间
     * @return
     */
    public String getLatestTime();
}
