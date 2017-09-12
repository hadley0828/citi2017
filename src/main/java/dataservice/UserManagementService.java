package dataservice;

import po.AccountSetPO;
import po.UserCompanyPO;
import po.UserFinancialPO;
import util.ResultMessage;

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
    public ResultMessage insertOneAccountSet(AccountSetPO po);

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
     * @param type
     * @return
     */
    public ResultMessage loginIn(String id,String password,String type);
}
