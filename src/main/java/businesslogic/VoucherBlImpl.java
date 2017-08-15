package businesslogic;

import businesslogicservice.VoucherBlService;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import data.VoucherDataServiceImpl;
import dataservice.VoucherDataService;
import po.VoucherAmountPO;
import po.VoucherPO;
import po.VoucherTemplateAmountPO;
import po.VoucherTemplatePO;
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
        return false;
    }

    @Override
    public ArrayList<VoucherVo> importFromExcel(String filePath) {
        return null;
    }

    @Override
    public boolean amendOneVoucher(String voucherId, VoucherVo voucherVo) { return false;
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
}
