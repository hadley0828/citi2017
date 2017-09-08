package dataservice;

import po.AccountPassword;
import po.AccountSetPO;

/**
 * Created by loohaze on 2017/9/8 上午7:44
 */
public interface UserManagementService {

    /**
     * 注册
     * @return
     */
    public boolean register(AccountPassword password, AccountSetPO accountSetPO);

    /**
     * 登录
     * @param id
     * @param password
     * @return
     */
    public boolean signIn(String id,String password);
}
