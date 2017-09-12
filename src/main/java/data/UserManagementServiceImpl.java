package data;

import dataservice.UserManagementService;
import po.AccountSetPO;
import po.UserCompanyPO;
import po.UserFinancialPO;
import util.EnumPackage.ResultMessage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/8 上午8:04
 */
public class UserManagementServiceImpl implements UserManagementService {

    SqlManager sqlManager = SqlManager.getSqlManager();


    @Override
    public ResultMessage insertOneCompanyUser(UserCompanyPO po) {
        sqlManager.getConnection();
        ArrayList<String> idList = getAllUserIDByCompany(po.getCompanyID());
        if (idList.contains(po.getUserID())){
            return ResultMessage.EXIST_USERID;
        }
        List<Object> params = new ArrayList<>();

        params.add(po.getUserID());
        params.add(po.getAccountID());
        params.add(po.getType());
        params.add(po.getCompanyID());
        String sql = sqlManager.appendSQL("insert into user_company values",params.size());
        sqlManager.executeUpdateByList(sql,params);

        sqlManager.releaseAll();
        return ResultMessage.REGISTER_SUCCESS;
    }

    @Override
    public ResultMessage insertOneFinancialUser(UserFinancialPO po) {
        sqlManager.getConnection();
        ArrayList<String> idList = getALLUserId();
        if (idList.contains(po.getUserID())){
            return ResultMessage.EXIST_USERID;
        }

        List<Object> params = new ArrayList<>();

        params.add(po.getUserID());
        params.add(po.getName());
        params.add(po.getAddress());
        params.add(po.getLegalPerson());
        params.add(po.getFinancialKey());
        params.add(po.getLegalPersonQualification());
        String sql = sqlManager.appendSQL("insert into user_financial values",params.size());
        sqlManager.executeUpdateByList(sql,params);

        sqlManager.releaseAll();
        return ResultMessage.REGISTER_SUCCESS;
    }

    @Override
    public ResultMessage modifyPassword(String id, String rawpassword, String newpassword) {
        sqlManager.getConnection();
        if (rawpassword.equals(newpassword)){
            return ResultMessage.SAME_PASSWORD;
        }
        List<Object> params = new ArrayList<>();
        params.add(newpassword);
        params.add(id);
        params.add(rawpassword);

        String sql = "update user_password set password=? where id=? and password=?";
        try{
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return ResultMessage.SUCCESS;
        }catch (Exception e){
            sqlManager.releaseAll();
            return ResultMessage.FAIL;
        }

    }

    @Override
    public UserCompanyPO getOneCompanyUser(String id,String company_id) {
        sqlManager.getConnection();

        String sql = "select * from user_company where id=? and company_id=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{id,company_id});

        UserCompanyPO po = new UserCompanyPO();
        po.setUserID(map.get("id").toString());
        po.setAccountID(map.get("account_id").toString());
        po.setType(map.get("type").toString());
        po.setCompanyID(map.get("company_id").toString());

        sqlManager.releaseAll();
        return po;
    }

    @Override
    public UserFinancialPO getOneFinancialUser(String id) {

        sqlManager.getConnection();

        String sql = "select * from user_financial where id=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{id});

        UserFinancialPO po = new UserFinancialPO();
        po.setUserID(map.get("id").toString());
        po.setName(map.get("name").toString());
        po.setAddress(map.get("address").toString());
        po.setLegalPerson(map.get("legal_person").toString());
        po.setFinancialKey(map.get("financial_key").toString());
        po.setLegalPersonQualification(map.get("legal_person_qualification").toString());

        sqlManager.releaseAll();
        return po;
    }

    @Override
    public ResultMessage insertOneAccountSet(AccountSetPO po) {
        sqlManager.getConnection();

        List<Object> params = new ArrayList<>();
        params.add(po.getAccountID());
        params.add(po.getCompanyID());
        params.add(po.getCompanyName());
        params.add(po.getLocation());
        params.add(po.getIndustry());
        params.add(po.getDate());
        params.add(po.getCreditCode());
        params.add(po.getContact());


        try {
            String sql = sqlManager.appendSQL("insert into account_set values",params.size());
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();

            return ResultMessage.SUCCESS;
        }catch (Exception e){
            sqlManager.releaseAll();
            return ResultMessage.FAIL;
        }

    }

    @Override
    public ResultMessage modifyOneAccountSet(AccountSetPO po) {
        sqlManager.getConnection();

        List<Object> params = new ArrayList<>();

        params.add(po.getCompanyName());
        params.add(po.getLocation());
        params.add(po.getContact());
        params.add(po.getAccountID());
        params.add(po.getCompanyID());

        try{
            String sql = "update account_set set company_name=?,location=?,contact=? where account_id=? and company_id=?";
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return ResultMessage.SUCCESS;
        }catch (Exception e){
            sqlManager.releaseAll();
            return ResultMessage.FAIL;
        }
    }

    @Override
    public AccountSetPO getAccountSetByCompanyID(String companyID) {
        sqlManager.getConnection();

        String sql = "select * from account_set where company_id=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{companyID});

        AccountSetPO po = new AccountSetPO();
        po.setCompanyID(map.get("company_id").toString());
        po.setAccountID(map.get("account_id").toString());
        po.setCompanyName(map.get("company_name").toString());
        po.setLocation(map.get("location").toString());
        po.setIndustry(map.get("industry").toString());
        po.setDate(Date.valueOf(map.get("date").toString()));
        po.setCreditCode(map.get("credit_code").toString());
        po.setContact(map.get("contact").toString());

        sqlManager.releaseAll();
        return po;
    }

    @Override
    public ResultMessage loginIn(String id, String password) {
        sqlManager.getConnection();

        String sql = "select password from user_password where id=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{password});

        if (password.equals(map.get("password").toString())){
            return ResultMessage.SUCCESS;
        }else {
            return ResultMessage.FAIL;
        }
    }

    @Override
    public ArrayList<String> getAllSuperIndustry() {
        sqlManager.getConnection();

        ArrayList<String> list = new ArrayList<>();

        String sql = "select distinct super_industry from industry";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{});

        for (Map<String,Object> map : maps){
            list.add(map.get("super_industry").toString());
        }

        sqlManager.releaseAll();
        return list;

    }

    @Override
    public ArrayList<String> getAllSubIndustry(String superIndustry){
        sqlManager.getConnection();

        ArrayList<String> list = new ArrayList<>();

        String sql = "select sub_industry from industry where super_industry=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{superIndustry});

        for (Map<String,Object> map : maps){
            list.add(map.get("sub_industry").toString());
        }
        sqlManager.releaseAll();
        return list;
    }


    @Override
    public ResultMessage insertPassword(String id, String password) {
        sqlManager.getConnection();
        if (password.length()<6){
            return ResultMessage.SHORT_PASSWORD;
        }
        List<Object> params = new ArrayList<>();
        params.add(id);
        params.add(password);

        String sql = sqlManager.appendSQL("insert into user_password values",params.size());

        try{
            sqlManager.executeUpdateByList(sql,params);
            sqlManager.releaseAll();
            return ResultMessage.SUCCESS;
        }catch (Exception e){
            sqlManager.releaseAll();
            return ResultMessage.FAIL;
        }
    }

    private ArrayList<String> getAllUserIDByCompany(String company_id){
        ArrayList<String> list = new ArrayList<>();
        String sql = "select id from user_company where company_id=? union all select id from user_financial";

        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id});
        for (Map<String,Object> map : maps){
            list.add(map.get("id").toString());
        }

        return list;
    }

    private ArrayList<String> getALLUserId(){
        ArrayList<String> list = new ArrayList<>();
        String sql = "select id from user_company union all select id from user_financial";

        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{});
        for (Map<String,Object> map : maps){
            list.add(map.get("id").toString());
        }

        return list;
    }
}
