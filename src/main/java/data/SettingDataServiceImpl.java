package data;

import dataservice.SettingDataService;
import vo.userManagement.SubjectsInitialVO;
import vo.userManagement.SubjectsVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/13 下午6:48
 */
public class SettingDataServiceImpl implements SettingDataService{

    SqlManager sqlManager = SqlManager.getSqlManager();



    @Override
    public ArrayList<String> getAllSuperIndustry() {
        sqlManager.getConnection();

        ArrayList<String> list = new ArrayList<>();

        String sql = "select distinct super_industry from industry";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{});

        for (Map<String,Object> map : maps){
            list.add(map.get("super_industry").toString());
        }

        sqlManager.releaseAll();
        return list;

    }

    @Override
    public ArrayList<String> getAllSubIndustry(String superIndustry){
        sqlManager.getConnection();

        ArrayList<String> list = new ArrayList<>();

        String sql = "select sub_industry from industry where super_industry=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{superIndustry});

        for (Map<String,Object> map : maps){
            list.add(map.get("sub_industry").toString());
        }
        sqlManager.releaseAll();
        return list;
    }

    @Override
    public ArrayList<SubjectsVO> getAllSubjects() {
        sqlManager.getConnection();

        ArrayList<SubjectsVO> list = new ArrayList<>();
        String sql = "select * from subjects";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{});

        for (Map<String,Object> map : maps){
            list.add(getSubjectsVOByMap(map));
        }
        sqlManager.releaseAll();
        return list;
    }

    @Override
    public boolean setInitialSubjects(ArrayList<SubjectsInitialVO> list, String company_id) {
        sqlManager.getConnection();

        for (SubjectsInitialVO vo : list){
            insertOneSubjectInitialVO(vo,company_id);
        }
        sqlManager.releaseAll();
        return true;
    }

    @Override
    public boolean setSupplyChain(String company_id, String index, String upper, String down) {
        sqlManager.getConnection();

        String getsql = "select * from supply_chain where upper_id=? or middle_id=? or down_id=?";
        Map<String,Object> map = sqlManager.querySimple(getsql,new Object[]{company_id,company_id,company_id});
        if (map.isEmpty()){
            List<Object> params = new ArrayList<>();
            params.add(0);
            String sql = "";
            try{
                if (index.equals("供应商")){
                    params.add(company_id);
                    params.add(down);
                    params.add("");

                }else if(index.equals("生产商")){
                    params.add(upper);
                    params.add(company_id);
                    params.add(down);
                }else if(index.equals("分销商")){
                    params.add("");
                    params.add(upper);
                    params.add(company_id);
                }
                sql = sqlManager.appendSQL("insert into supply_chain values",params.size());
                sqlManager.executeUpdateByList(sql,params);
                sqlManager.commit();
                return true;
            }catch (Exception e){
                return false;
            }
        }else{
            String sql = "";
            List<Object> params = new ArrayList<>();
            try {
                if (index.equals("供应商")){
                    sql = "update supply_chain set middle_id=? where upper_id=?";
                    params.add(down);
                    params.add(company_id);
                }else if (index.equals("生产商")){
                    sql = "update supply_chain set upper_id=?,down_id=? where middle_id=?";
                    params.add(upper);
                    params.add(down);
                    params.add(company_id);

                }else if (index.equals("分销商")){
                    sql = "update supply_chain set middle_id=? where down_id=?";
                    params.add(upper);
                    params.add(company_id);
                }
                sqlManager.executeUpdateByList(sql,params);
                sqlManager.commit();
                return true;
            }catch (Exception e){
                return false;
            }
        }

    }


    private SubjectsVO getSubjectsVOByMap(Map<String,Object> map){
        SubjectsVO vo = new SubjectsVO();
        vo.setSubjectsID(map.get("subjects_id").toString());
        vo.setSubjectsName(map.get("subjects_name").toString());
        vo.setDirection(map.get("direction").toString());
        vo.setType(map.get("type").toString());
        return vo;
    }

    private void insertOneSubjectInitialVO(SubjectsInitialVO vo,String company_id){
        List<Object> params = new ArrayList<>();
        params.add(vo.getSubejcts_id());
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = sdf.format(date);
        params.add(dateNow);
        params.add(vo.getPeroidRemain());
        params.add(vo.getDebitSum());
        params.add(vo.getCreditSum());
        params.add(company_id);
        params.add("initialization");

        String sql = sqlManager.appendSQL("insert into subjects_balance values",params.size());
        sqlManager.executeUpdateByList(sql,params);
    }
}
