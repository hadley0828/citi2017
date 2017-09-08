package dataservice;

import vo.RawMaterialInventoryItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/5.
 */
public interface InventoryService {
    /**
     * 获取当前时间某供应商的原材料的库存
     * @param name 供应商名称
     * @return
     */
    public int getRawInventoryBySupplier(String name);

    /**
     * 更新供应商的原材料库存
     * @param name 供应商名称
     * @param inventory 库存量
     * @return
     */
    public boolean setSupplierRawInventory(String name, int inventory);

    /**
     * 获取当前时间某生产商的原材料库存
     * @param name 生产商名称
     * @return
     */
    public int getRawInventoryByProducer(String name);

    /**
     * 更新生产商的原材料库存
     * @param name 生产商名称
     * @param inventory 库存量
     * @return
     */
    public boolean setProducerRawInventory(String name, int inventory);

    /**
     * 获取当前时间某生产商的产品库存
     * @param name 生产商名称
     * @return
     */
    public int getProductInventoryByProducer(String name);

    /**
     * 更新生产商的产品库存
     * @param name 生产商名称
     * @param inventory 库存量
     * @return
     */
    public boolean setProducterProductInventory(String name, int inventory);

    /**
     * 获取当前时间某分销商的产品库存
     * @param name 分销商名称
     * @return
     */
    public int getProductInventoryByDistributor(String name);

    /**
     * 更新分销商的产品库存
     * @param name 分销商名称
     * @param inventory 库存量
     * @return
     */
    public boolean setDistributorProductInventory(String name, int inventory);

}
