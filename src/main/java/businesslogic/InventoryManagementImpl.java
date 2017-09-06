package businesslogic;

import businesslogicservice.InventoryManagementService;
import dataservice.InventoryService;
import vo.ProductInventoryItemVo;
import vo.RawMaterialInventoryItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/4.
 */
public class InventoryManagementImpl implements InventoryManagementService {
    /**
     * 供应商库存情况信息录入
     * @param supplier 供应商
     * @param emiter 发出方
     * @param receiver 收入方
     * @param list 表格输入数据
     */
    public void SupplierInformationEntry(String supplier, String emiter, String receiver, ArrayList<RawMaterialInventoryItemVo> list){
//        InventoryService inventoryService = new InventoryServiceImp();
//        String time = list.get(0).getDatetime();
//        int Mit1 = inventoryService.getMit(time, supplier)+inventoryService.getVit(time, supplier)-inventoryService.getVijt(time,supplier);
    }

    /**
     * 生产商原材料库存情况信息录入
     * @param emiter 发出方
     * @param receiver 收入方
     * @param list 表格输入数据
     */
    public void ProducerRawMaterialInformationEntry(String emiter, String receiver, ArrayList<RawMaterialInventoryItemVo> list){

    }

    /**
     * 生产商产品库存情况信息录入
     * @param emiter 发出方
     * @param receiver 收入方
     * @param list 表格输入数据
     */
    public void ProducerProductInformationEntry(String emiter, String receiver, ArrayList<ProductInventoryItemVo> list){

    }

    /**
     * 分销商库存情况信息录入
     * @param emiter 发出方
     * @param receiver 收入方
     * @param list 表格输入数据
     */
    public void DistributorInformationEntry(String emiter, String receiver, ArrayList<ProductInventoryItemVo> list){

    }
}
