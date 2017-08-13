package data;

import dataservice.VoucherDataService;
import po.VoucherAmountPO;
import po.VoucherPO;

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
}
