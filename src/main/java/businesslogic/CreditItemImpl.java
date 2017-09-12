package businesslogic;

import businesslogicservice.CreditItemService;
import po.CreditItemPO;
import vo.CreditItemVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/12.
 */
public class CreditItemImpl implements CreditItemService {
    @Override
    public void SaveCreditItem(ArrayList<CreditItemVo> list, String company_id, String vocher_id) {
        ArrayList<CreditItemPO> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            CreditItemVo vo = list.get(i);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            if(vo.getCompany_name().substring(0,4).equals("应收账款")){
                try {
                    result.add(new CreditItemPO("应收账款", String.valueOf(i+1),vo.getCompany_name(), new java.sql.Date(sdf.parse(vo.getBorrow_time()).getTime()),new java.sql.Date(sdf.parse(vo.getDeadline()).getTime()),vo.getMoney(),
                                vo.getPolicy(),new java.sql.Date(sdf.parse(vo.getDiscount_deadline()).getTime()),vo.getRemark()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    result.add(new CreditItemPO("应付账款", String.valueOf(i+1),vo.getCompany_name(), new java.sql.Date(sdf.parse(vo.getBorrow_time()).getTime()),new java.sql.Date(sdf.parse(vo.getDeadline()).getTime()),vo.getMoney(),
                            vo.getPolicy(),new java.sql.Date(sdf.parse(vo.getDiscount_deadline()).getTime()),vo.getRemark()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public ArrayList<CreditItemVo> getReceivables(String company_id, String voucher_id) {
        return null;
    }

    @Override
    public ArrayList<CreditItemVo> getAccountsPayable(String company_id, String voucher_id) {
        return null;
    }
}
