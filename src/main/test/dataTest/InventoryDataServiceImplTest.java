package dataTest;

import data.InventoryServiceImpl;
import dataservice.InventoryService;
import org.junit.Before;
import org.junit.Test;
import po.Inventory.InventoryProductItemPO;
import po.Inventory.InventoryRawMaterialItemPO;

import java.sql.Timestamp;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

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
    public void testGetRawInventoryBySupplier(){
        assertEquals(30,inventoryService.getRawInventoryBySupplier("001","借-1","cotton"));
    }

    @Test
    public void testGetMaxSheetID(){
        System.out.println(inventoryService.getMaxSheetId("001"));
    }

    @Test
    public void testSaveRawMaterialInventoryItem(){
        ArrayList<InventoryRawMaterialItemPO> list = new ArrayList<>();

        InventoryRawMaterialItemPO po1 = new InventoryRawMaterialItemPO(1,0,"001","steel","记-1", Timestamp.valueOf("2017-09-10 00:00:00"),false,false,100,100,10000,0,0,0,100);
        InventoryRawMaterialItemPO po2 = new InventoryRawMaterialItemPO(1,1,"001","xxx","记-1",Timestamp.valueOf("2017-09-10 00:00:00"),false,false,200,100,20000,0,0,0,200);
        list.add(po1);
        list.add(po2);

        inventoryService.SaveRawMaterialInventoryItem(list);
    }


    @Test
    public void testSaveProductInventoryItem(){
        ArrayList<InventoryProductItemPO> list = new ArrayList<>();

        InventoryProductItemPO po1 = new InventoryProductItemPO(3,0,"001","pen","记-3", Timestamp.valueOf("2017-09-11 00:00:00"),false,false,0,0,0,1000,10,10000,0);
        list.add(po1);

        inventoryService.SaveProductInventoryItem(list);
    }
}
