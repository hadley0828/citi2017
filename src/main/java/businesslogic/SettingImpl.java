package businesslogic;

import businesslogicservice.SettingService;
import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsInitialVO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/13 上午11:00
 */
public class SettingImpl implements SettingService{
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
        return null;
    }

    @Override
    public ArrayList<String> getAllSubIndustry(String superIndustry) {
        return null;
    }
}
