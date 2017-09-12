package data;

import dataservice.UserManagementService;
import po.AccountSetPO;
import po.UserCompanyPO;
import po.UserFinancialPO;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/8 上午8:04
 */
public class UserManagementServiceImpl implements UserManagementService {

    SqlManager sqlManager = SqlManager.getSqlManager();


    @Override
    public ResultMessage insertOneCompanyUser(UserCompanyPO po) {

        return null;
    }

    @Override
    public ResultMessage insertOneFinancialUser(UserFinancialPO po) {
        return null;
    }

    @Override
    public ResultMessage modifyPassword(String id, String rawpassword, String newpassword) {
        return null;
    }

    @Override
    public UserCompanyPO getOneCompanyUser(String id) {
        return null;
    }

    @Override
    public UserFinancialPO getOneFinancialUser(String id) {
        return null;
    }

    @Override
    public ResultMessage insertOneAccountSet(AccountSetPO po) {
        return null;
    }

    @Override
    public ResultMessage modifyOneAccountSet(AccountSetPO po) {
        return null;
    }

    @Override
    public AccountSetPO getAccountSetByUserID(String userID) {
        return null;
    }

    @Override
    public AccountSetPO getAccountSetByCompanyID(String companyID) {
        return null;
    }

    @Override
    public ResultMessage loginIn(String id, String password, String type) {
        return null;
    }

    /**
     * 获得所有存在的公司ID
     * @return
     */
    private ArrayList<String> getAllCompanyID(){

        ArrayList<String> list = new ArrayList<>();
        String sql = "select company_id from account_set";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{});

        for (Map<String,Object> map : maps){
            list.add(map.get("company_id").toString());
        }

        return list;
    }

}
