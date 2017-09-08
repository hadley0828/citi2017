package data;

import dataservice.UserManagementService;
import po.AccountPassword;
import po.AccountSetPO;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/8 上午8:04
 */
public class UserManagementServiceImpl implements UserManagementService {

    SqlManager sqlManager = SqlManager.getSqlManager();

    @Override
    public boolean register(AccountPassword password, AccountSetPO accountSetPO) {
        sqlManager.getConnection();


        return false;
    }

    @Override
    public boolean signIn(String id, String password) {
        return false;
    }

    private ArrayList<String> getAllCompanyID(){
        return null;
    }
}
