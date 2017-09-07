package dataservice;

import vo.RawMaterialInventoryItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/5.
 */
public interface InventoryService {
    /**
     * 保存供应商库存情况信息录入
     * @param emiter 发出方
     * @param receiver 收入方
     * @param list 表格输入数据
     */
    public void SaveSupplierInformationEntry(String emiter, String receiver, ArrayList<RawMaterialInventoryItemVo> list);

    /**
     * 获取t时期供应商i的原材料的库存
     * @param time 时间
     * @param name 供应商名称
     * @return
     */
    public int getMit(String time, String name);

    /**
     * 获取t时期被运进供应商i的原材料的数量
     * @param time 时间
     * @param name 供应商名称
     * @return
     */
    public int getVit(String time, String name);


    /**
     * 获取t时期从供应商i运送到其他所有生产商的原材料的数量
     * @param time 时间
     * @param name 供应商名称
     * @return
     */
    public int getVijt(String time, String name);
}
