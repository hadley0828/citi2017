package businesslogic;

import businesslogicservice.AccountBooksBlService;
import data.SubjectDataServiceImpl;
import data.VoucherDataServiceImpl;
import dataservice.SubjectDataService;
import dataservice.VoucherDataService;
import po.SubjectsPO;
import util.DateConvert;
import util.SubjectBalanceHelper;
import vo.accountBook.*;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by zhangzy on 2017/8/7 下午10:45
 */
public class AccountBooksBlImpl implements AccountBooksBlService {

    private VoucherDataService voucherDataService;
    private SubjectDataService subjectDataService;

    public AccountBooksBlImpl(){

        voucherDataService=new VoucherDataServiceImpl();
        subjectDataService=new SubjectDataServiceImpl();
    }

    @Override
    public ArrayList<String> getAllExistedSubjectId(String factoryId) {
        return subjectDataService.getAllExistedSubjectId(factoryId);
    }

    @Override
    public DetailAccountVo getOneSubjectDetail(String subjectId, BookSearchVo searchVo, String factoryId) {
        DetailAccountVo resultvo=new DetailAccountVo();
        ArrayList<DetailAccountAmountVo> resultAmountVo=new ArrayList<>();


        String startMonth=DateConvert.periodToMonth(searchVo.getStartPeriod());
        String endMonth=DateConvert.periodToMonth(searchVo.getEndPeriod());
        HashSet<String> betweenMonth=DateConvert.getBetweenMonth(startMonth,endMonth);

        ArrayList<String> betweenMonthList=new ArrayList<>();
        for(String str:betweenMonth){
            betweenMonthList.add(str);
        }

        if(!(searchVo.getStartSubjectId()==null||searchVo.getEndSubjectId()==null)){
            int startSubjectId=Integer.valueOf(searchVo.getStartSubjectId());
            int endSubjectId=Integer.valueOf(searchVo.getEndSubjectId());
            int currentSubjectId=Integer.valueOf(subjectId);

            if(currentSubjectId<startSubjectId||endSubjectId<currentSubjectId){
                return null;
            }

        }

        if(!(searchVo.getLowLevel()==0||searchVo.getHighLevel()==0)){
            if(searchVo.getLowLevel()>searchVo.getHighLevel()){
                return null;
            }else{
                int subjectLevel= SubjectBalanceHelper.getSubjectLevel(subjectId);
                if(subjectLevel<searchVo.getLowLevel()||searchVo.getHighLevel()<subjectLevel){
                    return null;
                }
            }
        }


        ArrayList<SubjectsPO> subjectsPOList=subjectDataService.findMonthsAllSubjects(betweenMonthList,factoryId);




        return null;
    }

    @Override
    public ArrayList<TotalAccountVo> getAllSubjectTotal(BookSearchVo searchVo, String factoryId) {
        return null;
    }

    @Override
    public TotalAccountVo getOneSubjectTotal(String subjectId, BookSearchVo searchVo, String factoryId) {
        return null;
    }

    @Override
    public ArrayList<BalanceTableOneClause> getBalanceTableAllClauses(BookSearchVo searchVo, String factoryId) {
        return null;
    }

    @Override
    public ArrayList<GatherTableOneClause> getGatherTableAllClauses(BookSearchVo searchVo, String factoryId) {
        return null;
    }
}
