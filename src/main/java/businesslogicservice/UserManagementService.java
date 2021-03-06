package businesslogicservice;

import util.EnumPackage.ResultMessage;
import vo.userManagement.AccountSetVO;
import vo.userManagement.FinancialUserVO;
import vo.userManagement.UserVO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/11 下午9:18
 */
public interface UserManagementService {

    /**
     * 增加一个企业用户VO
     * @param vo
     * @return
     */
    public ResultMessage insertOneCompanyUser(UserVO vo, String password);

    /**
     * 增加一个金融机构用户VO
     * @param vo
     * @return
     */
    public ResultMessage insertOneFinancialUser(FinancialUserVO vo, String password);

    /**
     * 修改密码
     * @param id
     * @param rawpassword
     * @param newpassword
     * @return
     */
    public ResultMessage modifyPassword(String id, String rawpassword,String newpassword);

    /**
     * 获得一个企业用户VO
     * @return
     */
    public UserVO getOneCompanyUser(String id);

    /**
     * 获得一个金融机构用户VO
     * @param id
     * @return
     */
    public FinancialUserVO getOneFinancialUser(String id);

    /**
     * 公司注册
     * @param vo
     * @return
     */
    public ResultMessage register(AccountSetVO vo,String userID);

    /**
     * 修改公司账套信息
     * @param vo
     * @return
     */
    public ResultMessage modifyAccountSet(AccountSetVO vo);

    /**
     * 根据用户id获得账套信息
     * @param userID
     * @return
     */
    public AccountSetVO getAccountSetByUserID(String userID);

    /**
     * 根据公司id获得账套信息
     * @param companyID
     * @return
     */
    public AccountSetVO getAccountSetByCompanyID(String companyID);


    /**
     * 登录 type是企业用户或者金融机构用户
     * @param id
     * @param password
     * @return
     */
    public ResultMessage loginIn(String id, String password);


    /**
     * 判断是否为企业用户
     * @param id
     * @return
     */
    public Boolean isCompanyUser(String id);

    /**
     * 判断是否为金融机构用户
     * @param id
     * @return
     */
    public Boolean isFinancialUser(String id);

    /**
     * 根据账套ID获得所有USER
     * @param account_id
     * @return
     */
    public ArrayList<UserVO> getAllUserVoByAccountId(String account_id);
}
