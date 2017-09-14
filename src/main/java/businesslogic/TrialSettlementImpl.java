package businesslogic;

import businesslogicservice.TrialSettlementService;
import businesslogicservice.VoucherBlService;
import data.CourseMessageServiceImpl;
import dataservice.CourseMessageService;
import po.SubjectsPO;
import po.VoucherAmountPO;
import vo.TrialTableItemVo;
import vo.voucher.VoucherVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/8/20.
 */
public class TrialSettlementImpl implements TrialSettlementService {
    private static ArrayList<TrialTableItemVo> list;
    /**
     * 根据凭证id和时期得到试算表的数据
     * @param company_id 公司id
     * @return
     */
    public ArrayList<TrialTableItemVo> getTrialTable(String company_id){
        CourseMessageService service = new CourseMessageServiceImpl();
        ArrayList<SubjectsPO> polist = service.getCurrentCouseMessage(company_id);
        ArrayList<TrialTableItemVo> result = new ArrayList<>();
        double debit_amount = 0.0;
        double credit_amount = 0.0;
        for(int i=0;i<polist.size();i++){
            SubjectsPO po = polist.get(i);
            double debit = po.getDebitAmount();
            double credit = po.getCreditAmount();
            result.add(new TrialTableItemVo(po.getId(), po.getName(), debit, credit));
            debit_amount = debit_amount+debit;
            credit_amount = credit_amount+credit;
        }
        result.add(new TrialTableItemVo("合计", "", debit_amount, credit_amount));
        list = result;
        return result;
    }

    @Override
    public boolean IsCreditDebitequal() {
        TrialTableItemVo vo = list.get(list.size()-1);
        if(vo.getDebit()==vo.getCredit()){
            return true;
        }
        return false;
    }

    /**
     * 管理员账号反结账
     * @param voucher_id 凭证id
     * @param voucherVo 修改的一行凭证信息
     * @param company_id 公司id
     */
    public void AntiSettle(String voucher_id, VoucherVo voucherVo, String company_id){
        VoucherBlService service = new VoucherBlImpl();
        service.amendOneVoucher(voucher_id, voucherVo, company_id);
    }
}
