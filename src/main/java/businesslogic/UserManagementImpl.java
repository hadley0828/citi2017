package businesslogic;

import businesslogicservice.UserManagementService;
import data.UserManagementServiceImpl;
import po.UserCompanyPO;
import util.EnumPackage.ResultMessage;
import vo.userManagement.AccountSetVO;
import vo.userManagement.FinancialUserVO;
import vo.userManagement.UserVO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/12 上午1:28
 */
public class UserManagementImpl implements UserManagementService{

    private dataservice.UserManagementService dataservice;

    public UserManagementImpl() {
        dataservice = new UserManagementServiceImpl();
    }



    @Override
    public ResultMessage insertOneCompanyUser(UserVO vo, String password) {
        return null;
    }

    @Override
    public ResultMessage insertOneFinancialUser(FinancialUserVO vo, String password) {
        return null;
    }

    @Override
    public ResultMessage modifyPassword(String id, String rawpassword, String newpassword) {
        return null;
    }

    @Override
    public UserVO getOneCompanyUser(String id) {
        return null;
    }

    @Override
    public FinancialUserVO getOneFinancialUser(String id) {
        return null;
    }

    @Override
    public ResultMessage register(AccountSetVO vo) {
        return null;
    }

    @Override
    public ResultMessage modifyAccountSet(AccountSetVO vo) {
        return null;
    }

    @Override
    public AccountSetVO getAccountSetByUserID(String userID) {
        return null;
    }

    @Override
    public AccountSetVO getAccountSetByCompanyID(String companyID) {
        return null;
    }



    @Override
    public ResultMessage loginIn(String id, String password) {
        return null;
    }

    @Override
    public Boolean isCompanyUser(String id) {
        return dataservice.isCompanyUser(id);
    }

    @Override
    public Boolean isFinancialUser(String id) {
        return dataservice.isFinancialUser(id);
    }



    private UserCompanyPO UserCompanyVO2PO(UserVO vo){
        UserCompanyPO po = new UserCompanyPO();
        po.setUserID(vo.getUserID());
        po.setCompanyID(vo.getCompanyID());
        po.setType(vo.getType());
        po.setAccountID(vo.getAccountID());

        return po;
    }
}
