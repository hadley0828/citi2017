package data;

import dataservice.CourseMessageService;
import po.VoucherAmountPO;
import util.DatesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/8/8.
 */
public class CourseMessageServiceImpl implements CourseMessageService{

    SqlManager sqlManager = SqlManager.getSqlManager();

    @Override
    public ArrayList<VoucherAmountPO> getCourseMessageById(String voucher_id) {

        sqlManager.getConnection();
        String sql = "SELECT * FROM voucher_amount WHERE v_id=?";
        List<Map<String,Object>> dataList = sqlManager.queryMulti(sql,new Object[]{voucher_id});

        ArrayList<VoucherAmountPO> list = new ArrayList<>();

        for(Map<String,Object> map : dataList){
            list.add(getVoucherAmountPO(map));
        }

        sqlManager.releaseAll();
        return list;
    }

    @Override
    public ArrayList<VoucherAmountPO> getCourseMessageByTime(String period) {
        sqlManager.getConnection();

        ArrayList<VoucherAmountPO> list = new ArrayList<>();
        Map<String,String> datemap = DatesUtil.datesParser(period);
        String sql = "SELECT * FROM voucher_amount where v_id IN (SELECT v_id FROM voucher WHERE year(date)=? AND month(date)=?)";
        List<Map<String,Object>> datalist = sqlManager.queryMulti(sql,new Object[]{datemap.get("year"),datemap.get("month")});
        for(Map<String,Object> map : datalist){
            list.add(getVoucherAmountPO(map));
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
    public int getVoucherNumber() {
        sqlManager.getConnection();

        String sql = "SELECT count(v_id) as num from voucher";
        Map<String, Object> map = sqlManager.querySimple(sql,new Object[]{});
        int result = Integer.parseInt(map.get("num").toString());
        sqlManager.releaseAll();

        return result;
    }

    @Override
    public String getEarliestTime() {
        sqlManager.getConnection();

        String sql = "SELECT min(date) as earlytime from voucher";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{});
        String[] time = map.get("earlytime").toString().split("-");
        String result = time[0]+ "-" + time[1];
        sqlManager.releaseAll();
        return result;
    }

    @Override
    public String getLatestTime() {
        sqlManager.getConnection();

        String sql = "SELECT max(date) as latetime from voucher";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{});
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
}
