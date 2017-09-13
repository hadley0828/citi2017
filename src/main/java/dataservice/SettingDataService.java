package dataservice;

import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsInitialVO;
import vo.userManagement.SubjectsVO;
import vo.userManagement.UserVO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/13 下午6:47
 */
public interface SettingDataService {

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

    /**
     * 获得所有科目
     * @return
     */
    public ArrayList<SubjectsVO> getAllSubjects();


    /**
     * 根据账套ID获得所有USER
     * @param account_id
     * @return
     */
    public ArrayList<UserVO> getAllUserVoByAccountId(String account_id);
}
