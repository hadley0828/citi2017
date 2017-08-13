package businesslogic;

import businesslogicservice.VoucherBlService;
import data.VoucherDataServiceImpl;
import dataservice.VoucherDataService;
import po.VoucherPO;
import vo.voucher.*;

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





        return false;
    }

    @Override
    public AmountTotalVo getVoucherTotal(ArrayList<VoucherAmountVo> amountVoArrayList) {
        return null;
    }

    @Override
    public double getOneSubjectBalance(String subjectId) {
        return 0;
    }

    @Override
    public double getNewSubjectBalance(double beforeNumber, double changeNumber) {
        return 0;
    }

    @Override
    public boolean changeSubjectBalance(String subjectId, double newNumber) {
        return false;
    }

    @Override
    public VoucherTemplateVo getOneTemplate(String templateId) {
        return null;
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
