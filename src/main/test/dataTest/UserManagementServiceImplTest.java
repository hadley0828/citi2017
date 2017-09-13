package dataTest;

import data.UserManagementServiceImpl;
import dataservice.UserManagementService;
import org.junit.Before;
import org.junit.Test;
import po.AccountSetPO;
import po.UserCompanyPO;
import po.UserFinancialPO;
import util.EnumPackage.ResultMessage;

import java.sql.Date;
import java.util.ArrayList;

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
    public void testinsertOneCompanyUser(){
        UserCompanyPO po1 = new UserCompanyPO("01","12312","normal","001");
        UserCompanyPO po2 = new UserCompanyPO("02","12312","normal","001");
        UserCompanyPO po3 = new UserCompanyPO("0002","12412","normal","001");
        assertEquals(ResultMessage.EXIST_USERID,userManagementService.insertOneCompanyUser(po1));
        assertEquals(ResultMessage.EXIST_USERID,userManagementService.insertOneCompanyUser(po2));

    }

    @Test
    public void testinsertOneFinancialUser(){
        UserFinancialPO po1 = new UserFinancialPO("01","ff","xxxx","wang","1241241","24j21k41");
        UserFinancialPO po2 = new UserFinancialPO("0004","13412","asdxa","li","124124","21h214k23");
        assertEquals(ResultMessage.EXIST_USERID,userManagementService.insertOneFinancialUser(po1));
//        assertEquals(ResultMessage.REGISTER_SUCCESS,userManagementService.insertOneFinancialUser(po2));
    }

    @Test
    public void testmodifyPassword(){
        System.out.println(userManagementService.modifyPassword("01","1312412","000000").toString());
    }

    @Test
    public void testgetOneCompanyUser(){
        UserCompanyPO po = userManagementService.getOneCompanyUser("01","001");
        print(po);
    }

    @Test
    public void testgetOneFinancialUser(){
        UserFinancialPO po = userManagementService.getOneFinancialUser("02");
        print(po);
    }

    @Test
    public void testinsertOneAccountSet(){
        AccountSetPO po = new AccountSetPO();
        po.setAccountID("a1");
        po.setCompanyID("001");
        po.setCompanyName("xxx");
        po.setLocation("NANJING");
        po.setIndustry("钢铁行业");
        po.setDate(Date.valueOf("2017-09-01"));
        po.setCreditCode("14125124");
        po.setContact("125311@qq.com");
        System.out.println(userManagementService.insertOneAccountSet(po).toString());
    }

    @Test
    public void testmodifyOneAccountSet(){
        AccountSetPO po = new AccountSetPO();
        po.setCompanyName("gyyy");
        po.setLocation("SHENZHEN");
        po.setContact("124124123412@gmail.com");
        po.setAccountID("a1");
        po.setCompanyID("001");

        System.out.println(userManagementService.modifyOneAccountSet(po).toString());
    }


    @Test
    public void testgetAllSuperIndustry(){
        ArrayList<String> list = userManagementService.getAllSuperIndustry();
        for (String s : list){
            System.out.println(s);
        }
    }

    @Test
    public void testgetAllSubIndustry(){
        ArrayList<String> list = userManagementService.getAllSubIndustry("工业");
        for (String s : list){
            System.out.println(s);
        }
    }

    @Test
    public void testisCompanyUser(){
        System.out.println(userManagementService.isCompanyUser("01"));
        System.out.println(userManagementService.isCompanyUser("02"));
        System.out.println(userManagementService.isFinancialUser("02"));
    }

    private void print(UserCompanyPO po){
        System.out.print(po.getUserID() + "\t");
        System.out.print(po.getAccountID()+ "\t");
        System.out.print(po.getType()+ "\t");
        System.out.println(po.getCompanyID() + "\t");
    }

    private void print(UserFinancialPO po){
        System.out.print(po.getUserID() + "\t");
        System.out.print(po.getName() + "\t");
        System.out.print(po.getAddress() + "\t");
        System.out.print(po.getLegalPerson() + "\t");
        System.out.print(po.getFinancialKey() + "\t");
        System.out.println(po.getLegalPersonQualification() + "\t");
    }
}
