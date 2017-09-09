package data;

import dataservice.SubjectDataService;
import po.SubjectNumberPO;
import po.SubjectsPO;
import util.DatesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzy on 2017/9/9 下午1:14
 */
public class SubjectDataServiceImpl implements SubjectDataService{

    SqlManager sqlManager = SqlManager.getSqlManager();

    @Override
    public HashMap<String, String> getSubjectIdToNameMap() {
        sqlManager.getConnection();

        HashMap<String,String> hashmap = new HashMap<>();

        String sql = "select * from subjects";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{});

        for (Map<String,Object> map : maps){
            hashmap.put(map.get("subjects_id").toString(),map.get("subjects_name").toString());
        }

        sqlManager.releaseAll();
        return hashmap;
    }

    @Override
    public boolean addOneSubject(SubjectsPO po, String factoryId) {
        if(po == null){
            return false;
        }

        sqlManager.getConnection();

        List<Object> params = new ArrayList<>();
        params.add(po.getId());
        params.add(po.getDate());
        params.add(po.getBalances());
        params.add(po.getDebitAmount());
        params.add(po.getCreditAmount());
        params.add(factoryId);
        params.add(po.getVoucher_id());

        try{
            String sql = sqlManager.appendSQL("insert into subjects_balance values", params.size());
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteOneSubject(String subjectId, String voucherId, double balance, String factoryId) {
        sqlManager.getConnection();

        String sql = "delete from subjects_balance where subjects_id=? and voucher_id=? and balance=? and company_id=?";
        List<Object> params = new ArrayList<>();
        params.add(subjectId);
        params.add(voucherId);
        params.add(balance);
        params.add(factoryId);

        try{
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public SubjectNumberPO getNewestSubjectBalance(String subjectId,String factoryId) {
        sqlManager.getConnection();

        String sql = "select * from subjects_balance where date in (select max(date) from subjects_balance where subjects_id=? and company_id=?) and subjects_id=? and company_id=?";

        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{subjectId,factoryId,subjectId,factoryId});
        SubjectNumberPO po = new SubjectNumberPO() ;

        po.setSubjectId(map.get("subjects_id").toString());
        po.setBalance(Double.parseDouble(map.get("balance").toString()));
        po.setCreditAmount(Double.parseDouble(map.get("credit_amount").toString()));
        po.setDebitAmount(Double.parseDouble(map.get("debit_amount").toString()));

        return po;
    }

    @Override
    public ArrayList<SubjectsPO> findOneMonthAllSubjects(String month, String factoryId) {
        sqlManager.getConnection();

        ArrayList<SubjectsPO> list = new ArrayList<>();

        Map<String,String> datemap = DatesUtil.datesParser(month);

        String sql = "select t1.subjects_name,t2.* from subjects as t1,subjects_balance as t2 where t1.subjects_id = t2.subjects_id and year(t2.date)=? and month(t2.date)=? and t2.company_id=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{datemap.get("year"),datemap.get("month"),factoryId});

        for (Map<String,Object> map : maps){
            list.add(getSubjectsPOByMap(map));
        }

        sqlManager.releaseAll();
        return list;
    }

    @Override
    public ArrayList<SubjectsPO> findMonthsAllSubjects(ArrayList<String> monthList, String factoryId) {
        sqlManager.getConnection();

        ArrayList<SubjectsPO> list = new ArrayList<>();

        String regexp= "";
        for (String month : monthList){
            regexp += month + "|";
        }
        regexp = regexp.substring(0, regexp.length()-1);

        //利用mysql中的正则表达式避免循环数据库查询操作
        String sql = "select t1.subjects_name,t2.* from subjects as t1,subjects_balance as t2 where t1.subjects_id = t2.subjects_id and t2.date regexp ? and t2.company_id=?";

        ArrayList<Map<String,Object>>  maps = sqlManager.queryMulti(sql,new Object[]{regexp,factoryId});

        for (Map<String,Object> map : maps){
            list.add(getSubjectsPOByMap(map));
        }
        sqlManager.releaseAll();
        return list;
    }

    @Override
    public ArrayList<SubjectsPO> findOneYearAllSubjects(String year, String factoryId) {
        sqlManager.getConnection();

        ArrayList<SubjectsPO> list = new ArrayList<>();

        String sql = "select t1.subjects_name,t2.* from subjects as t1,subjects_balance as t2 where t1.subjects_id = t2.subjects_id and year(t2.date)=? and t2.company_id=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{year,factoryId});

        for (Map<String,Object> map : maps){
            list.add(getSubjectsPOByMap(map));
        }

        sqlManager.releaseAll();
        return list;
    }

    @Override
    public ArrayList<String> getAllExistedSubjectId(String factoryId) {
        sqlManager.getConnection();

        ArrayList<String> list = new ArrayList<>();
        String sql = "select distinct(subjects_id) from subjects_balance where company_id=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{factoryId});
        for (Map<String,Object> map : maps){
            list.add(map.get("subjects_id").toString());
        }

        sqlManager.releaseAll();
        return list;
    }

    private SubjectsPO getSubjectsPOByMap(Map<String,Object> map){
        SubjectsPO po = new SubjectsPO();

        po.setId(map.get("subjects_id").toString());
        po.setName(map.get("subjects_name").toString());
        po.setDate(java.sql.Date.valueOf(map.get("date").toString()));
        po.setBalances(Double.parseDouble(map.get("balance").toString()));
        po.setDebitAmount(Double.parseDouble(map.get("debit_amount").toString()));
        po.setCreditAmount(Double.parseDouble(map.get("credit_amount").toString()));
        po.setVoucher_id(map.get("voucher_id").toString());

        return po;
    }
}
