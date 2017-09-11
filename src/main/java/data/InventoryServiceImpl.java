package data;

import dataservice.InventoryService;
import po.Inventory.InventoryProductItemPO;
import po.Inventory.InventoryRawMaterialItemPO;
import po.Inventory.ProductSafeInventoryPo;
import po.Inventory.RawMaterialSafeInventoryPo;

import java.sql.Timestamp;
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

        ArrayList<String> varietyList = getRawMaterialVarietyList(company_id);

        for (String variety : varietyList){
            String sql = "select * from inventory_material where datetime<=? and company_id =? and material_variety =? order by datetime";
            ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{time,company_id,variety});

            for (Map<String,Object> map : maps){
                list.add(getInventoryRawMaterialItemPOByMap(map));
            }
        }

        sqlManager.releaseAll();
        return list;
    }

    @Override
    public ArrayList<InventoryProductItemPO> getProductInventoryItem(String company_id, String time) {
        sqlManager.getConnection();

        ArrayList<InventoryProductItemPO> list = new ArrayList<>();

        ArrayList<String> varietyList = getProductVarietyList(company_id);

        for (String variety : varietyList){
            String sql = "select * from inventory_product where datetime<=? and company_id =? and product_variety=? order by datetime";
            ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{time,company_id,variety});

            for (Map<String,Object> map : maps){
                list.add(getInventoryProductItemPOByMap(map));
            }
        }

        sqlManager.releaseAll();
        return list;
    }

    @Override
    public ArrayList<InventoryRawMaterialItemPO> getRawMaterialInventoryItemByVariety(String company_id, String time, String variety) {
        sqlManager.getConnection();

        ArrayList<InventoryRawMaterialItemPO> list = new ArrayList<>();

        String sql = "select * from inventory_material where datetime<=? and company_id=? and material_variety=? order by datetime";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{time,company_id,variety});

        for (Map<String,Object> map : maps){
            list.add(getInventoryRawMaterialItemPOByMap(map));
        }

        sqlManager.releaseAll();
        return list;
    }

    @Override
    public ArrayList<InventoryProductItemPO> getProductInventoryItemByVariety(String company_id, String time, String variety) {
        sqlManager.getConnection();

        ArrayList<InventoryProductItemPO> list = new ArrayList<>();

        String sql = "select * from inventory_product where datetime<=? and company_id=? and product_variety=? order by datetime";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{time,company_id,variety});

        for (Map<String,Object> map : maps){
            list.add(getInventoryProductItemPOByMap(map));
        }

        sqlManager.releaseAll();
        return list;
    }

    @Override
    public void SaveRawMaterialSafeInventory(ArrayList<RawMaterialSafeInventoryPo> list) {
        sqlManager.getConnection();
        for (RawMaterialSafeInventoryPo po : list){
            insertOneRawMaterialSafeInventoryPo(po);
        }
        sqlManager.releaseAll();
    }

    @Override
    public void SaveProductSafeInventory(ArrayList<ProductSafeInventoryPo> list) {
        sqlManager.getConnection();
        for (ProductSafeInventoryPo po : list){
            insertOneProductSafeInventoryPo(po);
        }
        sqlManager.releaseAll();
    }

    @Override
    public ArrayList<String> getAllRawMaterialVariety(String company_id) {
        sqlManager.getConnection();
        ArrayList<String> varietyList = getRawMaterialVarietyList(company_id);
        sqlManager.releaseAll();
        return varietyList;
    }

    @Override
    public ArrayList<String> getAllProductVariety(String company_id) {
        sqlManager.getConnection();
        ArrayList<String> varietyList = getProductVarietyList(company_id);
        sqlManager.releaseAll();
        return varietyList;
    }

    @Override
    public ArrayList<RawMaterialSafeInventoryPo> getAllRawMaterialSafeInventory(String company_id) {
        sqlManager.getConnection();

        ArrayList<RawMaterialSafeInventoryPo> list = new ArrayList<>();

        String sql = "select * from safe_inventory_material where company_id=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id});

        for (Map<String,Object> map : maps){
            list.add(getRawMaterialSafeInventoryPoByMap(map));
        }
        sqlManager.releaseAll();
        return list;
    }

    @Override
    public ArrayList<ProductSafeInventoryPo> getAllProductSafeInventory(String company_id) {
        sqlManager.getConnection();

        ArrayList<ProductSafeInventoryPo> list = new ArrayList<>();

        String sql = "select * from safe_inventory_product where company_id=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id});

        for (Map<String,Object> map : maps){
            list.add(getProductSafeInventoryPo(map));
        }
        sqlManager.releaseAll();
        return list;
    }

    @Override
    public int getRawInventory(String company_id, String raw_material_variety) {
        sqlManager.getConnection();

        String sql = "select safety_stock from safe_inventory_material where company_id=? and variety=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{company_id,raw_material_variety});

        try{
            int inventory = Integer.parseInt(map.get("safety_stock").toString());
            sqlManager.releaseAll();
            return inventory;
        }catch (Exception e){
            sqlManager.releaseAll();
            return 0;
        }
    }

    @Override
    public int getProductInventory(String company_id, String product_variety) {
        sqlManager.getConnection();

        String sql = "select safety_stock from safe_inventory_product where company_id=? and variety=?";
        Map<String,Object> map = sqlManager.querySimple(sql,new Object[]{company_id,product_variety});

        try {
            int inventory = Integer.parseInt(map.get("safety_stock").toString());
            sqlManager.releaseAll();
            return inventory;
        }catch (Exception e){
            sqlManager.releaseAll();
            return 0;
        }
    }


    /**
     * 数据库单条InventoryRawMaterialItemPO插入
     * @param po
     */
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

    /**
     * 数据库单条InventoryProductItemPO插入
     * @param po
     */
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

    /**
     * 数据库单条RawMaterialSafeInventoryPo插入
     * @param po
     */
    private void insertOneRawMaterialSafeInventoryPo(RawMaterialSafeInventoryPo po){
        List<Object> params = new ArrayList<>();

        params.add(po.getCompany_id());
        params.add(po.getRaw_material_variety());
        params.add(po.getSafe_inventory());

        String sql = sqlManager.appendSQL("insert into safe_inventory_material values",params.size());
        sqlManager.executeUpdateByList(sql,params);
    }

    /**
     * 数据库单条ProductSafeInventoryPo插入
     * @param po
     */
    private void insertOneProductSafeInventoryPo(ProductSafeInventoryPo po){
        List<Object> params = new ArrayList<>();

        params.add(po.getCompany_id());
        params.add(po.getProduct_variety());
        params.add(po.getSafe_inventory());

        String sql = sqlManager.appendSQL("insert into safe_inventory_product values",params.size());
        sqlManager.executeUpdateByList(sql,params);
    }

    /**
     * 通过数据库获得map转为InventoryRawMaterialItemPO
     * @param map
     * @return
     */
    private InventoryRawMaterialItemPO getInventoryRawMaterialItemPOByMap(Map<String,Object> map){
        InventoryRawMaterialItemPO po = new InventoryRawMaterialItemPO();

        po.setSheetID(Integer.parseInt(map.get("sheet_id").toString()));
        po.setListID(Integer.parseInt(map.get("list_id").toString()));
        po.setCompanyID(map.get("company_id").toString());
        po.setVariety(map.get("material_variety").toString());
        po.setDate(Timestamp.valueOf(map.get("datetime").toString()));
        po.setDeliveryOntime(Boolean.valueOf(map.get("is_delivery_ontime").toString()));
        po.setReturn(Boolean.valueOf(map.get("is_return").toString()));
        po.setInputNum(Integer.parseInt(map.get("input_num").toString()));
        po.setInputUnitPrice(Double.parseDouble(map.get("input_unit_price").toString()));
        po.setInputTotalPrice(Double.parseDouble(map.get("input_account").toString()));
        po.setOutputNum(Integer.parseInt(map.get("output_num").toString()));
        po.setOutputUnitPrice(Double.parseDouble(map.get("output_unit_price").toString()));
        po.setOutputTotalPrice(Double.parseDouble(map.get("output_account").toString()));
        po.setRemainNum(Integer.parseInt(map.get("balance_num").toString()));

        return po;
    }

    /**
     * 通过数据库获得map转为InventoryProductItemPO
     * @param map
     * @return
     */
    private InventoryProductItemPO getInventoryProductItemPOByMap(Map<String,Object> map){
        InventoryProductItemPO po = new InventoryProductItemPO();

        po.setSheetID(Integer.parseInt(map.get("sheet_id").toString()));
        po.setListID(Integer.parseInt(map.get("list_id").toString()));
        po.setCompanyID(map.get("company_id").toString());
        po.setVariety(map.get("product_variety").toString());
        po.setDate(Timestamp.valueOf(map.get("datetime").toString()));
        po.setDeliveryOntime(Boolean.valueOf(map.get("is_delivery_ontime").toString()));
        po.setReturn(Boolean.valueOf(map.get("is_return").toString()));
        po.setInputNum(Integer.parseInt(map.get("input_num").toString()));
        po.setInputUnitPrice(Double.parseDouble(map.get("input_unit_price").toString()));
        po.setInputTotalPrice(Double.parseDouble(map.get("input_account").toString()));
        po.setOutputNum(Integer.parseInt(map.get("output_num").toString()));
        po.setOutputUnitPrice(Double.parseDouble(map.get("output_unit_price").toString()));
        po.setOutputTotalPrice(Double.parseDouble(map.get("output_account").toString()));
        po.setRemainNum(Integer.parseInt(map.get("balance_num").toString()));

        return po;
    }

    /**
     * 通过数据库获得map转为RawMaterialSafeInventoryPo
     * @param map
     * @return
     */
    private RawMaterialSafeInventoryPo getRawMaterialSafeInventoryPoByMap(Map<String,Object> map){
        RawMaterialSafeInventoryPo po = new RawMaterialSafeInventoryPo();

        po.setCompany_id(map.get("company_id").toString());
        po.setRaw_material_variety(map.get("variety").toString());
        po.setSafe_inventory(Integer.parseInt(map.get("safety_stock").toString()));

        return po;
    }

    /**
     * 通过数据库获得map转为ProductSafeInventoryPo
     * @return
     */
    private ProductSafeInventoryPo getProductSafeInventoryPo(Map<String,Object> map){
        ProductSafeInventoryPo po = new ProductSafeInventoryPo();

        po.setCompany_id(map.get("company_id").toString());
        po.setProduct_variety(map.get("variety").toString());
        po.setSafe_inventory(Integer.parseInt(map.get("safety_stock").toString()));

        return po;
    }
    /**
     * 获得公司所有原材料种类
     * @param company_id
     * @return
     */
    private ArrayList<String> getRawMaterialVarietyList(String company_id){
        ArrayList<String> list = new ArrayList<>();

        String sql = "select distinct material_variety from inventory_material where company_id=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id});

        for (Map<String,Object> map : maps){
            list.add(map.get("material_variety").toString());
        }

        return list;
    }

    /**
     * 获得公司所有产品种类
     * @param company_id
     * @return
     */
    private ArrayList<String> getProductVarietyList(String company_id){
        ArrayList<String> list = new ArrayList<>();

        String sql = "select distinct product_variety from inventory_product where company_id=?";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{company_id});

        for (Map<String,Object> map : maps){
            list.add(map.get("product_variety").toString());
        }

        return list;
    }
}
