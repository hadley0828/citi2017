package data;

import dataservice.InventoryService;
import po.Inventory.InventoryProductItemPO;
import po.Inventory.InventoryRawMaterialItemPO;
import po.Inventory.ProductSafeInventoryPo;
import po.Inventory.RawMaterialSafeInventoryPo;

import java.util.ArrayList;

/**
 * Created by loohaze on 2017/9/6 上午10:11
 */
public class InventoryServiceImpl implements InventoryService{

    SqlManager sqlManager = SqlManager.getSqlManager();


    @Override
    public int getRawInventoryBySupplier(String company_id, String voucher_id, String raw_material_name) {
        return 0;
    }

    @Override
    public boolean setSupplierRawInventory(String company_id, String voucher_id, String raw_material_name, int inventory) {
        return false;
    }

    @Override
    public int getProductInventoryByProducer(String company_id, String voucher_id, String product_name) {
        return 0;
    }

    @Override
    public boolean setProducterProductInventory(String company_id, String voucher_id, String product_name, int inventory) {
        return false;
    }

    @Override
    public int getMaxSheetId(String company_id) {
        return 0;
    }

    @Override
    public void SaveRawMaterialInventoryItem(ArrayList<InventoryRawMaterialItemPO> list) {

    }

    @Override
    public void SaveProductInventoryItem(ArrayList<InventoryProductItemPO> list) {

    }

    @Override
    public ArrayList<InventoryRawMaterialItemPO> getRawMaterialInventoryItem(String company_id, String time) {
        return null;
    }

    @Override
    public ArrayList<InventoryProductItemPO> getProductInventoryItem(String company_id, String time) {
        return null;
    }

    @Override
    public ArrayList<InventoryRawMaterialItemPO> getRawMaterialInventoryItemByVariety(String company_id, String time, String variety) {
        return null;
    }

    @Override
    public ArrayList<InventoryProductItemPO> getProductInventoryItemByVariety(String company_id, String time, String variety) {
        return null;
    }

    @Override
    public void SaveRawMaterialSafeInventory(ArrayList<RawMaterialSafeInventoryPo> list) {

    }

    @Override
    public void SaveProductSafeInventory(ArrayList<ProductSafeInventoryPo> list) {

    }

    @Override
    public ArrayList<String> getAllRawMaterialVariety(String company_id) {
        return null;
    }

    @Override
    public ArrayList<String> getAllProductVariety(String company_id) {
        return null;
    }

    @Override
    public ArrayList<RawMaterialSafeInventoryPo> getAllRawMaterialSafeInventory(String company_id) {
        return null;
    }

    @Override
    public ArrayList<ProductSafeInventoryPo> getAllProductSafeInventory(String company_id) {
        return null;
    }

    @Override
    public int getRawInventory(String company_id, String raw_material_variety) {
        return 0;
    }

    @Override
    public int getProductInventory(String company_id, String product_variety) {
        return 0;
    }
}
