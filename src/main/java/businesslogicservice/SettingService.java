package businesslogicservice;

import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsInitialVO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/11 下午9:33
 */
public interface SettingService {

    /**
     * 一个公司的期初设置
     * @param companyID
     * @return
     */
    public ArrayList<SubjectsInitialVO> setInitialSubjectsBalance(String companyID);

    /**
     * 一个公司的安全库存量设置
     * @param companyID
     * @return
     */
    public ArrayList<SafeInventoryVo> setSafeInventory(String companyID);


    /**
     * 获得所有行业
     * @return
     */
    public ArrayList<String> getAllSuperIndustry();

    /**
     *
     * @param superIndustry
     * @return
     */
    public ArrayList<String> getAllSubIndustry(String superIndustry);

}
