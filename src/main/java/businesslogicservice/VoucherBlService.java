package businesslogicservice;

import po.VoucherPO;
import vo.voucher.AmountTotalVo;
import vo.voucher.VoucherSearchVo;
import vo.voucher.VoucherTemplateVo;
import vo.voucher.VoucherVo;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/8/7 下午10:40
 * 处理和凭证相关功能的逻辑层接口
 */
public interface VoucherBlService {

    //添加凭证  计算当前的总和 获得当前会计科目的余额并可以实时计算余额
    //保存并新增 保存  保存为模板(提示框询问是否保存金额)   从模板中加载 改变余额
    //保存凭证需要保证借方金额和贷方金额是相同的 而保存为模板是不需要的

    //查看凭证
    //获得当前月的全部凭证并且显示    默认是按照凭证字号排序
    //按要求筛选凭证
    //打印
    //导出凭证
    //导入凭证
    //批量删除 需要先进行选择 有这些凭证的凭证编号
    //对一条凭证进行处理 修改!!!  删除  复制  插入
    //可以新增一条会计科目

    /**
     * 添加一个新的凭证 该凭证的id不能与数据库中的重复 若重复则返回false
     * @param voucherPO
     * @return
     */
    public boolean saveOneVoucher(VoucherPO voucherPO);

    /**
     * 获得当前凭证的合计一行的数据
     * @param voucherPO
     * @return
     */
    public AmountTotalVo getVoucherTotal(VoucherPO voucherPO);

    /**
     * 获得当前会计科目的余额
     * @param subjectId
     * @return
     */
    public double getOneSubjectBalance(String subjectId);

    /**
     * 根据原来的余额和借方金额/贷方金额实时改变科目的余额的显示
     * @param beforeNumber
     * @param changeNumber
     * @return
     */
    public double getNewSubjectBalance(double beforeNumber,double changeNumber);

    /**
     * 根据模板编号获得相对应的凭证模板
     * @param templateId
     * @return
     */
    public VoucherTemplateVo getOneTemplate(String templateId);

    /**
     * 添加一个新的凭证模板   需要判断相同名称的模板是否存在
     * @param voucherTemplateVo
     * @return
     */
    public boolean addOneTemplate(VoucherTemplateVo voucherTemplateVo);

    /**
     * 获得当前的时期的全部凭证信息
     * @return
     */
    public ArrayList<VoucherVo> getCurrentPeriodAllVoucher();

    /**
     * 获得按条件筛选后的全部的凭证信息
     * @param voucherSearchVo
     * @return
     */
    public ArrayList<VoucherVo> getSearchedVoucher(VoucherSearchVo voucherSearchVo);

    /**
     * 删除所选择的全部凭证信息
     * @param voucherIdList
     * @return
     */
    public boolean deleteSelectedVoucher(ArrayList<String> voucherIdList);

    /**
     * 导出所选的凭证
     * @param voucherIdList
     * @return
     */
    public boolean exportToExcel(ArrayList<String> voucherIdList);

    /**
     * 根据文件的路径把需要的凭证导入到系统中
     * @param filePath
     * @return
     */
    public ArrayList<VoucherVo> importFromExcel(String filePath);


    //TODO 打印凭证并且对打印进行设置    对一条凭证进行处理 修改!!!  删除  复制  插入    可以新增一条会计科目

}
