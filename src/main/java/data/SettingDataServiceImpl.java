package data;

import dataservice.SettingDataService;
import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsInitialVO;
import vo.userManagement.SubjectsVO;
import vo.userManagement.UserVO;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/13 下午6:48
 */
public class SettingDataServiceImpl implements SettingDataService{

    SqlManager sqlManager = SqlManager.getSqlManager();


    @Override
    public ArrayList<SubjectsInitialVO> setInitialSubjectsBalance(String companyID) {

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

        return null;
    }

    @Override
    public ArrayList<UserVO> getAllUserVoByAccountId(String account_id) {
        return null;
    }
}
