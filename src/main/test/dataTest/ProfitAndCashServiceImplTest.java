package dataTest;

import data.ProfitAndCashServiceImpl;
import dataservice.ProfitAndCashService;
import org.junit.Before;
import org.junit.Test;
import po.VoucherAmountPO;

import java.util.ArrayList;

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
}
