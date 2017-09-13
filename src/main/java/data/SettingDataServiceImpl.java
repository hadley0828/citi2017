package data;

import dataservice.SettingDataService;
import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsInitialVO;
import vo.userManagement.SubjectsVO;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/13 下午6:48
 */
public class SettingDataServiceImpl implements SettingDataService{

    SqlManager sqlManager = SqlManager.getSqlManager();


    @Override
    public ArrayList<SubjectsInitialVO> setInitialSubjectsBalance(String companyID) {
        sqlManager.getConnection();


        sqlManager.releaseAll();
        return null;
    }

    @Override
    public ArrayList<SafeInventoryVo> setSafeInventory(String companyID) {
        return null;
    }

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



    private SubjectsVO getSubjectsVOByMap(Map<String,Object> map){
        SubjectsVO vo = new SubjectsVO();
        vo.setSubjectsID(map.get("subjects_id").toString());
        vo.setSubjectsName(map.get("subjects_name").toString());
        vo.setDirection(map.get("direction").toString());
        vo.setType(map.get("type").toString());
        return vo;
    }
}
