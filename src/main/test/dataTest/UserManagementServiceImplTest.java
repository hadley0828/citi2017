package dataTest;

import data.UserManagementServiceImpl;
import dataservice.UserManagementService;
import org.junit.Before;
import org.junit.Test;
import po.AccountPassword;
import po.AccountSetPO;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by loohaze on 2017/9/11 下午1:38
 */
public class UserManagementServiceImplTest {

    UserManagementService userManagementService;

    @Before
    public void setUp(){
        userManagementService = new UserManagementServiceImpl();
    }


    @Test
    public void testregister(){
        AccountPassword password = new AccountPassword();
        AccountSetPO set = new AccountSetPO();

        password.setCompanyID("001");
        password.setPassword("001001");

        set.setCompanyId("001");
        set.setCompanyName("001Company");
        set.setIndustry("服装业");
        set.setLocation("Nanjing");
        set.setStartDate(Date.valueOf("2017-09-01"));
        set.setCreditCode("123124");
        set.setContact("124123123");

        System.out.println(userManagementService.register(password,set));
    }

    @Test
    public void testsignIn(){
        String id = "001";
        String password = "001001";
        assertEquals(true,userManagementService.signIn(id,password));
        assertEquals(false,userManagementService.signIn(id,"23"));
    }
}
