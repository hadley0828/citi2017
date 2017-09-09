package businesslogic;

import businesslogicservice.InventoryManagementService;
import data.InventoryServiceImpl;
import dataservice.InventoryService;
import po.Inventory.InventoryProductItemPO;
import po.Inventory.InventoryRawMaterialItemPO;
import po.Inventory.ProductSafeInventoryPo;
import po.Inventory.RawMaterialSafeInventoryPo;
import vo.Inventory.*;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by 费慧通 on 2017/9/4.
 * 
 */
public class InventoryManagementImpl implements InventoryManagementService {

    /**
     * 保存公司的原材料安全库存量
     * @param company_id 公司id
     * @param list 用户输入数据
     */
    public void SaveRawMaterialSafeInventory(String company_id, ArrayList<RawMaterialSafeInventoryVo> list){
        InventoryService service = new InventoryServiceImpl();
        ArrayList<RawMaterialSafeInventoryPo> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            RawMaterialSafeInventoryVo vo = list.get(i);
            result.add(new RawMaterialSafeInventoryPo(company_id, vo.getRaw_material_variety(), vo.getSafe_inventory()));
        }
        service.SaveRawMaterialSafeInventory(result);
    }

    /**
     * 保存公司的产品安全库存量
     * @param company_id 公司id
     * @param list 用户输入数据
     */
    public void SaveProductSafeInventory(String company_id, ArrayList<ProductSafeInventoryVo> list){
        InventoryService service = new InventoryServiceImpl();
        ArrayList<ProductSafeInventoryPo> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            ProductSafeInventoryVo vo = list.get(i);
            result.add(new ProductSafeInventoryPo(company_id, vo.getProduct_variety(), vo.getSafe_inventory()));
        }
        service.SaveProductSafeInventory(result);
    }

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
        InventoryService service = new InventoryServiceImpl();
        ArrayList<InventoryRawMaterialItemPO> list = service.getRawMaterialInventoryItem(company_id, time);
        ArrayList<RawMaterialSafeInventoryPo> list1 = service.getAllRawMaterialSafeInventory(company_id);

        ArrayList<RawMaterialInventoryMonitorItemVo> result = new ArrayList<>();

        String first_raw_material = list.get(0).getVariety();
        int trade = 0;  //交易次数
        int delivery_on_time = 0;  //准时交货次数
        int back = 0;   //退货次数
        int inventory = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i=0;i<list.size();i++){
            InventoryRawMaterialItemPO po = list.get(i);
            String variety = po.getVariety();
            if(first_raw_material.equals(variety)){
                trade++;
                if(po.isDeliveryOntime()){
                    delivery_on_time++;
                }
                if(po.isReturn()){
                    back++;
                }
                inventory = po.getRemainNum();
            }else{
                //准时交货率
                String punctual_delivery_rate = df.format(delivery_on_time/trade*100)+"%";
                //退货率
                String refund_rate = df.format(back/trade*100)+"%";
                result.add(new RawMaterialInventoryMonitorItemVo(first_raw_material,inventory,getSafeInventory(list1,first_raw_material),punctual_delivery_rate,refund_rate));
                first_raw_material = variety;

                trade = 1;
                delivery_on_time = 0;
                back = 0;
                if(po.isDeliveryOntime()){
                    delivery_on_time++;
                }
                if(po.isReturn()){
                    back++;
                }
                inventory = po.getRemainNum();
            }
        }
        String punctual_delivery_rate = df.format(delivery_on_time/trade*100)+"%";
        String refund_rate = df.format(back/trade*100)+"%";
        result.add(new RawMaterialInventoryMonitorItemVo(first_raw_material,inventory,getSafeInventory(list1,first_raw_material),punctual_delivery_rate,refund_rate));
        return result;
    }

    /**
     * 得到公司截至某一时间产品库存监控信息
     * @param company_id 公司id
     * @param time 最后时间
     * @return
     */
    public ArrayList<ProductInventoryMonitorItemVo> getProductInventoryMonitorItem(String company_id, String time){
        InventoryService service = new InventoryServiceImpl();
        ArrayList<InventoryProductItemPO> list = service.getProductInventoryItem(company_id, time);
        ArrayList<ProductSafeInventoryPo> list1 = service.getAllProductSafeInventory(company_id);

        ArrayList<ProductInventoryMonitorItemVo> result = new ArrayList<>();

        String first_product = list.get(0).getVariety();
        int trade = 0;  //交易次数
        int delivery_on_time = 0;  //准时交货次数
        int back = 0;   //退货次数
        int inventory = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i=0;i<list.size();i++){
            InventoryProductItemPO po = list.get(i);
            String variety = po.getVariety();
            if(first_product.equals(variety)){
                trade++;
                if(po.isDeliveryOntime()){
                    delivery_on_time++;
                }
                if(po.isReturn()){
                    back++;
                }
                inventory = po.getRemainNum();
            }else{
                //准时交货率
                String punctual_delivery_rate = df.format(delivery_on_time/trade*100)+"%";
                //退货率
                String refund_rate = df.format(back/trade*100)+"%";
                result.add(new ProductInventoryMonitorItemVo(first_product,inventory,getProductSafeInventory(list1,first_product),punctual_delivery_rate,refund_rate));
                first_product = variety;

                trade = 1;
                delivery_on_time = 0;
                back = 0;
                if(po.isDeliveryOntime()){
                    delivery_on_time++;
                }
                if(po.isReturn()){
                    back++;
                }
                inventory = po.getRemainNum();
            }
        }
        //准时交货率
        String punctual_delivery_rate = df.format(delivery_on_time/trade*100)+"%";
        //退货率
        String refund_rate = df.format(back/trade*100)+"%";
        result.add(new ProductInventoryMonitorItemVo(first_product,inventory,getProductSafeInventory(list1,first_product),punctual_delivery_rate,refund_rate));
        return result;
    }

    /**
     * 得到公司的所有原材料种类
     * @param company_id 公司id
     * @return
     */
    public ArrayList<String> getAllRawMaterialVariety(String company_id){
        InventoryService service = new InventoryServiceImpl();
        return service.getAllRawMaterialVariety(company_id);
    }

    /**
     * 得到公司的所有产品种类
     * @param company_id 公司id
     * @return
     */
    public ArrayList<String> getAllProductVariety(String company_id){
        InventoryService service = new InventoryServiceImpl();
        return service.getAllProductVariety(company_id);
    }

    /**
     * 原材料库存量与时间的关系
     * @param company_id 公司id
     * @param raw_material_variety 原材料种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<InventoryChangeVo> getRawInventoryChange(String company_id, String raw_material_variety, String time){
        InventoryService service = new InventoryServiceImpl();
        ArrayList<InventoryRawMaterialItemPO> list = service.getRawMaterialInventoryItemByVariety(company_id, time, raw_material_variety);

        ArrayList<InventoryChangeVo> result = new ArrayList<>();

        String first = getYearAndMonth(list.get(0).getDate());
        int inventory = 0;
        for(int i=0;i<list.size();i++){
            InventoryRawMaterialItemPO po = list.get(i);
            String now = getYearAndMonth(po.getDate());
            if(now.equals(first)){
                inventory = po.getRemainNum();
            }else{
                result.add(new InventoryChangeVo(first, inventory));
                first = now;
                inventory = po.getRemainNum();
            }
        }
        result.add(new InventoryChangeVo(first, inventory));
        return result;
    }

    /**
     * 产品库存量与时间的关系
     * @param company_id 公司id
     * @param product_variety 产品种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<InventoryChangeVo> getProductInventoryChange(String company_id, String product_variety, String time){
        InventoryService service = new InventoryServiceImpl();
        ArrayList<InventoryProductItemPO> list = service.getProductInventoryItemByVariety(company_id, time, product_variety);

        ArrayList<InventoryChangeVo> result = new ArrayList<>();

        String first = getYearAndMonth(list.get(0).getDate());
        int inventory = 0;
        for(int i=0;i<list.size();i++){
            InventoryProductItemPO po = list.get(i);
            String now = getYearAndMonth(po.getDate());
            if(now.equals(first)){
                inventory = po.getRemainNum();
            }else{
                result.add(new InventoryChangeVo(first, inventory));
                first = now;
                inventory = po.getRemainNum();
            }
        }
        result.add(new InventoryChangeVo(first, inventory));
        return result;
    }

    /**
     * 原材料准时交货率与时间的关系
     * @param company_id 公司id
     * @param raw_material_variety 原材料种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<PunctualDeliveryRateChangeVo> getRawPunctualDeliveryRateChange(String company_id, String raw_material_variety, String time){
        InventoryService service = new InventoryServiceImpl();
        ArrayList<InventoryRawMaterialItemPO> list = service.getRawMaterialInventoryItemByVariety(company_id, time, raw_material_variety);

        ArrayList<PunctualDeliveryRateChangeVo> result = new ArrayList<>();

        String first = getYearAndMonth(list.get(0).getDate());
        int trade = 0;
        int delivery_on_time = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i=0;i<list.size();i++){
            InventoryRawMaterialItemPO po = list.get(i);
            String now = getYearAndMonth(po.getDate());
            if(now.equals(first)){
                trade++;
                if(po.isDeliveryOntime()){
                    delivery_on_time++;
                }
            }else{
                double punctual_delivery_rate = Double.valueOf(df.format(delivery_on_time/trade*100));
                result.add(new PunctualDeliveryRateChangeVo(first,punctual_delivery_rate));
                first = now;
                trade = 1;
                delivery_on_time = 0;
                if(po.isDeliveryOntime()){
                    delivery_on_time++;
                }
            }
        }
        double punctual_delivery_rate = Double.valueOf(df.format(delivery_on_time/trade*100));
        result.add(new PunctualDeliveryRateChangeVo(first,punctual_delivery_rate));
        return result;
    }

    /**
     * 产品准时交货率与时间的关系
     * @param company_id 公司id
     * @param product_variety 产品种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<PunctualDeliveryRateChangeVo> getProductPunctualDeliveryRateChange(String company_id, String product_variety, String time){
        InventoryService service = new InventoryServiceImpl();
        ArrayList<InventoryProductItemPO> list = service.getProductInventoryItemByVariety(company_id,time,product_variety);

        ArrayList<PunctualDeliveryRateChangeVo> result = new ArrayList<>();

        String first = getYearAndMonth(list.get(0).getDate());
        int trade = 0;
        int delivery_on_time = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i=0;i<list.size();i++){
            InventoryProductItemPO po = list.get(i);
            String now = getYearAndMonth(po.getDate());
            if(now.equals(first)){
                trade++;
                if(po.isDeliveryOntime())
                    delivery_on_time++;
            }else{
                double punctual_delivery_rate = Double.valueOf(df.format(delivery_on_time/trade*100));
                result.add(new PunctualDeliveryRateChangeVo(first,punctual_delivery_rate));
                first = now;
                trade = 1;
                delivery_on_time = 0;
                if(po.isDeliveryOntime())
                    delivery_on_time++;
            }
        }
        double punctual_delivery_rate = Double.valueOf(df.format(delivery_on_time/trade*100));
        result.add(new PunctualDeliveryRateChangeVo(first,punctual_delivery_rate));
        return result;
    }

    /**
     * 原材料退货率与时间的关系
     * @param company_id 公司id
     * @param raw_material_variety 原材料种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<RefundRateChangeVo> getRawRefundRateChange(String company_id, String raw_material_variety, String time){
        InventoryService service = new InventoryServiceImpl();
        ArrayList<InventoryRawMaterialItemPO> list = service.getRawMaterialInventoryItemByVariety(company_id, time, raw_material_variety);

        ArrayList<RefundRateChangeVo> result = new ArrayList<>();

        String first = getYearAndMonth(list.get(0).getDate());
        int trade = 0;
        int back = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i=0;i<list.size();i++){
            InventoryRawMaterialItemPO po = list.get(i);
            String now = getYearAndMonth(po.getDate());
            if(now.equals(first)){
                trade++;
                if(po.isReturn()){
                    back++;
                }
            }else{
                double refund_rate = Double.valueOf(df.format(back/trade*100));
                result.add(new RefundRateChangeVo(first,refund_rate));
                first = now;
                trade = 1;
                back = 0;
                if(po.isReturn()){
                    back++;
                }
            }
        }
        double refund_rate = Double.valueOf(df.format(back/trade*100));
        result.add(new RefundRateChangeVo(first,refund_rate));
        return result;
    }

    /**
     * 产品退货率与时间的关系
     * @param company_id 公司id
     * @param product_variety 产品种类
     * @param time 截止时间
     * @return
     */
    public ArrayList<RefundRateChangeVo> getProductRefundRateChange(String company_id, String product_variety, String time){
        InventoryService service = new InventoryServiceImpl();
        ArrayList<InventoryProductItemPO> list = service.getProductInventoryItemByVariety(company_id, time, product_variety);

        ArrayList<RefundRateChangeVo> result = new ArrayList<>();

        String first = getYearAndMonth(list.get(0).getDate());
        int trade = 0;
        int back = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i=0;i<list.size();i++){
            InventoryProductItemPO po = list.get(i);
            String now = getYearAndMonth(po.getDate());
            if(now.equals(first)){
                trade++;
                if(po.isReturn())
                    back++;
            }else{
                double refund_rate = Double.valueOf(df.format(back/trade*100));
                result.add(new RefundRateChangeVo(first,refund_rate));
                first = now;
                trade = 1;
                back = 0;
                if(po.isReturn())
                    back++;
            }
        }
        double refund_rate = Double.valueOf(df.format(back/trade*100));
        result.add(new RefundRateChangeVo(first,refund_rate));
        return result;
    }

    /**
     * 原材料库存量与安全库存量的关系
     * @param company_id 公司id
     * @return
     */
    public ArrayList<RawSafeInventoryRateVo> getRawSafeInventoryRate(String company_id){
        InventoryService service = new InventoryServiceImpl();
        ArrayList<RawMaterialSafeInventoryPo> list = service.getAllRawMaterialSafeInventory(company_id);
        ArrayList<RawSafeInventoryRateVo> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            RawMaterialSafeInventoryPo po = list.get(i);
            String variety = po.getRaw_material_variety();
            result.add(new RawSafeInventoryRateVo(variety,service.getRawInventory(company_id, variety),po.getSafe_inventory()));
        }
        return result;
    }

    /**
     * 产品库存量与安全库存量的关系
     * @param company_id 公司id
     * @return
     */
    public ArrayList<ProductSafeInventoryRateVo> getProductInventoryRate(String company_id){
        InventoryService service = new InventoryServiceImpl();
        ArrayList<ProductSafeInventoryPo> list = service.getAllProductSafeInventory(company_id);
        ArrayList<ProductSafeInventoryRateVo> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            ProductSafeInventoryPo po = list.get(i);
            String variety = po.getProduct_variety();
            result.add(new ProductSafeInventoryRateVo(variety,service.getProductInventory(company_id, variety),po.getSafe_inventory()));
        }
        return result;
    }

    /**
     * 得到某一种原材料的安全库存量
     * @param list 所有原材料安全库存量
     * @param raw_material_variety 原材料种类
     * @return
     */
    private int getSafeInventory(ArrayList<RawMaterialSafeInventoryPo> list, String raw_material_variety){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getRaw_material_variety().equals(raw_material_variety)){
                return list.get(i).getSafe_inventory();
            }
        }
        return 0;
    }

    /**
     * 得到某一种产品的安全库存量
     * @param list 所有产品安全库存量
     * @param product_variety 产品种类
     * @return
     */
    private int getProductSafeInventory(ArrayList<ProductSafeInventoryPo> list, String product_variety){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getProduct_variety().equals(product_variety)){
                return list.get(i).getSafe_inventory();
            }
        }
        return 0;
    }

    /**
     * 把timestamp转成只带年和月的string
     * @param timestamp
     * @return
     */
    private String getYearAndMonth(Timestamp timestamp){
        Calendar c = Calendar.getInstance();
        c.setTime(timestamp);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        return year+"-"+month;
    }
}
