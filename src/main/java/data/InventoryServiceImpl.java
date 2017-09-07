package data;

import dataservice.InventoryService;
import util.RandomString;
import vo.RawMaterialInventoryItemVo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/9/6 上午10:11
 */
public class InventoryServiceImpl implements InventoryService{

    SqlManager sqlManager = SqlManager.getSqlManager();


    @Override
    public void SaveSupplierInformationEntry(String emiter, String receiver, ArrayList<RawMaterialInventoryItemVo> list) {
        sqlManager.getConnection();

        String id = RandomString.getRandomString(10);
        ArrayList<String> idList = getAllListId();
        while(idList.contains(id)){
            id = RandomString.getRandomString(10);
        }
        insertInventoryIdList("s_id",id);

        List<Object> params1 = new ArrayList<>();
        params1.add(id);
        params1.add(emiter);
        params1.add(receiver);
        String sql = sqlManager.appendSQL("INSERT INTO inventory_supplier VALUES",params1.size());
        sqlManager.executeUpdateByList(sql,params1);
        sqlManager.commit();

        for (RawMaterialInventoryItemVo vo : list){
            List<Object> params2 = new ArrayList<>();
            params2.add(vo.getId());
            params2.add(id);
            params2.add(vo.getRawMaterial_variety());
            params2.add(vo.getVoucher_id());
            params2.add(Timestamp.valueOf(vo.getDatetime()));
            params2.add(vo.isIs_delivery_ontime());
            params2.add(vo.isIs_return());
            params2.add(vo.getInput_num());
            params2.add(vo.getInput_account());
            params2.add(vo.getOut_num());
            params2.add(vo.getOut_account());
            params2.add(vo.getBalance_num());
            params2.add(vo.getBalance_account());

            String sql2 = sqlManager.appendSQL("INSERT INTO inventory_supplier_list VALUES",params2.size());
            sqlManager.executeUpdateByList(sql2,params2);
            sqlManager.commit();
        }

        sqlManager.releaseAll();
    }

    @Override
    public int getMit(String time, String name) {
        return 0;
    }

    @Override
    public int getVit(String time, String name) {
        return 0;
    }

    @Override
    public int getVijt(String time, String name) {
        return 0;
    }

    private void insertInventoryIdList(String variety,String id){
        sqlManager.getConnection();
        List<Object> params = new ArrayList<>();
        params.add(variety);
        params.add(id);
        String sql = sqlManager.appendSQL("INSERT INTO inventory_id_list VALUES",params.size());
        sqlManager.executeUpdateByList(sql,params);
        sqlManager.commit();
    }

    private ArrayList<String> getAllListId(){
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT id FROM inventory_id_list";
        ArrayList<Map<String,Object>> maps = sqlManager.queryMulti(sql,new Object[]{});
        for (Map<String,Object> map : maps){
            list.add(map.get("id").toString());
        }
        return list;
    }
}
