package data;

import dataservice.InventoryService;

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
}
