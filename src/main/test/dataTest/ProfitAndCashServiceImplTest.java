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

    @Test
    public void testGetVourchersByYear(){
        String year = "2012";
        String account_id = "1001";
        ArrayList<VoucherAmountPO> list = (ArrayList<VoucherAmountPO>) profitAndCashService.getVourchersByYear(year,account_id);
        for (VoucherAmountPO po : list){
            System.out.println(po.getV_id() + " " +po.getA_id() + " " +po.getSubject());
        }

    }

    @Test
    public void testGetVourchersByPeriod(){
        String period = "2012-04";
        String account_id = "1001";
        ArrayList<VoucherAmountPO> list = (ArrayList<VoucherAmountPO>) profitAndCashService.getVourchersByPeriod(period,account_id);
        for (VoucherAmountPO po : list){
            System.out.println(po.getV_id() + " " +po.getA_id() + " " +po.getSubject());
        }
    }

    @Test
    public void testGetVourchersBefore(){
        String period = "2012-07";
        String account_id = "1001";
        ArrayList<VoucherAmountPO> list = (ArrayList<VoucherAmountPO>) profitAndCashService.getVourchersBefore(period,account_id);
        for (VoucherAmountPO po : list){
            System.out.println(po.getV_id() + " " +po.getA_id() + " " +po.getSubject());
        }
    }

    @Test
    public void testGetGivenVourchers(){
        String time = "2012-04";
        String id1 = "1001";
        String id2 = "1002";
        List<Double> list = profitAndCashService.getGivenVourchers(time,id1,id2);
        for (double minNum : list){
            System.out.println(minNum);
        }
    }

    @Test
    public void testGetGivenVourchersByYear(){
        String time = "2012";
        String id1 = "1001";
        String id2 = "1002";
        List<Double> list = profitAndCashService.getGivenVourchersByYear(time,id1,id2);
        for (double minNum : list){
            System.out.println(minNum);
        }
    }
}
