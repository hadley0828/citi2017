package data;

import dataservice.UserManagementService;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/8 上午8:04
 */
public class UserManagementServiceImpl implements UserManagementService {

    SqlManager sqlManager = SqlManager.getSqlManager();




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
