package businesslogic;

import businesslogicservice.InventoryManagementService;
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
    InventoryManagementService inventoryservice;

    public SettingImpl() {
        userdataservice = new UserManagementServiceImpl();
        settingDataService = new SettingDataServiceImpl();
        inventoryservice = new InventoryManagementImpl();
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
        return settingDataService.getAllSubjects();
    }

    @Override
    public ArrayList<UserVO> getAllUserVoByAccountId(String account_id) {
        return userdataservice.getAllUserVoByAccountId(account_id);
    }

    @Override
    public boolean setInitialSubjects(ArrayList<SubjectsInitialVO> list, String company_id) {
        return settingDataService.setInitialSubjects(list,company_id);
    }

    @Override
    public boolean setSafetyInventory(ArrayList<SafeInventoryVo> list, String company_id) {
        try{
            inventoryservice.SaveSafeInventory(company_id,list);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
