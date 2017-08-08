package data;

import dataservice.CourseMessageService;
import po.VoucherAmountPO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by loohaze on 2017/8/8.
 */
public class CourseMessageServiceImpl implements CourseMessageService{

    SqlManager sqlManager = SqlManager.getSqlManager();

    @Override
    public ArrayList<VoucherAmountPO> getCourseMessageById(String voucher_id) {

        sqlManager.getConnection();

        ArrayList<VoucherAmountPO> list = new ArrayList<>();

        String sql = "SELECT * FROM voucher_amount WHERE v_id=?";
        List<Map<String,Object>> mapList =  sqlManager.queryMulti(sql,new Object[]{voucher_id});

        for (Map<String,Object> map : mapList){
            list.add(getVoucherAmountPO(map));
        }

        sqlManager.releaseAll();
        return list;
    }

    private VoucherAmountPO getVoucherAmountPO(Map<String,Object> map){

        VoucherAmountPO po = new VoucherAmountPO();
        po.setId(map.get("v_id").toString());
        po.setDigest(map.get("abstract").toString());
        po.setSubject(map.get("subject").toString());
        po.setDebitAmount(Double.parseDouble(map.get("debit_amount").toString()));
        po.setCreditAmount(Double.parseDouble(map.get("credit_amount").toString()));

        return po;
    }
}
