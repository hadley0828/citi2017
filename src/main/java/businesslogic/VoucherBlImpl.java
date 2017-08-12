package businesslogic;

import businesslogicservice.VoucherBlService;
import po.VoucherPO;
import vo.voucher.AmountTotalVo;
import vo.voucher.VoucherSearchVo;
import vo.voucher.VoucherTemplateVo;
import vo.voucher.VoucherVo;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/8/7 下午10:41
 */
public class VoucherBlImpl implements VoucherBlService {

    @Override
    public boolean saveOneVoucher(VoucherPO voucherPO) {
        return false;
    }

    @Override
    public AmountTotalVo getVoucherTotal(VoucherPO voucherPO) {
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
}
