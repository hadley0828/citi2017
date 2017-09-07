package businesslogic;

import businesslogicservice.TrialSettlementService;
import data.CourseMessageServiceImpl;
import dataservice.CourseMessageService;
import po.VoucherAmountPO;
import vo.TrialTableItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/8/20.
 */
public class TrialSettlementImpl implements TrialSettlementService {
    /**
     * 根据凭证id和时期得到试算表的数据
     * @param voucher_id 凭证id
     * @param time 时期
     * @return
     */
    public ArrayList<TrialTableItemVo> getTrialTable(String voucher_id, String time){
        CourseMessageService service = new CourseMessageServiceImpl();
        ArrayList<VoucherAmountPO> polist = service.getCourseMessageByTime(time);
        ArrayList<TrialTableItemVo> result = new ArrayList<>();
        for(int i=0;i<polist.size();i++){
            VoucherAmountPO po = polist.get(i);
            String id = po.getSubject();
            String name = service.getCourseNameById(id);
            result.add(new TrialTableItemVo(id, name, po.getDebitAmount(), po.getCreditAmount()));
        }
        return result;
    }
}
