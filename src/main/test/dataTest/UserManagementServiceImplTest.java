package dataTest;

import data.UserManagementServiceImpl;
import dataservice.UserManagementService;
import org.junit.Before;

/**
 * Created by loohaze on 2017/9/11 下午1:38
 */
public class UserManagementServiceImplTest {

    UserManagementService userManagementService;

    @Before
    public void setUp(){
        userManagementService = new UserManagementServiceImpl();
    }


}
