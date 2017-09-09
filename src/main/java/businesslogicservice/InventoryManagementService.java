package businesslogicservice;

import vo.Inventory.*;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/4.
 *
 */
public interface InventoryManagementService {

    /**
     * 保存公司的原材料安全库存量
     * @param company_id 公司id
     * @param list 用户输入数据
     */
    public void SaveRawMaterialSafeInventory(String company_id, ArrayList<RawMaterialSafeInventoryVo> list);

    /**
     * 保存公司的产品安全库存量
     * @param company_id 公司id
     * @param list 用户输入数据
     */
    public void SaveProductSafeInventory(String company_id, ArrayList<ProductSafeInventoryVo> list);

    /**
     * 根据录入记录得到表格末尾的原材料结存数量
     * @param company_id 公司id
     * @param voucher_id 凭证id
     * @param raw_material_name 原材料种类
     * @param change 发出量/收入量（发出量为负）
     * @return
     */
    public int getRawInventory(String company_id, String voucher_id, String  raw_material_name, int change);

    /**
     * 公司原材料库存情况信息录入
     * @param company_id 公司id
     * @param list 表格输入数据
     */
    public void SupplierInformationEntry(String company_id, ArrayList<RawMaterialInventoryItemVo> list);

    /**
     * 根据录入记录得到表格末尾的产品结存数量
     * @param company_id 公司id
     * @param voucher_id 凭证id
     * @param product_name 产品名称
     * @param change 发出量/收入量（发出量为负）
     * @return
     */
    public int getProductInventory(String company_id, String voucher_id, String product_name, int change);

    /**
     * 公司产品库存情况信息录入
     * @param company_id 公司id
     * @param list 表格输入数据
     */
    public void ProducerProductInformationEntry(String company_id, ArrayList<ProductInventoryItemVo> list);

    /**
     * 得到公司截至某一时间原材料库存监控信息
     * @param company_id 公司id
     * @param time 最后时间
     * @return
     */
    public ArrayList<RawMaterialInventoryMonitorItemVo> getRawMaterialInventoryMonitorItem(String company_id, String time);

    /**
     * 得到公司截至某一时间产品库存监控信息
     * @param company_id 公司id
     * @param time 最后时间
     * @return
     */
    public ArrayList<ProductInventoryMonitorItemVo> getProductInventoryMonitorItem(String company_id, String time);

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
     * 原材料库存量与时间的关系
     * @param company_id 公司id
     * @param raw_material_variety 原材料种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<InventoryChangeVo> getRawInventoryChange(String company_id, String raw_material_variety, String time);

    /**
     * 产品库存量与时间的关系
     * @param company_id 公司id
     * @param product_variety 产品种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<InventoryChangeVo> getProductInventoryChange(String company_id, String product_variety, String time);

    /**
     * 原材料准时交货率与时间的关系
     * @param company_id 公司id
     * @param raw_material_variety 原材料种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<PunctualDeliveryRateChangeVo> getRawPunctualDeliveryRateChange(String company_id, String raw_material_variety, String time);

    /**
     * 产品准时交货率与时间的关系
     * @param company_id 公司id
     * @param product_variety 产品种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<PunctualDeliveryRateChangeVo> getProductPunctualDeliveryRateChange(String company_id, String product_variety, String time);

    /**
     * 原材料退货率与时间的关系
     * @param company_id 公司id
     * @param raw_material_variety 原材料种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<RefundRateChangeVo> getRawRefundRateChange(String company_id, String raw_material_variety, String time);

    /**
     * 产品退货率与时间的关系
     * @param company_id 公司id
     * @param product_variety 产品种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<RefundRateChangeVo> getProductRefundRateChange(String company_id, String product_variety, String time);

    /**
     * 原材料库存量与安全库存量的关系
     * @param company_id 公司id
     * @return
     */
    public ArrayList<RawSafeInventoryRateVo> getRawSafeInventoryRate(String company_id);

    /**
     * 产品库存量与安全库存量的关系
     * @param company_id 公司id
     * @return
     */
    public ArrayList<ProductSafeInventoryRateVo> getProductInventoryRate(String company_id);

}
