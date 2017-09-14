package businesslogicservice;

import po.SubjectNumberPO;
import po.SubjectsPO;
import vo.accountBook.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhangzy on 2017/8/7 下午10:44
 * 处理和账簿相关功能的逻辑层接口
 */
public interface AccountBooksBlService {

    /**
     * searchVo中的startPeriod endPeriod highLevel lowLevel为必填选项
     */

    /**
     * 明细账
     */

    /**
     * 获得全部有数据记录的科目编号
     * @param factoryId
     * @return
     */
    public ArrayList<String> getAllExistedSubjectId(String factoryId);

    /**
     * 获得一个科目的明细账
     * 默认状态是会计期间是当前的期间 科目级别是1到1
     * @param subjectId
     * @param searchVo
     * @param factoryId
     * @return
     */
    public DetailAccountVo getOneSubjectDetail(String subjectId, BookSearchVo searchVo,String factoryId);

    /**
     * 总账
     */

    /**
     * 获得全部科目的总账
     * @param searchVo
     * @param factoryId
     * @return
     */
    public ArrayList<TotalAccountVo> getAllSubjectTotal(BookSearchVo searchVo,String factoryId);

    /**
     * 获得单个科目的总账
     * @param subjectId
     * @param searchVo
     * @param factoryId
     * @return
     */
    public TotalAccountVo getOneSubjectTotal(String subjectId,BookSearchVo searchVo,String factoryId);

    /**
     * 科目余额表
     */

    /**
     * 获取部分科目的科目余额表
     * @param searchVo
     * @param factoryId
     * @return
     */
    public ArrayList<BalanceTableOneClause> getBalanceTableAllClauses(BookSearchVo searchVo,String factoryId);


    /**
     * 科目汇总表
     */

    /**
     * 获取部分科目的科目汇总表
     * @param searchVo
     * @param factoryId
     * @return
     */
    public ArrayList<GatherTableOneClause> getGatherTableAllClauses(BookSearchVo searchVo,String factoryId);

    /**
     * 获得全部科目某一个期间的期末金额信息
     * @param month    "2017-08"
     * @param factoryId
     * @return
     */
    public ArrayList<SubjectsPO> getAllSubjectPeriodEndPrice(String month, String factoryId);

}
