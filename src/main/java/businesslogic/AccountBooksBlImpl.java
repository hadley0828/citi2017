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
import java.util.HashMap;

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


        java.lang.String startMonth=DateConvert.periodToMonth(searchVo.getStartPeriod());
        java.lang.String endMonth=DateConvert.periodToMonth(searchVo.getEndPeriod());
        ArrayList<String> betweenMonthList=DateConvert.getBetweenMonthList(startMonth,endMonth);

        if(!(searchVo.getStartSubjectId()==null||searchVo.getEndSubjectId()==null)){
            int startSubjectId=Integer.valueOf(searchVo.getStartSubjectId());
            int endSubjectId=Integer.valueOf(searchVo.getEndSubjectId());
            int currentSubjectId=Integer.valueOf(java.lang.String.valueOf(subjectId));

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

        //需要对subjectsPOArrayList按照时间来进行排序
        ArrayList<SubjectsPO> subjectsPOArrayList=subjectDataService.getOneSubjectAllRecords(subjectId,factoryId);
        //处理betweenMonthList

        //用来先把结果进行分类
        HashMap<String,ArrayList<DetailAccountAmountVo>> monthToDetailMap=new HashMap<>();

        for(int count=betweenMonthList.indexOf(startMonth);count<=betweenMonthList.indexOf(endMonth);count++){
            String month=betweenMonthList.get(count);
            ArrayList<DetailAccountAmountVo> newList=new ArrayList<>();
            monthToDetailMap.put(month,newList);
        }

        //首先得到期初的金额
        DetailAccountAmountVo beginNumber=new DetailAccountAmountVo();
        beginNumber.setDate(startMonth+"-01");
        beginNumber.setSubject(subjectId);
        beginNumber.setAbstracts("期初余额");
        beginNumber.setDebitAmount(0.0);
        beginNumber.setCreditAmount(0.0);
        beginNumber.setDirection("平");
        beginNumber.setBalance(0.0);

        






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
