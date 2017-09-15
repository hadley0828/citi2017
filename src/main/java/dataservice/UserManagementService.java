package dataservice;

import po.AccountSetPO;
import po.UserCompanyPO;
import po.UserFinancialPO;
import util.EnumPackage.ResultMessage;
import vo.userManagement.UserVO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/8 上午7:44
 */
public interface UserManagementService {


    /**
     * 增加一个企业用户PO
     * @param po
     * @return
     */
    public ResultMessage insertOneCompanyUser(UserCompanyPO po);

    /**
     * 增加一个金融机构用户PO
     * @param po
     * @return
     */
    public ResultMessage insertOneFinancialUser(UserFinancialPO po);

    /**
     *
     * @param id
     * @param rawpassword
     * @param newpassword
     * @return
     */
    public ResultMessage modifyPassword(String id,String rawpassword,String newpassword);

    /**
     * 获得一个企业用户PO
     * @param id
     * @return
     */
    public UserCompanyPO getOneCompanyUser(String id);

    /**
     * 获得一个金融机构用户PO
     * @param id
     * @return
     */
    public UserFinancialPO getOneFinancialUser(String id);


    /**
     * 新增一个账套PO
     * @param po
     * @return
     */
    public ArrayList<String> insertOneAccountSet(AccountSetPO po);

    /**
     * 修改一个账套PO
     * @param po
     * @return
     */
    public ResultMessage modifyOneAccountSet(AccountSetPO po);



    /**
     * 根据用户id获得账套信息
     * @param userID
     * @return
     */
    public AccountSetPO getAccountSetByUserID(String userID);

    /**
     * 根据公司id获得账套信息
     * @param companyID
     * @return
     */
    public AccountSetPO getAccountSetByCompanyID(String companyID);

    /**
     * 登录
     * @param id
     * @param password
     * @return
     */
    public ResultMessage loginIn(String id,String password);


    /**
     *
     * @param id
     * @param password
     * @return
     */
    public ResultMessage insertPassword(String id,String password);

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


    public void updateUserInfo(String user_id, String account_id,String company_id);
}
