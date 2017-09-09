package businesslogic;

import businesslogicservice.TrialSettlementService;
import data.CourseMessageServiceImpl;
import dataservice.CourseMessageService;
import po.SubjectsPO;
import po.VoucherAmountPO;
import vo.TrialTableItemVo;

import java.util.ArrayList;

/**
 * Created by 费慧通 on 2017/8/20.
 */
public class TrialSettlementImpl implements TrialSettlementService {
    /**
     * 根据凭证id和时期得到试算表的数据
     * @param company_id 公司id
     * @return
     */
    public ArrayList<TrialTableItemVo> getTrialTable(String company_id){
        CourseMessageService service = new CourseMessageServiceImpl();
        ArrayList<SubjectsPO> polist = service.getCurrentCouseMessage(company_id);
        ArrayList<TrialTableItemVo> result = new ArrayList<>();
        for(int i=0;i<polist.size();i++){
            SubjectsPO po = polist.get(i);
            result.add(new TrialTableItemVo(po.getId(), po.getName(), po.getDebitAmount(), po.getCreditAmount()));
        }
        return result;
    }
}
