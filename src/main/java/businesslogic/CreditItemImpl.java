package businesslogic;

import businesslogicservice.CreditItemService;
import data.CreditItemServiceImpl;
import po.CreditItemPO;
import vo.CreditItemVo;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/9/12.
 */
public class CreditItemImpl implements CreditItemService {
    @Override
    public void SaveCreditItem(ArrayList<CreditItemVo> list, String company_id, String voucher_id) {
        ArrayList<CreditItemPO> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            CreditItemVo vo = list.get(i);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            if(vo.getCompany_name().substring(0,4).equals("应收账款")){
                try {
                    result.add(new CreditItemPO("应收账款", String.valueOf(i+1), voucher_id, vo.getCompany_name(), new java.sql.Date(sdf.parse(vo.getBorrow_time()).getTime()),new java.sql.Date(sdf.parse(vo.getDeadline()).getTime()),vo.getMoney(),
                                vo.getPolicy(),new java.sql.Date(sdf.parse(vo.getDiscount_deadline()).getTime()),vo.getRemark()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    result.add(new CreditItemPO("应付账款", String.valueOf(i+1), voucher_id, vo.getCompany_name(), new java.sql.Date(sdf.parse(vo.getBorrow_time()).getTime()),new java.sql.Date(sdf.parse(vo.getDeadline()).getTime()),vo.getMoney(),
                            vo.getPolicy(),new java.sql.Date(sdf.parse(vo.getDiscount_deadline()).getTime()),vo.getRemark()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        dataservice.CreditItemService service = new CreditItemServiceImpl();
        service.SaveCreditItem(result, company_id);
    }

    @Override
    public ArrayList<CreditItemVo> getReceivables(String company_id, String voucher_id) {
        dataservice.CreditItemService service = new CreditItemServiceImpl();
        ArrayList<CreditItemPO> list = service.getReceivables(company_id, voucher_id);
        return getList(list);
    }

    @Override
    public ArrayList<CreditItemVo> getAccountsPayable(String company_id, String voucher_id) {
        dataservice.CreditItemService service = new CreditItemServiceImpl();
        ArrayList<CreditItemPO> list = service.getAccountsPayable(company_id, voucher_id);
        return getList(list);
    }

    @Override
    public boolean IsExpire(String company_id, String voucher_id) {
        dataservice.CreditItemService service = new CreditItemServiceImpl();
        ArrayList<CreditItemPO> list = service.getReceivables(company_id, voucher_id);
        boolean result = false;
        for(int i=0;i<list.size();i++){
            CreditItemPO po = list.get(i);
            LocalDate borrow_time = po.getDebitDate().toLocalDate();
            LocalDate deadline = po.getDdl().toLocalDate();
            LocalDate discount = po.getDiscountDate().toLocalDate();
            LocalDate today = LocalDate.now();
            int time = (int)borrow_time.until(deadline, ChronoUnit.DAYS);
            int days = (int)borrow_time.until(discount, ChronoUnit.DAYS);
            int now1 = (int)today.until(deadline, ChronoUnit.DAYS);
            int now2 = (int)today.until(discount, ChronoUnit.DAYS);
            if(now1<time*0.3||now2<days*0.3){
                result = true;
            }
        }
        return result;
    }

    private ArrayList<CreditItemVo> getList(ArrayList<CreditItemPO> list){
        ArrayList<CreditItemVo> result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            CreditItemPO po = list.get(i);
            result.add(new CreditItemVo(po.getCompanyName(),po.getDebitDate().toString(),po.getDdl().toString(),po.getCreditNum(),po.getDiscountPolicy(),po.getDiscountDate().toString(),po.getComment()));
        }
        return result;
    }
}
