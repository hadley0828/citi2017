package dataTest;

import data.ProfitAndCashServiceImpl;
import dataservice.ProfitAndCashService;
import org.junit.Before;
import org.junit.Test;
import po.VoucherAmountPO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loohaze on 2017/8/13.
 */
public class ProfitAndCashServiceImplTest {

    private ProfitAndCashService profitAndCashService;

    @Before
    public void setUp() throws Exception{
        profitAndCashService = new ProfitAndCashServiceImpl();
    }
//
    @Test
    public void testGetVourchersByYear(){
        String year = "2017";
        String account_id = "1001";
        String company_id = "001";
        ArrayList<VoucherAmountPO> list = (ArrayList<VoucherAmountPO>) profitAndCashService.getVourchersByYear(year,company_id);
        for (VoucherAmountPO po : list){
            System.out.println(po.getV_id() + " " +po.getA_id() + " " +po.getSubject());
        }

    }
//
    @Test
    public void testGetVourchersByPeriod(){
        String period = "2017-08";
        String account_id = "1001";
        ArrayList<VoucherAmountPO> list = (ArrayList<VoucherAmountPO>) profitAndCashService.getVourchersByPeriod(period,"001");
        for (VoucherAmountPO po : list){
            System.out.println(po.getV_id() + " " +po.getA_id() + " " +po.getSubject());
        }
    }
//
    @Test
    public void testGetVourchersBefore(){
        String period = "2017-09";
        String account_id = "1001";
        ArrayList<VoucherAmountPO> list = (ArrayList<VoucherAmountPO>) profitAndCashService.getVourchersBefore(period,"001");
        for (VoucherAmountPO po : list){
            System.out.println(po.getV_id() + " " +po.getA_id() + " " +po.getSubject());
        }
    }
//
    @Test
    public void testGetGivenVourchers(){
        String time = "2017-09";
        String id1 = "1001";
        String id2 = "1002";
        List<Double> list = profitAndCashService.getGivenVourchers(time,id1,id2,"001");
        for (double minNum : list){
            System.out.println(minNum);
        }
    }

    @Test
    public void testGetGivenVourchersByYear(){
        String time = "2017";
        String id1 = "1001";
        String id2 = "1002";
        List<Double> list = profitAndCashService.getGivenVourchersByYear(time,id1,id2,"001");
        for (double minNum : list){
            System.out.println(minNum);
        }
    }
}
