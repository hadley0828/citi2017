package dataservice;

import po.VoucherAmountPO;
import po.VoucherPO;
import po.VoucherTemplateAmountPO;
import po.VoucherTemplatePO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhangzy on 2017/8/7 下午10:49
 * 处理和凭证相关功能的数据层接口
 */
public interface VoucherDataService {

    /**
     * 新添加一条凭证信息
     * @param voucherPO 一条凭证信息
     * @return
     */
    public boolean addVoucher(VoucherPO voucherPO);

    /**
     * 根据凭证id删除一条凭证信息
     * @param voucherId 凭证id
     * @return
     */
    public boolean deleteOneVoucher(String voucherId);

    /**
     * 删除全部的凭证信息
     * @return
     */
    public boolean deleteAllVoucher();

    /**
     * 根据凭证id和凭证信息修改一条凭证信息
     * ps:如果用户修改凭证id的话 需要判断修改之后的凭证id是否已经存在 来决定是否可以改变还是删除原有的新增一个新的凭证信息
     * @param voucherId 修改之前的凭证id
     * @param voucherPO 修改之后的凭证信息
     * @return
     */
    public boolean modifyOneVoucher(String voucherId,VoucherPO voucherPO);

    /**
     * 根据凭证id获得对应的凭证信息 需要考虑该凭证id不存在的情况
     * @param voucherId 凭证id
     * @return 对应的凭证信息
     */
    public VoucherPO findOneVoucher(String voucherId);

    /**
     * 根据一些凭证id获取这些id对应的凭证信息
     * @param idList 凭证id的list
     * @return
     */
    public ArrayList<VoucherPO> findSeveralVoucher(ArrayList<String> idList);

    /**
     * 获得全部的凭证信息
     * @return
     */
    public ArrayList<VoucherPO> findAllVoucher();

    /**
     * 根据凭证id和对应的金额添加对应的几条金额信息
     * @param voucherId 凭证id
     * @param amountList 金额的list
     * @return
     */
    public boolean addOneVoucherAllAmount(String voucherId, ArrayList<VoucherAmountPO> amountList);

    /**
     * 删除一个凭证id对应的全部金额信息
     * @param voucherId 凭证id
     * @return
     */
    public boolean deleteOneVoucherAllAmount(String voucherId);

    /**
     * 根据金额id删除一条金额信息
     * @param amountId  金额id
     * @return
     */
    public boolean deleteOneAmount(String amountId);

    /**
     * 根据凭证id的列表删除这些凭证对应的全部金额信息
     * @param voucherIdList
     * @return
     */
    public boolean deleteSeveralVoucherAllAmount(ArrayList<String> voucherIdList);

    /**
     * 删除全部的金额信息
     * @return
     */
    public boolean deleteAllAmount();

    /**
     * 根据金额id和对应的金额信息修改一条金额信息
     * @param amountId 金额id
     * @param voucherAmountPO 对应的金额信息
     * @return
     */
    public boolean modifyOneAmount(String amountId,VoucherAmountPO voucherAmountPO);

    /**
     * 根据凭证id获得对应的全部金额信息
     * @param voucherId 凭证编号
     * @return
     */
    public ArrayList<VoucherAmountPO> findOneVoucherAllAmount(String voucherId);

    /**
     * 根据一些凭证id或者这些id对应的全部金额信息
     * @param voucherIdList 凭证编号的列表
     * @return
     */
    public HashMap<String,ArrayList<VoucherAmountPO>> findSeveralVoucherAllAmount(ArrayList<String> voucherIdList);

    /**
     * 获取全部凭证id对应的金额信息
     * @return
     */
    public ArrayList<VoucherAmountPO> findAllVoucherAllAmount();

    /**
     * 新增一个会计科目的余额信息
     * @param subjectId 会计科目的编号
     * @param number 初始的余额
     * @return
     */
    public boolean addOneSubjectBalance(String subjectId,double number);

    /**
     * 删除一个会计科目的余额信息
     * @param subjectId 会计科目的编号
     * @return
     */
    public boolean deleteOneSubjectBalance(String subjectId);

    /**
     * 修改一个会计科目的余额信息
     * @param subjectId 会计科目的编号
     * @param number 修改后的余额数量
     * @return
     */
    public boolean modifyOneSubjectBalance(String subjectId,double number);


    /**
     * 根据会计科目的id获取对应的余额
     * @param subjectId
     * @return
     */
    public double findOneSubjectBalance(String subjectId);

    /**
     * 添加一个新的凭证模板
     * @param voucherTemplatePO
     * @return
     */
    public boolean addOneTemplate(VoucherTemplatePO voucherTemplatePO);

    /**
     * 根据模板编号和模板金额的列表添加这个模板的全部金额
     * @param templateId
     * @param templateAmountPOArrayList
     * @return
     */
    public boolean addOneTemplateAmounts(String templateId, ArrayList<VoucherTemplateAmountPO> templateAmountPOArrayList);

    /**
     * 根据模板编号删除一个模板
     * @param templateId 模板编号
     * @return
     */
    public boolean deleteOneTemplate(String templateId);

    /**
     * 根据模板编号删除一个模板的全部金额
     * @param templateId
     * @return
     */
    public boolean deleteOneTemplateAmounts(String templateId);

    /**
     * 根据模板编号和模板信息改变一个模板的基本信息
     * @param templateId
     * @param voucherTemplatePO
     * @return
     */
    public boolean modifyOneTemplate(String templateId,VoucherTemplatePO voucherTemplatePO);

    /**
     * 新添加一个模板金额
     * @param templateAmountPO
     * @return
     */
    public boolean addOneTemplateAmount(VoucherTemplateAmountPO templateAmountPO);

    /**
     * 修改一个模板金额
     * @param templateAmountPO
     * @return
     */
    public boolean modifyTemplateAmount(VoucherTemplateAmountPO templateAmountPO);

    /**
     * 根据模板编号获得对应的模板信息
     * @param templateId
     * @return
     */
    public VoucherTemplatePO getOneTemplate(String templateId);

    /**
     * 获得全部的模板信息
     * @return
     */
    public ArrayList<VoucherTemplatePO> getAllTemplate();
    /**
     * 删除一个模板金额
     * @param amountId 模板金额编号
     * @return
     */
    public boolean deleteTemplateAmount(String amountId);

    /**
     * 获得一个模板金额
     * @param amountId
     * @return
     */
    public VoucherTemplateAmountPO getOneTemplateAmount(String amountId);

    /**
     * 获得一个模板的全部模板金额
     * @param templateId
     * @return
     */
    public ArrayList<VoucherTemplateAmountPO> getOneTemplateAllAmount(String templateId);


    /**
     * 初始化科目余额表
     */
    public void intialSubjectsBalance();
}
