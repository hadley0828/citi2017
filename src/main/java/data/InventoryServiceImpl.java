package data;

import dataservice.InventoryService;
import po.Inventory.InventoryProductItemPO;
import po.Inventory.InventoryRawMaterialItemPO;

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
}
