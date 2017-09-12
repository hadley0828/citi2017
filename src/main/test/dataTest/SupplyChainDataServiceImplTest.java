package dataTest;

import data.SupplyChainDataServiceImpl;
import dataservice.SupplyChainDataService;
import org.junit.Before;
import org.junit.Test;
import po.SupplyChainPO;

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

    }
}
