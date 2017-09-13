package dataTest;

import data.InventoryServiceImpl;
import dataservice.InventoryService;
import org.junit.Before;
import org.junit.Test;
import po.Inventory.InventoryProductItemPO;
import po.Inventory.InventoryRawMaterialItemPO;
import po.Inventory.ProductSafeInventoryPo;
import po.Inventory.RawMaterialSafeInventoryPo;

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

    @Test
    public void testgetRawMaterialInventoryItem(){
        ArrayList<InventoryRawMaterialItemPO> list = new ArrayList<>();
        list = inventoryService.getRawMaterialInventoryItem("001","2017-12-12");

        for (InventoryRawMaterialItemPO po : list){
            print(po);
        }
    }

    @Test
    public void testgetProductInventoryItem(){
        ArrayList<InventoryProductItemPO> list = new ArrayList<>();
        list = inventoryService.getProductInventoryItem("001","2017-12-12");

        for (InventoryProductItemPO po : list){
            print(po);
        }
    }

    @Test
    public void testgetRawMaterialInventoryItemByVariety(){
        ArrayList<InventoryRawMaterialItemPO> list = inventoryService.getRawMaterialInventoryItemByVariety("001","2017-12-12","wood");
        for (InventoryRawMaterialItemPO po : list){
            print(po);
        }
    }

    @Test
    public void testgetProductInventoryItemByVariety(){
        ArrayList<InventoryProductItemPO> list = inventoryService.getProductInventoryItemByVariety("001","2017-12-12","pen");
        for (InventoryProductItemPO po : list){
            print(po);
        }
    }

    @Test
    public void testSaveRawMaterialSafeInventory(){
        RawMaterialSafeInventoryPo po1 = new RawMaterialSafeInventoryPo("001","cotton",10000);
        RawMaterialSafeInventoryPo po2 = new RawMaterialSafeInventoryPo("001","steel",20000);
        ArrayList<RawMaterialSafeInventoryPo> list = new ArrayList<>();
        list.add(po1);
        list.add(po2);
        inventoryService.SaveRawMaterialSafeInventory(list);
    }

    @Test
    public void testSaveProductSafeInventory(){
        ProductSafeInventoryPo po1 = new ProductSafeInventoryPo("001","pen",10000);
        ProductSafeInventoryPo po2 = new ProductSafeInventoryPo("001","pencil",20000);
        ArrayList<ProductSafeInventoryPo> list = new ArrayList<>();
        list.add(po1);
        list.add(po2);
        inventoryService.SaveProductSafeInventory(list);
    }

    @Test
    public void testgetAllRawMaterialVariety(){
        ArrayList<String> list = inventoryService.getAllRawMaterialVariety("001");
        for (String variety : list){
            System.out.println(variety);
        }
    }

    @Test
    public void testgetAllProductVariety(){
        ArrayList<String> list = inventoryService.getAllProductVariety("001");
        for (String variety : list){
            System.out.println(variety);
        }
    }

    @Test
    public void testgetAllRawMaterialSafeInventory(){
        ArrayList<RawMaterialSafeInventoryPo> list = inventoryService.getAllRawMaterialSafeInventory("001");
        for (RawMaterialSafeInventoryPo po : list){
            print(po);
        }
    }

    @Test
    public void testgetAllProductSafeInventory(){
        ArrayList<ProductSafeInventoryPo> list = inventoryService.getAllProductSafeInventory("001");
        for (ProductSafeInventoryPo po : list){
            print(po);
        }
    }

    @Test
    public void testgetRawInventory(){
        System.out.println(inventoryService.getRawInventory("001","cotton"));
    }

    @Test
    public void testgetProductInventory(){
        System.out.println(inventoryService.getProductInventory("001","pesn"));
    }

    @Test
    public void test(){
        System.out.println(Boolean.valueOf("0"));
    }


    @Test
    public void testgetRawMaterialInventoryItemByMonth(){
        ArrayList<InventoryRawMaterialItemPO> list = inventoryService.getRawMaterialInventoryItemByMonth("001","2017-09");
        for (InventoryRawMaterialItemPO po : list){
            print(po);
        }
    }

    @Test
    public void testgetProductInventoryItemByMonth(){
        ArrayList<InventoryProductItemPO> list = inventoryService.getProductInventoryItemByMonth("001","2017-09");
        for (InventoryProductItemPO po : list){
            print(po);
        }
    }

    private void print(InventoryRawMaterialItemPO po){
        System.out.print(po.getSheetID() + "\t");
        System.out.print(po.getListID() + "\t");
        System.out.print(po.getCompanyID() + "\t");
        System.out.print(po.getVariety() + "\t");
        System.out.print(po.getVoucherID() + "\t");
        System.out.print(po.getDate() + "\t");
        System.out.print(po.isDeliveryOntime() + "\t");
        System.out.print(po.isReturn() + "\t");
        System.out.print(po.getInputNum() + "\t");
        System.out.print(po.getInputUnitPrice() + "\t");
        System.out.print(po.getInputTotalPrice() + "\t");
        System.out.print(po.getOutputNum() + "\t");
        System.out.print(po.getOutputUnitPrice() + "\t");
        System.out.print(po.getOutputTotalPrice() + "\t");
        System.out.println(po.getRemainNum() + "\t");

    }

    private void print(InventoryProductItemPO po){
        System.out.print(po.getSheetID() + "\t");
        System.out.print(po.getListID() + "\t");
        System.out.print(po.getCompanyID() + "\t");
        System.out.print(po.getVariety() + "\t");
        System.out.print(po.getVoucherID() + "\t");
        System.out.print(po.getDate() + "\t");
        System.out.print(po.isDeliveryOntime() + "\t");
        System.out.print(po.isReturn() + "\t");
        System.out.print(po.getInputNum() + "\t");
        System.out.print(po.getInputUnitPrice() + "\t");
        System.out.print(po.getInputTotalPrice() + "\t");
        System.out.print(po.getOutputNum() + "\t");
        System.out.print(po.getOutputUnitPrice() + "\t");
        System.out.print(po.getOutputTotalPrice() + "\t");
        System.out.println(po.getRemainNum() + "\t");

    }

    private void print(RawMaterialSafeInventoryPo po){
        System.out.print(po.getCompany_id() + "\t");
        System.out.print(po.getRaw_material_variety() + "\t");
        System.out.println(po.getSafe_inventory()+ "\t");
    }

    private void print(ProductSafeInventoryPo po){
        System.out.print(po.getCompany_id() + "\t");
        System.out.print(po.getProduct_variety() + "\t");
        System.out.println(po.getSafe_inventory() + "\t");
    }
}
