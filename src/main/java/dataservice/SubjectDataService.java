package dataservice;

import po.SubjectNumberPO;
import po.SubjectsPO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhangzy on 2017/9/8 上午10:06
 * 用于处理和会计科目相关的数据层接口
 */
public interface SubjectDataService {

    /**
     * 获得全部的科目编号和名称的对应
     * @return
     */
    public HashMap<String,String> getSubjectIdToNameMap();

    /**
     * 添加一个科目记录
     * @param po
     * @return
     */
    public boolean addOneSubject(SubjectsPO po,String factoryId);

    /**
     * 删除一个科目记录
     * @param subjectId
     * @param voucherId
     * @param balance
     * @param factoryId
     * @return
     */
    public boolean deleteOneSubject(String subjectId,String voucherId,double balance,String factoryId);

    /**
     * 获得会计科目的最新的一条金额改变的信息
     * @param subjectId
     * @return
     */
    public SubjectNumberPO getNewestSubjectBalance(String subjectId,String factoryId);

    /**
     * 查找某一个月的全部科目记录
     * @param month 格式为 2017-08
     * @return
     */
    public ArrayList<SubjectsPO> findOneMonthAllSubjects(String month,String factoryId);

    /**
     * 查找选定的月份的全部科目记录
     * 获取之后逻辑层再对期间进行筛选
     * @param monthList
     * @return
     */
    public ArrayList<SubjectsPO> findMonthsAllSubjects(ArrayList<String> monthList,String factoryId);

    /**
     * 查找某一年的全部科目记录
     * @param year 格式为 2017
     * @param factoryId
     * @return
     */
    public ArrayList<SubjectsPO> findOneYearAllSubjects(String year,String factoryId);

    /**
     * 获得科目数据库所有存在记录的科目编号
     * @param factoryId
     * @return
     */
    public ArrayList<String> getAllExistedSubjectId(String factoryId);

    /**
     * 获得数据库中全部的科目记录信息
     * @param factoryId
     * @return
     */
    public ArrayList<SubjectsPO> getAllSubjects(String factoryId);

    /**
     * 获得一个科目全部的科目记录信息
     * @param subjectId
     * @param factoryId
     * @return
     */
    public ArrayList<SubjectsPO> getOneSubjectAllRecords(String subjectId,String factoryId);

}
