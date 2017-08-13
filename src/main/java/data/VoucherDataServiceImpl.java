package data;

import dataservice.VoucherDataService;
import po.VoucherAmountPO;
import po.VoucherPO;
import po.VoucherTemplateAmountPO;
import po.VoucherTemplatePO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by loohaze on 2017/8/13.
 */
public class VoucherDataServiceImpl implements VoucherDataService {
    @Override
    public boolean addVoucher(VoucherPO voucherPO) {
        return false;
    }

    @Override
    public boolean deleteOneVoucher(String voucherId) {
        return false;
    }

    @Override
    public boolean deleteAllVoucher() {
        return false;
    }

    @Override
    public boolean modifyOneVoucher(String voucherId, VoucherPO voucherPO) {
        return false;
    }

    @Override
    public VoucherPO findOneVoucher(String voucherId) {
        return null;
    }

    @Override
    public ArrayList<VoucherPO> findSeveralVoucher(ArrayList<String> idList) {
        return null;
    }

    @Override
    public ArrayList<VoucherPO> findAllVoucher() {
        return null;
    }

    @Override
    public boolean addOneVoucherAllAmount(String voucherId, ArrayList<VoucherAmountPO> amountList) {
        return false;
    }

    @Override
    public boolean deleteOneVoucherAllAmount(String voucherId) {
        return false;
    }

    @Override
    public boolean deleteOneAmount(String amountId) {
        return false;
    }

    @Override
    public boolean deleteSeveralVoucherAllAmount(ArrayList<String> voucherIdList) {
        return false;
    }

    @Override
    public boolean deleteAllAmount() {
        return false;
    }

    @Override
    public boolean modifyOneAmount(String amountId, VoucherAmountPO voucherAmountPO) {
        return false;
    }

    @Override
    public ArrayList<VoucherAmountPO> findOneVoucherAllAmount(String voucherId) {
        return null;
    }

    @Override
    public HashMap<String, ArrayList<VoucherAmountPO>> findSeveralVoucherAllAmount(ArrayList<String> voucherIdList) {
        return null;
    }

    @Override
    public HashMap<String, ArrayList<VoucherAmountPO>> findAllVoucherAllAmount() {
        return null;
    }

    @Override
    public boolean addOneSubjectBalance(String subjectId, double number) {
        return false;
    }

    @Override
    public boolean deleteOneSubjectBalance(String subjectId) {
        return false;
    }

    @Override
    public boolean modifyOneSubjectBalance(String subjectId, double number) {
        return false;
    }

    @Override
    public double findOneSubjectBalance(String subjectId) {
        return 0;
    }

    @Override
    public boolean addOneTemplate(VoucherTemplatePO voucherTemplatePO) {
        return false;
    }

    @Override
    public boolean addOneTemplateAmounts(String templateId, ArrayList<VoucherTemplateAmountPO> templateAmountPOArrayList) {
        return false;
    }

    @Override
    public boolean deleteOneTemplate(String templateId) {
        return false;
    }

    @Override
    public boolean deleteOneTemplateAmounts(String templateId) {
        return false;
    }

    @Override
    public boolean modifyOneTemplate(String templateId, VoucherTemplatePO voucherTemplatePO) {
        return false;
    }

    @Override
    public boolean addOneTemplateAmount(VoucherTemplateAmountPO templateAmountPO) {
        return false;
    }

    @Override
    public boolean modifyTemplateAmount(VoucherTemplateAmountPO templateAmountPO) {
        return false;
    }

    @Override
    public VoucherTemplatePO getOneTemplate(String templateId) {
        return null;
    }

    @Override
    public ArrayList<VoucherTemplatePO> getAllTemplate() {
        return null;
    }

    @Override
    public boolean deleteTemplateAmount(String amountId) {
        return false;
    }

    @Override
    public VoucherTemplateAmountPO getOneTemplateAmount(String amountId) {
        return null;
    }

    @Override
    public ArrayList<VoucherTemplateAmountPO> getOneTemplateAllAmount(String templateId) {
        return null;
    }
}
