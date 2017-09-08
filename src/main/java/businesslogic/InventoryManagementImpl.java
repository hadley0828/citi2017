package businesslogic;

import businesslogicservice.InventoryManagementService;
import dataservice.InventoryService;
import vo.ProductInventoryItemVo;
import vo.RawMaterialInventoryItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/4.
 * 
 */
public class InventoryManagementImpl implements InventoryManagementService {
    /**
     * 根据录入记录得到表格末尾的结存数量
     * @param name 供应商名称
     * @param change 发出量/收入量（发出量为负）
     * @return
     */
    public int getSupplierRawInventory(String name, int change) {
        return 0;
    }

    /**
     * 供应商库存情况信息录入
     * @param supplier 供应商
     * @param list 表格输入数据
     */
    public void SupplierInformationEntry(String supplier, ArrayList<RawMaterialInventoryItemVo> list){

    }

    /**
     * 生产商原材料库存情况信息录入
     * @param producer 生产商
     * @param list 表格输入数据
     */
    public void ProducerRawMaterialInformationEntry(String producer, ArrayList<RawMaterialInventoryItemVo> list){

    }

    /**
     * 生产商产品库存情况信息录入
     * @param producer 生产商
     * @param list 表格输入数据
     */
    public void ProducerProductInformationEntry(String producer, ArrayList<ProductInventoryItemVo> list){

    }

    /**
     * 分销商库存情况信息录入
     * @param distributor 分销商
     * @param list 表格输入数据
     */
    public void DistributorInformationEntry(String distributor, ArrayList<ProductInventoryItemVo> list){

    }
}
