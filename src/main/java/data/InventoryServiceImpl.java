package data;

import dataservice.InventoryService;
import po.Inventory.InventoryProductItemPO;
import po.Inventory.InventoryRawMaterialItemPO;
import po.Inventory.ProductSafeInventoryPo;
import po.Inventory.RawMaterialSafeInventoryPo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/6 上午10:11
 */
public class InventoryServiceImpl implements InventoryService{

    SqlManager sqlManager = SqlManager.getSqlManager();


    @Override
    public int getRawInventoryBySupplier(String company_id, String voucher_id, String raw_material_name) {
        sqlManager.getConnection();

        String sql = "select * from inventory_material as t where t.datetime = (select max(datetime) from inventory_material where company_id=? and voucher_id=? and material_variety=?) and company_id=? and voucher_id=? and material_variety=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{company_id,voucher_id,raw_material_name,company_id,voucher_id,raw_material_name});

        int remain = Integer.parseInt(map.get("balance_num").toString());

        sqlManager.releaseAll();
        return remain;
    }


    @Override
    public int getProductInventoryByProducer(String company_id, String voucher_id, String product_name) {
        sqlManager.getConnection();

        String sql = "select * from inventory_product as t where t.datetime = (select max(datetime) from inventory_product where company_id=? and voucher_id=? and product_variety=?) and company_id=? and voucher_id=? and product_variety=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{company_id,voucher_id,product_name,company_id,voucher_id,product_name});

        int remain = Integer.parseInt(map.get("balance_num").toString());

        sqlManager.releaseAll();
        return remain;
    }


    @Override
    public int getMaxSheetId(String company_id) {
        sqlManager.getConnection();

        String sql = "select sheet_id from(select sheet_id from inventory_material where company_id = ? union select sheet_id from inventory_product where company_id=?) alias group by sheet_id order by sheet_id desc limit 1";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{company_id,company_id});
        int maxSheetID = Integer.parseInt(map.get("sheet_id").toString());

        sqlManager.releaseAll();
        return maxSheetID;
    }

    @Override
    public void SaveRawMaterialInventoryItem(ArrayList<InventoryRawMaterialItemPO> list) {
        sqlManager.getConnection();
        for (InventoryRawMaterialItemPO po : list){
            insertOneInventoryRawMaterialItemPO(po);
        }
        sqlManager.releaseAll();
    }

    @Override
    public void SaveProductInventoryItem(ArrayList<InventoryProductItemPO> list) {
        sqlManager.getConnection();
        for (InventoryProductItemPO po : list){
            insertOneInventoryProductItemPO(po);
        }
        sqlManager.releaseAll();
    }

    @Override
    public ArrayList<InventoryRawMaterialItemPO> getRawMaterialInventoryItem(String company_id, String time) {
        sqlManager.getConnection();

        ArrayList<InventoryRawMaterialItemPO> list = new ArrayList<>();

        String sql = "select * from inventory_material where datetime<=? and company_id =? group by material_variety order by datetime";
        sqlManager.releaseAll();
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


    private void insertOneInventoryRawMaterialItemPO(InventoryRawMaterialItemPO po){
        List<Object> params = new ArrayList<>();

        params.add(po.getSheetID());
        params.add(po.getListID());
        params.add(po.getCompanyID());
        params.add(po.getVariety());
        params.add(po.getVoucherID());
        params.add(po.getDate());
        params.add(po.isDeliveryOntime());
        params.add(po.isReturn());
        params.add(po.getInputNum());
        params.add(po.getInputUnitPrice());
        params.add(po.getInputTotalPrice());
        params.add(po.getOutputNum());
        params.add(po.getOutputUnitPrice());
        params.add(po.getOutputTotalPrice());
        params.add(po.getRemainNum());

        String sql = sqlManager.appendSQL("insert into inventory_material values",params.size());
        sqlManager.executeUpdateByList(sql,params);
    }

    private void insertOneInventoryProductItemPO(InventoryProductItemPO po){
        List<Object> params = new ArrayList<>();

        params.add(po.getSheetID());
        params.add(po.getListID());
        params.add(po.getCompanyID());
        params.add(po.getVariety());
        params.add(po.getVoucherID());params.add(po.getDate());
        params.add(po.isDeliveryOntime());
        params.add(po.isReturn());
        params.add(po.getInputNum());
        params.add(po.getInputUnitPrice());
        params.add(po.getInputTotalPrice());
        params.add(po.getOutputNum());
        params.add(po.getOutputUnitPrice());
        params.add(po.getOutputTotalPrice());
        params.add(po.getRemainNum());

        String sql = sqlManager.appendSQL("insert into inventory_product values",params.size());
        sqlManager.executeUpdateByList(sql,params);
    }
}
