package data;

import dataservice.UserManagementService;
import po.AccountPassword;
import po.AccountSetPO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/8 上午8:04
 */
public class UserManagementServiceImpl implements UserManagementService {

    SqlManager sqlManager = SqlManager.getSqlManager();

    @Override
    public boolean register(AccountPassword password, AccountSetPO accountSetPO) {
        if(!password.getCompanyID().equals(accountSetPO.getCompanyId())){
            return false;
        }
        sqlManager.getConnection();

        ArrayList<String> idList = getAllCompanyID();
        if (idList.contains(accountSetPO.getCompanyId())){
            return false;
        }

        List<Object> params = new ArrayList<>();
        params.add(accountSetPO.getCompanyId());
        params.add(accountSetPO.getCompanyName());
        params.add(accountSetPO.getLocation());
        params.add(accountSetPO.getIndustry());
        params.add(accountSetPO.getStartDate());
        params.add(accountSetPO.getCreditCode());
        params.add(accountSetPO.getContact());

        List<Object> params2 = new ArrayList<>();
        params2.add(password.getCompanyID());
        params2.add(password.getPassword());

        String sql = sqlManager.appendSQL("insert into account_set values",params.size());
        String sql2 = sqlManager.appendSQL("insert into account_password values",params2.size());


        try{
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.executeUpdateByList(sql2,params2);
            sqlManager.releaseAll();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean signIn(String id, String password) {
        sqlManager.getConnection();

        String sql = "select * from account_password where company_id=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{id});

        sqlManager.releaseAll();
        if (password.equals(map.get("password").toString())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void modifyPassword(String id, String rawPassword, String newPassword) {
        sqlManager.getConnection();

        List<Object> params = new ArrayList<>();
        params.add(newPassword);
        params.add(id);
        params.add(rawPassword);

        String sql = "update account_password set password=? where company_id=? and password=?";
        sqlManager.executeUpdateByList(sql,params);
        sqlManager.releaseAll();
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
