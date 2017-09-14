package businesslogicservice;

import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsInitialVO;
import vo.userManagement.SubjectsVO;
import vo.userManagement.UserVO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/11 下午9:33
 */
public interface SettingService {



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


    public boolean setInitialSubjects(ArrayList<SubjectsInitialVO> list,String company_id);

    public boolean setSafetyInventory(ArrayList<SafeInventoryVo> list,String company_id);

    public boolean setSupplyChain(String company_id,String chainindex,String upper,String down);
}
