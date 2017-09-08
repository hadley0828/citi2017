package dataservice;


/**
 * Created by 费慧通 on 2017/9/5.
 *
 */
public interface InventoryService {
    /**
     * 获取当前时间某供应商的原材料的库存
     * @param voucher_id 凭证编号
     * @param raw_material_name 原材料种类
     * @return
     */
    public int getRawInventoryBySupplier(String voucher_id,String raw_material_name);

    /**
     * 更新供应商的原材料库存
     * @param voucher_id 凭证编号
     * @param raw_material_name 原材料种类
     * @param inventory 库存量
     * @return
     */
    public boolean setSupplierRawInventory(String voucher_id,String raw_material_name, int inventory);

    /**
     * 获取当前时间某生产商的原材料库存
     * @param voucher_id 凭证编号
     * @param raw_material_name 原材料种类
     * @return
     */
    public int getRawInventoryByProducer(String voucher_id,String raw_material_name);

    /**
     * 更新生产商的原材料库存
     * @param voucher_id 凭证编号
     * @param raw_material_name 原材料种类
     * @param inventory 库存量
     * @return
     */
    public boolean setProducerRawInventory(String voucher_id,String raw_material_name, int inventory);

    /**
     * 获取当前时间某生产商的产品库存
     * @param voucher_id 凭证编号
     * @param product_name 原材料种类
     * @return
     */
    public int getProductInventoryByProducer(String voucher_id,String product_name);

    /**
     * 更新生产商的产品库存
     * @param voucher_id 凭证编号
     * @param product_name 原材料种类
     * @param inventory 库存量
     * @return
     */
    public boolean setProducterProductInventory(String voucher_id,String product_name, int inventory);

    /**
     * 获取当前时间某分销商的产品库存
     * @param voucher_id 凭证编号
     * @param product_name 原材料种类
     * @return
     */
    public int getProductInventoryByDistributor(String voucher_id,String product_name);

    /**
     * 更新分销商的产品库存
     * @param voucher_id 凭证编号
     * @param product_name 原材料种类
     * @param inventory 库存量
     * @return
     */
    public boolean setDistributorProductInventory(String voucher_id,String product_name, int inventory);

}
