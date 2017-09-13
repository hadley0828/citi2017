package dataTest;

import data.SupplyChainDataServiceImpl;
import dataservice.SupplyChainDataService;
import org.junit.Before;
import org.junit.Test;
import po.SupplyChainPO;
import po.VoucherAmountPO;

import java.util.List;

/**
 * Created by loohaze on 2017/9/12 下午9:37
 */
public class SupplyChainDataServiceImplTest {

    SupplyChainDataService supplyChainDataService;

    @Before
    public void init(){
        supplyChainDataService = new SupplyChainDataServiceImpl();
    }

    @Test
    public void testgetTheCompanys(){
        String[] chain = supplyChainDataService.getTheCompanys("002");
        System.out.println("供应商：" + chain[0]);
        System.out.println("生产商：" + chain[1]);
        System.out.println("经销商：" + chain[2]);


    }

    @Test
    public void testSaveSupplyChain(){
        SupplyChainPO po = new SupplyChainPO("2017-09-01","gyyy","应收帐款融资",124124,24124);
        supplyChainDataService.SaveSupplyChain(po);
    }

    @Test
    public void testGetSupplyChains(){
        List<SupplyChainPO> list = supplyChainDataService.GetSupplyChains();
        for (SupplyChainPO po : list){
            System.out.println(po.getFinacingType());
        }
    }

    @Test
    public void testgetAcountReceivable(){
        List<String> list = supplyChainDataService.getAcountReceivable("001","2017-09-05");
        for (String s : list){
            System.out.println(s);
        }
    }

    @Test
    public void testgetRawMaterialAndProduct(){
        List<String> list = supplyChainDataService.getRawMaterialAndProduct("001","2017-10-10");
        for (String s : list){
            System.out.println(s);
        }
    }

    @Test
    public void testGetInitial(){
        System.out.println(supplyChainDataService.GetInitial("1012","001"));
    }

    @Test
    public void testGetVoucherAmountsWithCompany(){
        List<VoucherAmountPO> list = supplyChainDataService.GetVoucherAmountsWithCompany("001","1001","应收账款-A公司","2018-10-10");
        for (VoucherAmountPO po : list){
            System.out.println(po.getV_id());
        }
    }

    @Test
    public void testGetVoucherAmountsWithProduct(){
        List<VoucherAmountPO> list = supplyChainDataService.GetVoucherAmountsWithProduct("001","1001","cotton","2018-10-10");
        for (VoucherAmountPO po : list){
            System.out.println(po.getV_id());
        }
    }

}
