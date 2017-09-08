package data;

import dataservice.InventoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/6 上午10:11
 */
public class InventoryServiceImpl implements InventoryService{

    SqlManager sqlManager = SqlManager.getSqlManager();


    private void insertInventoryIdList(String variety,String id){
        sqlManager.getConnection();
        List<Object> params = new ArrayList<>();
        params.add(variety);
        params.add(id);
        String sql = sqlManager.appendSQL("INSERT INTO inventory_id_list VALUES",params.size());
        sqlManager.executeUpdateByList(sql,params);
        sqlManager.commit();
    }

    private ArrayList<String> getAllListId(){
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT id FROM inventory_id_list";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{});
        for (Map<String,Object> map : maps){
            list.add(map.get("id").toString());
        }
        return list;
    }


    @Override
    public int getRawInventoryBySupplier(String voucher_id, String raw_material_name) {
        return 0;
    }

    @Override
    public boolean setSupplierRawInventory(String voucher_id, String raw_material_name, int inventory) {
        return false;
    }

    @Override
    public int getRawInventoryByProducer(String voucher_id, String raw_material_name) {
        return 0;
    }

    @Override
    public boolean setProducerRawInventory(String voucher_id, String raw_material_name, int inventory) {
        return false;
    }

    @Override
    public int getProductInventoryByProducer(String voucher_id, String product_name) {
        return 0;
    }

    @Override
    public boolean setProducterProductInventory(String voucher_id, String product_name, int inventory) {
        return false;
    }

    @Override
    public int getProductInventoryByDistributor(String voucher_id, String product_name) {
        return 0;
    }

    @Override
    public boolean setDistributorProductInventory(String voucher_id, String product_name, int inventory) {
        return false;
    }
}
