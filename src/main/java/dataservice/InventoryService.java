package dataservice;


import po.Inventory.InventoryProductItemPO;
import po.Inventory.InventoryRawMaterialItemPO;
import po.Inventory.ProductSafeInventoryPo;
import po.Inventory.RawMaterialSafeInventoryPo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/5.
 *
 */
public interface InventoryService {
    /**
     * 获取当前时间公司的原材料的库存
     * @param company_id 公司id
     * @param voucher_id 凭证编号
     * @param raw_material_name 原材料种类
     * @return
     */
    public int getRawInventoryBySupplier(String company_id, String voucher_id,String raw_material_name);

    /**
     * 更新公司的原材料库存
     * @param company_id 公司id
     * @param voucher_id 凭证编号
     * @param raw_material_name 原材料种类
     * @param inventory 库存量
     * @return
     */
    public boolean setSupplierRawInventory(String company_id, String voucher_id,String raw_material_name, int inventory);

    /**
     * 获取当前时间公司的产品库存
     * @param company_id 公司id
     * @param voucher_id 凭证编号
     * @param product_name 原材料种类
     * @return
     */
    public int getProductInventoryByProducer(String company_id, String voucher_id,String product_name);

    /**
     * 更新公司的产品库存
     * @param company_id 公司id
     * @param voucher_id 凭证编号
     * @param product_name 原材料种类
     * @param inventory 库存量
     * @return
     */
    public boolean setProducterProductInventory(String company_id, String voucher_id,String product_name, int inventory);

    /**
     * 得到公司最大的表id
     * @param company_id 公司id
     * @return
     */
    public int getMaxSheetId(String company_id);

    /**
     * 保存原材料库存录入信息
     * @param list
     */
    public void SaveRawMaterialInventoryItem(ArrayList<InventoryRawMaterialItemPO> list);

    /**
     * 保存产品库存录入信息
     * @param list
     */
    public void SaveProductInventoryItem(ArrayList<InventoryProductItemPO> list);

    /**
     * 得到公司截至某一天所有的原材料库存录入信息(按原材料种类分组、时间从早到晚排序)
     * @param company_id 公司
     * @param time 最后时间
     * @return
     */
    public ArrayList<InventoryRawMaterialItemPO> getRawMaterialInventoryItem(String company_id, String time);

    /**
     * 得到公司截至某一天所有的产品库存录入信息(按产品种类分组、时间从早到晚排序)
     * @param company_id 公司id
     * @param time 最后时间
     * @return
     */
    public ArrayList<InventoryProductItemPO> getProductInventoryItem(String company_id, String time);

    /**
     * 得到公司截至某一天某种原材料库存录入信息(按时间从早到晚排序)
     * @param company_id 公司
     * @param time 最后时间
     * @param variety 原材料种类
     * @return
     */
    public ArrayList<InventoryRawMaterialItemPO> getRawMaterialInventoryItemByVariety(String company_id, String time, String variety);

    /**
     * 得到公司截至某一天某种产品库存录入信息(按时间从早到晚排序)
     * @param company_id 公司id
     * @param time 最后时间
     * @param variety 产品种类
     * @return
     */
    public ArrayList<InventoryProductItemPO> getProductInventoryItemByVariety(String company_id, String time, String variety);

    /**
     * 保存公司所有原材料的安全库存量
     * @param list
     */
    public void SaveRawMaterialSafeInventory(ArrayList<RawMaterialSafeInventoryPo> list);

    /**
     * 保存公司所有产品的安全库存量
     * @param list
     */
    public void SaveProductSafeInventory(ArrayList<ProductSafeInventoryPo> list);

    /**
     * 得到公司的所有原材料种类
     * @param company_id 公司id
     * @return
     */
    public ArrayList<String> getAllRawMaterialVariety(String company_id);

    /**
     * 得到公司的所有产品种类
     * @param company_id 公司id
     * @return
     */
    public ArrayList<String> getAllProductVariety(String company_id);

    /**
     * 得到公司的所有原材料的安全库存量
     * @param company_id 公司id
     * @return
     */
    public ArrayList<RawMaterialSafeInventoryPo> getAllRawMaterialSafeInventory(String company_id);

    /**
     * 得到公司的所有产品的安全库存量
     * @param company_id 公司id
     * @return
     */
    public ArrayList<ProductSafeInventoryPo> getAllProductSafeInventory(String company_id);

}
