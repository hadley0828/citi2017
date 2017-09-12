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
        ArrayList<String> betweenMonthList=DateConvert.getBetweenMonthList(startMonth,endMonth);

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


        //如果需要得到一个期间的期初的金额 先把第一天到最后一个搜索月的信息取出来 然后进行遍历筛选

        ArrayList<SubjectsPO> subjectsPOArrayList=subjectDataService.getOneSubjectAllRecords(subjectId,factoryId);
        //betweenMonthList







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

    @Override
    public ArrayList<SubjectsPO> getAllSubjectPeriodEndPrice(String period, String factoryId) {
        return null;
    }


}
