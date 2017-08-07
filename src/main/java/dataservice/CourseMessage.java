package dataservice;

/**
 * Created by 费慧通 on 2017/8/7.
 */
public interface CourseMessage {
    /**
     * 根据凭证id得到其会计科目信息
     * @param voucher_id 凭证id
     */
    public getCourseMessage(String voucher_id);
}
