package dataservice;

import po.SubjectsPO;
import po.VoucherAmountPO;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/8/7.
 *
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
     * 得到公司当前会计科目信息
     * @param company_id 公司id
     * @return
     */
    public ArrayList<SubjectsPO> getCurrentCouseMessage(String company_id);

    /**
     * 得到公司某年末的会计科目信息
     * @param company_id 公司id
     * @param year 年份
     * @return
     */
    public ArrayList<SubjectsPO> getYearEndCourseMessage(String company_id, String year);

    /**
     *  根据科目id得到科目名称
     * @param id 科目编号
     * @return
     */
    public String getCourseNameById(String id);

    /**
     * 得到所有凭证数量
     * @param company_id 公司id
     * @return
     */
    public int getVoucherNumber(String company_id);

    /**
     * 得到凭证最早时间
     * @param company_id 公司id
     * @return
     */
    public String getEarliestTime(String company_id);

    /**
     * 得到凭证最晚时间
     * @param company_id 公司id
     * @return
     */
    public String getLatestTime(String company_id);
}
