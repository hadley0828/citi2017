package businesslogicservice;

import vo.ProductInventoryItemVo;
import vo.RawMaterialInventoryItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/4.
 */
public interface InventoryManagementService {
    /**
     * 供应商库存情况信息录入
     * @param supplier 供应商
     * @param emitter 发出方
     * @param receiver 收入方
     * @param list 表格输入数据
     */
    public void SupplierInformationEntry(String supplier, String emitter, String receiver, ArrayList<RawMaterialInventoryItemVo> list);

    /**
     * 生产商原材料库存情况信息录入
     * @param emitter 发出方
     * @param receiver 收入方
     * @param list 表格输入数据
     */
    public void ProducerRawMaterialInformationEntry(String emitter, String receiver, ArrayList<RawMaterialInventoryItemVo> list);

    /**
     * 生产商产品库存情况信息录入
     * @param id 表格id
     * @param emitter 发出方
     * @param receiver 收入方
     * @param list 表格输入数据
     */
    public void ProducerProductInformationEntry(String emitter, String receiver, ArrayList<ProductInventoryItemVo> list);

    /**
     * 分销商库存情况信息录入
     * @param emitter 发出方
     * @param receiver 收入方
     * @param list 表格输入数据
     */
    public void DistributorInformationEntry(String emitter, String receiver, ArrayList<ProductInventoryItemVo> list);
}
