package dataTest;

import data.InventoryServiceImpl;
import dataservice.InventoryService;
import org.junit.Before;
import org.junit.Test;
import vo.RawMaterialInventoryItemVo;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/6 上午10:14
 */
public class InventoryDataServiceImplTest {

    private InventoryService inventoryService;

    @Before
    public void init(){
        inventoryService = new InventoryServiceImpl();
    }

    @Test
    public void testSaveSupplierInformationEntry(){
        String sender = "sender1";
        String receiver = "receiver1";
        ArrayList<RawMaterialInventoryItemVo> list = new ArrayList<>();
        RawMaterialInventoryItemVo vo1 = new RawMaterialInventoryItemVo();
        vo1.setRawMaterial_variety("木头");
        vo1.setVoucher_id("借-1");
        vo1.setDatetime(new Timestamp(System.currentTimeMillis()).toString());
        vo1.setIs_delivery_ontime(true);
        vo1.setIs_return(false);
        list.add(vo1);

        inventoryService.SaveSupplierInformationEntry(sender,receiver,list);
    }
}
