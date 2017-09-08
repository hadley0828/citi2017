package dataservice;

import po.SubjectsPO;

/**
 * Created by zhangzy on 2017/9/8 上午10:06
 * 用于处理和会计科目相关的数据层接口
 */
public interface SubjectDataService {

    /**
     * 添加一个科目记录
     * @param po
     * @return
     */
    public boolean addOneSubject(SubjectsPO po,String factoryId);

    /**
     * 删除一个科目记录
     * @param subjectId
     * @param date
     * @return
     */
    public boolean deleteOneSubject(String subjectId,String date,String factoryId);

    /**
     * 更新一个科目记录 如果是一个新的期间则添加 如果是仍然是同一个会计期间则更新
     * @param subjectPO
     * @return
     */
    public boolean updateOneSubject(SubjectsPO subjectPO,String factoryId);

    /**
     * 查找一个科目记录
     * @param subjectId
     * @param date
     * @return
     */
    public SubjectsPO findOneSubject(String subjectId,String date,String factoryId);


}
