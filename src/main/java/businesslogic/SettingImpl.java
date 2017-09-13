package businesslogic;

import businesslogicservice.SettingService;
import data.SettingDataServiceImpl;
import data.UserManagementServiceImpl;
import dataservice.SettingDataService;
import dataservice.UserManagementService;
import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsInitialVO;
import vo.userManagement.SubjectsVO;
import vo.userManagement.UserVO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/13 上午11:00
 */
public class SettingImpl implements SettingService{

    UserManagementService userdataservice;
    SettingDataService settingDataService;

    public SettingImpl() {
        userdataservice = new UserManagementServiceImpl();
        settingDataService = new SettingDataServiceImpl();
    }

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
        return settingDataService.getAllSuperIndustry();
    }

    @Override
    public ArrayList<String> getAllSubIndustry(String superIndustry) {
        return settingDataService.getAllSubIndustry(superIndustry);
    }

    @Override
    public ArrayList<SubjectsVO> getAllSubjects() {
        return null;
    }

    @Override
    public ArrayList<UserVO> getAllUserVoByAccountId(String account_id) {
        return null;
    }
}
