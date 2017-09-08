package data;

import dataservice.CourseMessageService;
import po.SubjectsPO;
import po.VoucherAmountPO;
import util.DatesUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/8/8.
 */
public class CourseMessageServiceImpl implements CourseMessageService{

    SqlManager sqlManager = SqlManager.getSqlManager();


    @Override
    public ArrayList<SubjectsPO> getCurrentCouseMessage(String company_id) {
        sqlManager.getConnection();

        ArrayList<SubjectsPO> list = new ArrayList<>();
        String sql = "SELECT t1.subjects_name,t2.* FROM subjects as t1,subjects_balance as t2 WHERE t1.subjects_id = t2.subjects_id AND company_id =? AND date IN(SELECT MAX(date) FROM subjects_balance WHERE subjects_id = t1.subjects_id)";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id});

        for (Map<String,Object> map : maps){
            list.add(getSubjectsPO(map));
        }

        sqlManager.releaseAll();
        return list;
    }

    @Override
    public ArrayList<SubjectsPO> getYearEndCourseMessage(String company_id, String year) {
        sqlManager.getConnection();

        ArrayList<SubjectsPO> list = new ArrayList<>();
        String sql = "SELECT t1.subjects_name,t2.* FROM subjects as t1,subjects_balance as t2 WHERE t1.subjects_id = t2.subjects_id AND company_id =? AND date IN(SELECT MAX(date) FROM subjects_balance WHERE subjects_id = t1.subjects_id AND year(date) =?)";

        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id,year});

        for (Map<String,Object> map : maps){
            list.add(getSubjectsPO(map));
        }

        sqlManager.releaseAll();
        return list;
    }

    @Override
    public boolean HasYear(String company_id, String year) {
        sqlManager.getConnection();

        String sql = "SELECT date FROM subjects_balance WHERE year(date)=? AND company_id=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{year,company_id});

        sqlManager.releaseAll();

        if (maps.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public ArrayList<SubjectsPO> getBeginCourseMessage(String company_id) {
        sqlManager.getConnection();

        ArrayList<SubjectsPO> list = new ArrayList<>();
        String sql = "SELECT t1.subjects_name,t2.* FROM subjects as t1,subjects_balance as t2 WHERE t1.subjects_id = t2.subjects_id AND company_id =? AND date IN(SELECT min(date) FROM subjects_balance WHERE subjects_id = t1.subjects_id)";

        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id});
        for (Map<String,Object> map : maps){
            list.add(getSubjectsPO(map));
        }

        sqlManager.releaseAll();
        return list;
    }

    @Override
    public String getCourseNameById(String id) {
        sqlManager.getConnection();

        String sql = "SELECT subjects_name FROM subjects WHERE subjects_id=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{id});
        sqlManager.releaseAll();
        return map.get("subjects_name").toString();
    }

    @Override
    public int getVoucherNumber(String company_id) {
        sqlManager.getConnection();

        String sql = "SELECT count(v_id) as num from voucher WHERE company_id=?";
        Map<String, Object> map = sqlManager.querySimple(sql,new Object[]{company_id});
        int result = Integer.parseInt(map.get("num").toString());
        sqlManager.releaseAll();

        return result;
    }

    @Override
    public String getEarliestTime(String company_id) {
        sqlManager.getConnection();

        String sql = "SELECT min(date) as earlytime from voucher WHERE company_id=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{company_id});
        String[] time = map.get("earlytime").toString().split("-");
        String result = time[0]+ "-" + time[1];
        sqlManager.releaseAll();
        return result;
    }

    @Override
    public String getLatestTime(String company_id) {
        sqlManager.getConnection();

        String sql = "SELECT max(date) as latetime from voucher WHERE company_id=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{company_id});
        String[] time = map.get("latetime").toString().split("-");
        String result = time[0]+ "-" + time[1];
        sqlManager.releaseAll();
        return result;
    }

    /**
     * 根据 yyyy-mm 时间格式获得 凭证id
     * @param period
     * @return
     */
    private ArrayList<String> getVIdListByDate(String period){
        sqlManager.getConnection();

        ArrayList<String> vidList = new ArrayList<>();
        Map<String,String> datemap = DatesUtil.datesParser(period);
        String sql = "SELECT v_id FROM voucher WHERE year(date)=? AND month(date)=?";
        List<Map<String,Object>> vidRawList = sqlManager.queryMulti(sql,new Object[]{datemap.get("year"),datemap.get("month")});
        for(Map<String,Object> map : vidRawList){
            vidList.add(map.get("v_id").toString());
        }
        sqlManager.releaseAll();
        return vidList;
    }

    private VoucherAmountPO getVoucherAmountPO(Map<String,Object> map){

        VoucherAmountPO po = new VoucherAmountPO();
        po.setV_id(map.get("v_id").toString());
        po.setA_id(map.get("a_id").toString());
        po.setDigest(map.get("abstract").toString());
        po.setSubject(map.get("subject").toString());
        po.setDebitAmount(Double.parseDouble(map.get("debit_amount").toString()));
        po.setCreditAmount(Double.parseDouble(map.get("credit_amount").toString()));

        return po;
    }


    private SubjectsPO getSubjectsPO(Map<String,Object> map){
        SubjectsPO po = new SubjectsPO();
        po.setId(map.get("subjects_id").toString());
        po.setName(map.get("subjects_name").toString());
        po.setDate(Date.valueOf(map.get("date").toString()));
        po.setDebitAmount(Double.parseDouble(map.get("debit_amount").toString()));
        po.setCreditAmount(Double.parseDouble(map.get("credit_amount").toString()));
        po.setBalances(Double.parseDouble(map.get("balance").toString()));

        return po;
    }

}
