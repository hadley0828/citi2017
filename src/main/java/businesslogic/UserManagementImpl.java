package businesslogic;

import businesslogicservice.UserManagementService;
import util.ResultMessage;
import vo.userManagement.AccountSetVO;
import vo.userManagement.FinancialUserVO;
import vo.userManagement.UserVO;

/**
 * Created by loohaze on 2017/9/12 上午1:28
 */
public class UserManagementImpl implements UserManagementService{


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
    public ResultMessage loginIn(String id, String password, String type) {
        return null;
    }
}
