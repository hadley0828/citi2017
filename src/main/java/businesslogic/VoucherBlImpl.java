package businesslogic;

import businesslogicservice.VoucherBlService;
import data.VoucherDataServiceImpl;
import dataservice.VoucherDataService;
import po.VoucherAmountPO;
import po.VoucherPO;
import po.VoucherTemplateAmountPO;
import po.VoucherTemplatePO;
import util.NumberToCN;
import vo.voucher.*;

import java.sql.Date;
import java.util.ArrayList;

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
                amountPoList.add(voucherAmountPO);
            }
        }

        boolean result=true;

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
        return false;
    }

    @Override
    public ArrayList<VoucherVo> getCurrentPeriodAllVoucher() {
        return null;
    }

    @Override
    public VoucherVo getOneVoucher(String voucherId) {
        return null;
    }

    @Override
    public ArrayList<VoucherVo> getSearchedVoucher(VoucherSearchVo voucherSearchVo) {
        return null;
    }

    @Override
    public boolean deleteSelectedVoucher(ArrayList<String> voucherIdList) {
        return false;
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
    public boolean amendOneVoucher(String voucherId, VoucherVo voucherVo) {
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
}
