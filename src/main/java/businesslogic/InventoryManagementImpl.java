package businesslogic;

import businesslogicservice.InventoryManagementService;
import data.InventoryServiceImpl;
import dataservice.InventoryService;
import po.Inventory.InventoryProductItemPO;
import po.Inventory.InventoryRawMaterialItemPO;
import vo.ProductInventoryItemVo;
import vo.ProductInventoryMonitorItemVo;
import vo.RawMaterialInventoryItemVo;
import vo.RawMaterialInventoryMonitorItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/4.
 * 
 */
public class InventoryManagementImpl implements InventoryManagementService {

    /**
     * 根据录入记录得到表格末尾的原材料结存数量
     * @param company_id 公司id
     * @param voucher_id 凭证id
     * @param raw_material_name 原材料种类
     * @param change 发出量/收入量（发出量为负）
     * @return
     */
    public int getRawInventory(String company_id, String voucher_id, String  raw_material_name, int change){
        InventoryService service = new InventoryServiceImpl();
        int result = service.getRawInventoryBySupplier(company_id, voucher_id, raw_material_name)+change;
        service.setSupplierRawInventory(company_id, voucher_id, raw_material_name, result);
        return result;
    }

    /**
     * 公司原材料库存情况信息录入
     * @param company_id 公司id
     * @param list 表格输入数据
     */
    public void SupplierInformationEntry(String company_id, ArrayList<RawMaterialInventoryItemVo> list) {
        InventoryService service = new InventoryServiceImpl();
        int sheet_id = service.getMaxSheetId(company_id)+1;
        ArrayList<InventoryRawMaterialItemPO> result = new ArrayList<>();
        for(int i=1;i<=list.size();i++){
            RawMaterialInventoryItemVo vo = list.get(i);
            result.add(new InventoryRawMaterialItemPO(sheet_id, i, company_id, vo.getRawMaterial_variety(), vo.getVoucher_id(), vo.getDatetime(), vo.isIs_delivery_ontime(),
                    vo.isIs_return(),vo.getInput_num(),vo.getInput_price(), vo.getInput_account(), vo.getOut_num(),vo.getOut_price(),vo.getOut_account(),vo.getBalance_num()));
        }
        service.SaveRawMaterialInventoryItem(result);
    }

    /**
     * 根据录入记录得到表格末尾的产品结存数量
     * @param company_id 公司id
     * @param voucher_id 凭证id
     * @param product_name 产品名称
     * @param change 发出量/收入量（发出量为负）
     * @return
     */
    public int getProductInventory(String company_id, String voucher_id, String product_name, int change){
        InventoryService service = new InventoryServiceImpl();
        int result = service.getProductInventoryByProducer(company_id, voucher_id, product_name)+change;
        service.setProducterProductInventory(company_id, voucher_id, product_name, result);
        return result;
    }

    /**
     * 公司产品库存情况信息录入
     * @param company_id 公司id
     * @param list 表格输入数据
     */
    public void ProducerProductInformationEntry(String company_id, ArrayList<ProductInventoryItemVo> list) {
        InventoryService service = new InventoryServiceImpl();
        int sheet_id = service.getMaxSheetId(company_id)+1;
        ArrayList<InventoryProductItemPO> result = new ArrayList<>();
        for(int i=1;i<=list.size();i++){
            ProductInventoryItemVo vo = list.get(i);
            result.add(new InventoryProductItemPO(sheet_id, i, company_id, vo.getProduct_variety(), vo.getVoucher_id(), vo.getDatetime(), vo.isIs_delivery_ontime(),
                    vo.isIs_return(),vo.getInput_num(),vo.getInput_price(), vo.getInput_account(), vo.getOut_num(),vo.getOut_price(),vo.getOut_account(),vo.getBalance_num()));
        }
        service.SaveProductInventoryItem(result);
    }

    /**
     * 得到公司截至某一时间原材料库存监控信息
     * @param company_id 公司id
     * @param time 最后时间
     * @return
     */
    public ArrayList<RawMaterialInventoryMonitorItemVo> getRawMaterialInventoryMonitorItem(String company_id, String time){
        return null;
    }

    /**
     * 得到公司截至某一时间产品库存监控信息
     * @param company_id 公司id
     * @param time 最后时间
     * @return
     */
    public ArrayList<ProductInventoryMonitorItemVo> getProductInventoryMonitorItem(String company_id, String time){
        return null;
    }
}
