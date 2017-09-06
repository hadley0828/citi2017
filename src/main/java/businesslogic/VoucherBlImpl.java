package businesslogic;

import businesslogicservice.VoucherBlService;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import data.VoucherDataServiceImpl;
import dataservice.VoucherDataService;
import po.VoucherAmountPO;
import po.VoucherPO;
import po.VoucherTemplateAmountPO;
import po.VoucherTemplatePO;
import util.DateConvert;
import util.NowString;
import util.NumberToCN;
import vo.voucher.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by zhangzy on 2017/8/7 下午10:41
 */
public class VoucherBlImpl implements VoucherBlService {

    private VoucherDataService voucherDataService;

    public VoucherBlImpl(){
        voucherDataService=new VoucherDataServiceImpl();
    }

    @Override
    public boolean saveOneVoucher(VoucherVo voucherVo) {
        VoucherPO voucherPO=new VoucherPO();

        voucherPO.setId(voucherVo.getVoucherId());
        voucherPO.setDate(Date.valueOf(voucherVo.getDate()));
        voucherPO.setAddReceipts(voucherVo.isAddedReceipts());
        voucherPO.setVoucher_maker(voucherVo.getVoucherMaker());
        voucherPO.setRemark(voucherVo.getRemark());

        ArrayList<VoucherAmountVo> amountVoList=voucherVo.getAmountList();
        ArrayList<VoucherAmountPO> amountPoList=new ArrayList<>();



        if(amountVoList.size()!=0){
            for(int count=0;count<amountVoList.size();count++){
                VoucherAmountVo voucherAmountVo=amountVoList.get(count);
                VoucherAmountPO voucherAmountPO=new VoucherAmountPO(voucherAmountVo);
                String subjectId=voucherAmountVo.getSubject();
                double beforeBalance=voucherDataService.findOneSubjectBalance(subjectId);
                //实现修改一个会计科目的余额的功能
                voucherDataService.modifyOneSubjectBalance(subjectId,beforeBalance+voucherAmountVo.getDebitAmount()-voucherAmountVo.getCreditAmount());

                amountPoList.add(voucherAmountPO);
            }
        }

        boolean result=true;

        //调用两个数据层的方法可能会出现一部分完成一部分出错的情况
        boolean result1=voucherDataService.addVoucher(voucherPO);
        boolean result2=voucherDataService.addOneVoucherAllAmount(voucherPO.getId(),amountPoList);

        result=result&result1&result2;


        return result;
    }

    @Override
    public AmountTotalVo getVoucherTotal(ArrayList<VoucherAmountVo> amountVoArrayList) {
        AmountTotalVo resultVo=new AmountTotalVo();

        String chineseTotal;
        double debitTotal=0.0;
        double creditTotal=0.0;

        if(amountVoArrayList.size()!=0){
            for(int count=0;count<amountVoArrayList.size();count++){
                VoucherAmountVo vo=amountVoArrayList.get(count);
                debitTotal=debitTotal+vo.getDebitAmount();
                creditTotal=creditTotal+vo.getCreditAmount();

            }
        }

        if(debitTotal==creditTotal){
            chineseTotal=NumberToCN.number2CNMontrayUnit(creditTotal);
        }else {
            chineseTotal="借款金额和贷款金额不相同";
        }

        resultVo.setChineseTotal(chineseTotal);
        resultVo.setDebitAmount(debitTotal);
        resultVo.setCreditAmount(creditTotal);

        return resultVo;
    }

    @Override
    public double getOneSubjectBalance(String subjectId) {
        return voucherDataService.findOneSubjectBalance(subjectId);
    }

    @Override
    public double getNewSubjectBalance(double beforeNumber, double changeNumber) {
        return beforeNumber+changeNumber;
    }

    @Override
    public boolean changeSubjectBalance(String subjectId, double newNumber) {
        boolean result=true;

        boolean result1=voucherDataService.modifyOneSubjectBalance(subjectId, newNumber);
        result=result&result1;

        return result;
    }

    @Override
    public VoucherTemplateVo getOneTemplate(String templateId) {
        VoucherTemplatePO po=voucherDataService.getOneTemplate(templateId);
        ArrayList<VoucherTemplateAmountPO> amountPOArrayList=voucherDataService.getOneTemplateAllAmount(templateId);

        VoucherTemplateVo resultVo=new VoucherTemplateVo();
        resultVo.setTemplateId(po.getTemplateId());
        resultVo.setCategory(po.getCatagory());
        resultVo.setTemplateName(po.getTemplateName());

        ArrayList<VoucherTemplateAmountVo> amountVoList=new ArrayList<>();

        if(amountPOArrayList.size()!=0){
            for(int count=0;count<amountPOArrayList.size();count++){
                VoucherTemplateAmountPO amountPO=amountPOArrayList.get(count);
                VoucherTemplateAmountVo amountVo=new VoucherTemplateAmountVo(amountPO);
                amountVoList.add(amountVo);
            }
        }

        resultVo.setAmountList(amountVoList);

        return resultVo;
    }

    @Override
    public boolean addOneTemplate(VoucherTemplateVo voucherTemplateVo) {
        ArrayList<VoucherTemplatePO> poList=voucherDataService.getAllTemplate();

        HashSet<String> allTemplateIdSet=new HashSet<>();
        if(poList.size()!=0){
            for(int count=0;count<poList.size();count++){
                allTemplateIdSet.add(poList.get(count).getTemplateId());
            }
        }

        boolean result;

        if(allTemplateIdSet.contains(voucherTemplateVo.getTemplateId())){
            result=false;
        }else{
            //处理模板
            VoucherTemplatePO voucherTemplatePO=new VoucherTemplatePO(voucherTemplateVo);


            //处理模板金额
            ArrayList<VoucherTemplateAmountVo> amountVoArrayList=voucherTemplateVo.getAmountList();
            ArrayList<VoucherTemplateAmountPO> amountPOArrayList=new ArrayList<>();

            if(amountVoArrayList.size()!=0){
                for(int count=0;count<amountVoArrayList.size();count++){
                    VoucherTemplateAmountVo oneAmountVo=amountVoArrayList.get(count);
                    VoucherTemplateAmountPO oneAmountPo=new VoucherTemplateAmountPO(oneAmountVo);

                    amountPOArrayList.add(oneAmountPo);
                }
            }

            boolean result1=voucherDataService.addOneTemplate(voucherTemplatePO);
            boolean result2=voucherDataService.addOneTemplateAmounts(voucherTemplateVo.getTemplateId(),amountPOArrayList);
            result=result1&result2;
        }

        return result;

    }

    @Override
    public ArrayList<VoucherVo> getCurrentPeriodAllVoucher() {

        String currentMonth= NowString.getNowMonth();

        //全部的凭证信息
        ArrayList<VoucherPO> allPoList=voucherDataService.findAllVoucher();
        //全屏的凭证金额信息
        HashMap<String,ArrayList<VoucherAmountPO>> voucherToAmountMap=voucherDataService.findAllVoucherAllAmount();

        ArrayList<VoucherVo> resultList=new ArrayList<>();

        for(int count=0;count<allPoList.size();count++){
            VoucherPO po=allPoList.get(count);
            String date=String.valueOf(po.getDate());

            if(date.contains(currentMonth)){
                ArrayList<VoucherAmountPO> amountPOArrayList=voucherToAmountMap.get(po.getId());
                //po 和 amountPOArrayList
                VoucherVo oneVo=new VoucherVo(po);

                ArrayList<VoucherAmountVo> amountVoList=new ArrayList<>();


                double debitTotal=0.0;
                double creditTotal=0.0;
                if(amountPOArrayList.size()!=0){
                    for(int index=0;index<amountPOArrayList.size();index++){
                        VoucherAmountPO oneAmountPO=amountPOArrayList.get(index);
                        debitTotal=debitTotal+oneAmountPO.getDebitAmount();
                        creditTotal=creditTotal+oneAmountPO.getCreditAmount();

                        VoucherAmountVo oneAmountVo=new VoucherAmountVo(oneAmountPO);
                        amountVoList.add(oneAmountVo);

                    }
                }

                AmountTotalVo amountTotalVo=new AmountTotalVo();
                String chineseTotal=NumberToCN.number2CNMontrayUnit(creditTotal);
                amountTotalVo.setChineseTotal(chineseTotal);
                amountTotalVo.setDebitAmount(debitTotal);
                amountTotalVo.setCreditAmount(creditTotal);

                oneVo.setAmountList(amountVoList);
                oneVo.setAmountTotalVo(amountTotalVo);
                resultList.add(oneVo);

            }
        }
        return resultList;
    }

    @Override
    public VoucherVo getOneVoucher(String voucherId) {

        ArrayList<VoucherPO> allPoList=voucherDataService.findAllVoucher();
        HashSet<String> allVoucherIdSet=new HashSet<>();

        for(int count=0;count<allPoList.size();count++){
            allVoucherIdSet.add(allPoList.get(count).getId());
        }

        if(!allVoucherIdSet.contains(voucherId)){
            return null;
        }else{
            //凭证信息
            VoucherPO voucherPO=voucherDataService.findOneVoucher(voucherId);
            //凭证的全部金额
            ArrayList<VoucherAmountPO> amountPOArrayList=voucherDataService.findOneVoucherAllAmount(voucherId);

            VoucherVo resultVo=new VoucherVo(voucherPO);

            ArrayList<VoucherAmountVo> amountVoList=new ArrayList<>();
            AmountTotalVo amountTotalVo=new AmountTotalVo();

            double debitAmount=0.0;
            double creditAmount=0.0;
            for(int count=0;count<amountPOArrayList.size();count++){
                VoucherAmountVo oneAmountVo=new VoucherAmountVo(amountPOArrayList.get(count));
                amountVoList.add(oneAmountVo);
                debitAmount=debitAmount+amountPOArrayList.get(count).getDebitAmount();
                creditAmount=creditAmount+amountPOArrayList.get(count).getCreditAmount();

            }

            amountTotalVo.setChineseTotal(NumberToCN.number2CNMontrayUnit(creditAmount));
            amountTotalVo.setDebitAmount(debitAmount);
            amountTotalVo.setCreditAmount(creditAmount);

            resultVo.setAmountList(amountVoList);
            resultVo.setAmountTotalVo(amountTotalVo);

            return resultVo;
        }
    }

    @Override
    public ArrayList<VoucherVo> getSearchedVoucher(VoucherSearchVo voucherSearchVo) {
        ArrayList<VoucherVo> allVoucherVoList=new ArrayList<>();
        ArrayList<VoucherPO> allVoucherPoList=voucherDataService.findAllVoucher();
        HashMap<String,ArrayList<VoucherAmountPO>> allVoucherMap=voucherDataService.findAllVoucherAllAmount();

        ArrayList<VoucherVo> resultVoList=new ArrayList<>();

        //先获取数据库中所有存在的凭证信息
        if(allVoucherPoList.size()==0){
            return null;
        }else{
            for(int count=0;count<allVoucherPoList.size();count++){
                VoucherPO voucherPO=allVoucherPoList.get(count);
                String voucherId=voucherPO.getId();

                if(!allVoucherMap.containsKey(voucherId)){
                    break;
                }else{
                    ArrayList<VoucherAmountPO> amountPOList=allVoucherMap.get(voucherId);
                    //对voucherPO和amountPOList进行处理
                    VoucherVo oneVoucherVo=new VoucherVo(voucherPO);
                    ArrayList<VoucherAmountVo> amountVoArrayList=new ArrayList<>();

                    for(int index=0;index<amountPOList.size();index++){
                        amountVoArrayList.add(new VoucherAmountVo(amountPOList.get(index)));
                    }
                    AmountTotalVo amountTotalVo=new AmountTotalVo(amountPOList);

                    oneVoucherVo.setAmountList(amountVoArrayList);
                    oneVoucherVo.setAmountTotalVo(amountTotalVo);

                    allVoucherVoList.add(oneVoucherVo);


                }
            }
        }

        //用户输入的搜索信息 voucherSearchVo

        //对全部的凭证信息根据搜索的vo进行筛选 voucherSearchVo
        for(int count=0;count<allVoucherVoList.size();count++){
            VoucherVo thisVo=allVoucherVoList.get(count);
            if(isOneVoucherVoSearched(voucherSearchVo,thisVo)){
                resultVoList.add(thisVo);
            }
        }

        //按照用户输入的排序方式来进行排序  1是凭证号排序 2是凭证日期排序 把每一期的都分开来
        //resultVoList是没有排序之前的列表

        //TODO


        return null;
    }

    @Override
    public boolean deleteSelectedVoucher(ArrayList<String> voucherIdList) {
        boolean result=true;

        for(int count=0;count<voucherIdList.size();count++){
            String voucherId=voucherIdList.get(count);

            boolean result1=voucherDataService.deleteOneVoucher(voucherId);
            boolean result2=voucherDataService.deleteOneVoucherAllAmount(voucherId);
            result=result&result1&result2;
        }
        return result;
    }

    @Override
    public boolean exportToExcel(ArrayList<String> voucherIdList) {
        if(voucherIdList.size()==0){
            return false;
        }else{



        }
        return false;
    }

    @Override
    public ArrayList<VoucherVo> importFromExcel(String filePath) {
        //TODO
        return null;
    }

    @Override
    public boolean amendOneVoucher(String voucherId, VoucherVo voucherVo) {
        String afterVoucherId=voucherVo.getVoucherId();

        HashSet<String> allVoucherIdSet=new HashSet<>();
        ArrayList<VoucherPO> allPoList=voucherDataService.findAllVoucher();
        for(int count=0;count<allPoList.size();count++){
            allVoucherIdSet.add(allPoList.get(count).getId());
        }

        //修改了凭证的编号 并且 修改后的编号在数据库已经存在
        if((!voucherId.equals(afterVoucherId))&&allVoucherIdSet.contains(afterVoucherId)){
            return false;
        }else{

            VoucherPO voucherPO=new VoucherPO(voucherVo);

            ArrayList<VoucherAmountPO> amountPOArrayList=new ArrayList<>();
            ArrayList<VoucherAmountVo> amountVoArrayList=voucherVo.getAmountList();

            //先删除再添加! TODO


        }


        return false;
    }

    @Override
    public boolean deleteOneVoucher(String voucherId) {
        return false;
    }

    @Override
    public VoucherVo copyOneVoucher(String voucherId) {
        return null;
    }

    @Override
    public boolean addOneSubject() {
        return false;
    }

    @Override
    public int getCurrentNumber(String voucherCharacter) {
        return 0;
    }

    /**
     * 用来判断一个VoucherVo是否符合搜索条件
     * @param searchVo
     * @param vo
     * @return
     */
    private boolean isOneVoucherVoSearched(VoucherSearchVo searchVo,VoucherVo vo){
        boolean result=true;

        //有一个条件不满足就返回false 全部满足才能返回true

        //处理会计期间
        if(searchVo.getStartPeriod().equals(searchVo.getEndPeriod())){
            String period=searchVo.getStartPeriod();
            String oneMonth= DateConvert.periodToMonth(period);

            //月份不包含搜索的月就直接返回false
            if(!vo.getDate().contains(oneMonth)){
                return false;
            }
        }else{
            String startMonth=DateConvert.periodToMonth(searchVo.getStartPeriod());
            String endMonth=DateConvert.periodToMonth(searchVo.getEndPeriod());
            HashSet<String> betweenMonthSet=DateConvert.getBetweenMonth(startMonth,endMonth);

            String currentMonth=vo.getDate().substring(0,vo.getDate().lastIndexOf("-"));

            if(!betweenMonthSet.contains(currentMonth)){
                return false;
            }
        }

        //处理凭证字
        String searchCharacter=searchVo.getCharacter();
        if(!searchCharacter.equals("全部")){
            if(!searchCharacter.equals(vo.getVoucherId().split("-")[0])){
                return false;
            }
        }

        //处理制单人
        String searchMaker=searchVo.getMaker();
        if(!searchMaker.equals("全部")){
            if(!searchMaker.equals(vo.getVoucherMaker())){
                return false;
            }
        }

        //处理摘要和科目id 摘要和科目id都是包含的关系
        String searchAbstract=searchVo.getAbstracts();
        String searchSubject=searchVo.getSubjectId();

        ArrayList<VoucherAmountVo> amountVoArrayList=vo.getAmountList();

        //每一行都没有摘要 每一行都没有科目id才返回false

        //需要分别处理摘要和科目id

        boolean abstractResult=false;
        if(!(searchAbstract==null||searchAbstract.length()<=0)){
            for(int count=0;count<amountVoArrayList.size();count++){
                VoucherAmountVo oneAmountVo=amountVoArrayList.get(count);
                boolean result1;
                String oneAbstract=oneAmountVo.getAbstracts();

                if(searchAbstract.indexOf(oneAbstract)!=-1||oneAbstract.indexOf(searchAbstract)!=-1){
                    result1=true;
                }else{
                    result1=false;
                }

                abstractResult=abstractResult||result1;
            }

            if(abstractResult==false){
                return false;
            }
        }

        boolean subjectResult=false;
        if(!(searchSubject==null||searchSubject.length()<=0)){
            for(int count=0;count<amountVoArrayList.size();count++){
                VoucherAmountVo oneAmountVo=amountVoArrayList.get(count);
                boolean result2;
                String oneSubject=oneAmountVo.getSubject();

                if(searchSubject.indexOf(oneSubject)!=-1||oneSubject.indexOf(searchSubject)!=-1){
                    result2=true;
                }else{
                    result2=false;
                }

                subjectResult=subjectResult||result2;
            }

            if(subjectResult==false){
                return false;
            }
        }

        //处理金额
        double searchLowPrice=searchVo.getLowPrice();
        double searchHighPrice=searchVo.getHighPrice();

        if(!(searchLowPrice==-1.0&&searchHighPrice==-1.0)){
            double currentPrice=vo.getAmountTotalVo().getCreditAmount();
            if(searchLowPrice==-1.0){
                if(currentPrice>searchHighPrice){
                    return false;
                }
            }else if(searchHighPrice==-1.0){
                if(currentPrice<searchLowPrice){
                    return false;
                }


            }else{
                if(currentPrice<searchLowPrice||currentPrice>searchHighPrice){
                    return false;
                }
            }
        }

        //处理凭证号
        int searchLowNumber=searchVo.getLowVoucherNumber();
        int searchHighNumber=searchVo.getHighVoucherNumber();

        if(!(searchLowNumber==-1&&searchHighNumber==-1)){
            int currentNumber=Integer.valueOf(vo.getVoucherId().split("-")[1]);
            if(searchLowNumber==-1){
                if(currentNumber>searchHighNumber){
                    return false;
                }
            }else if(searchHighNumber==-1){

                if(currentNumber<searchLowNumber){
                    return false;
                }

            }else{
                if(currentNumber<searchLowNumber||currentNumber>searchHighNumber){
                    return false;
                }
            }
        }


        return result;
    }
}
