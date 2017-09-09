package dataTest;

import businesslogic.VoucherBlImpl;
import businesslogicservice.VoucherBlService;
import org.junit.Before;
import org.junit.Test;
import vo.voucher.*;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/8/15 下午3:29
 */
public class VoucherBlServiceImplTest {

    private VoucherBlService voucherBlService;

    @Before
    public void setup() throws Exception{
        voucherBlService=new VoucherBlImpl();
    }

    @Test
    public void testSaveOneVoucher(){
        VoucherVo vo=new VoucherVo();
        vo.setVoucherId("记-12");
        vo.setDate("2017-04-20");
        vo.setAddedReceipts(true);
        vo.setVoucherMaker("hadley");
        vo.setRemark("this is a voucher three");

        VoucherAmountVo voucherAmountVo=new VoucherAmountVo();
        voucherAmountVo.setVoucherId("记-10");
        voucherAmountVo.setAmountId("5");
        voucherAmountVo.setAbstracts("利息收入");
        voucherAmountVo.setSubject("1002");
        voucherAmountVo.setDebitAmount(20000);
        voucherAmountVo.setCreditAmount(0);
        ArrayList<VoucherAmountVo> amountVoArrayList=new ArrayList<>();
        amountVoArrayList.add(voucherAmountVo);

        vo.setAmountList(amountVoArrayList);

        voucherBlService.saveOneVoucher(vo,"001");


    }

    @Test
    public void testGetVoucherTotal(){
        ArrayList<VoucherAmountVo> amountVoList=new ArrayList<>();

        VoucherAmountVo vo1=new VoucherAmountVo();
        vo1.setVoucherId("记-1");
        vo1.setAmountId("001");
        vo1.setAbstracts("发工资");
        vo1.setSubject("1001");
        vo1.setDebitAmount(12345.2);
        vo1.setCreditAmount(0);

        VoucherAmountVo vo2=new VoucherAmountVo();
        vo2.setVoucherId("记-1");
        vo2.setAmountId("002");
        vo2.setAbstracts("发工资");
        vo2.setSubject("1002");
        vo2.setDebitAmount(0);
        vo2.setCreditAmount(12345.2);

        amountVoList.add(vo1);
        amountVoList.add(vo2);

        System.out.println(voucherBlService.getVoucherTotal(amountVoList,"001").toString());
    }

    @Test
    public void testGetOneSubjectBalance(){
        String subjectId="1002";
        System.out.println(voucherBlService.getOneSubjectBalance(subjectId,"001"));

    }

    @Test
    public void testGetNewSubjectBalance(){
        double beforeNumber=123;
        double changeNumber=-50;
        System.out.println(voucherBlService.getNewSubjectBalance(beforeNumber,changeNumber,"001"));
    }

    /**
     * 14s左右
     */
    @Test
    public void testGetOneTemplate(){
        System.out.println(voucherBlService.getOneTemplate("1","001").toString());
    }

    @Test
    public void testAddOneTemplate(){
        VoucherTemplateVo oneVo=new VoucherTemplateVo();
        oneVo.setTemplateId("2");
        oneVo.setTemplateName("测试");
        oneVo.setCategory("测试大类");

        ArrayList<VoucherTemplateAmountVo> amountVoList=new ArrayList<>();
        VoucherTemplateAmountVo amountVo1=new VoucherTemplateAmountVo();
        amountVo1.setTemplateId("2");
        amountVo1.setTemplateAmountId("100");
        amountVo1.setAbstracts("测试");
        amountVo1.setSubject("1001");
        amountVo1.setDebitAmount(0);
        amountVo1.setCreditAmount(0);

        amountVoList.add(amountVo1);

        VoucherTemplateAmountVo amountVo2=new VoucherTemplateAmountVo();
        amountVo2.setTemplateId("2");
        amountVo2.setTemplateAmountId("200");
        amountVo2.setAbstracts("测试");
        amountVo2.setSubject("1002");
        amountVo2.setDebitAmount(0);
        amountVo2.setCreditAmount(0);

        amountVoList.add(amountVo2);

        oneVo.setAmountList(amountVoList);


        System.out.println(voucherBlService.addOneTemplate(oneVo,"001"));

    }

    @Test
    public void testGetCurrentPeriodAllVoucher(){
        ArrayList<VoucherVo> resultList=voucherBlService.getCurrentPeriodAllVoucher("001");

        for(int count=0;count<resultList.size();count++){
            System.out.println(resultList.get(count).toString());
        }
    }

    @Test
    public void testGetOneVoucher(){
        System.out.println(voucherBlService.getOneVoucher("记-10","001").toString());
    }

    @Test
    public void testGetSearchedVoucher(){
        VoucherSearchVo searchVo=new VoucherSearchVo();
        searchVo.setStartPeriod("2017年第4期");
        searchVo.setEndPeriod("2017年第9期");
        searchVo.setCharacter("全部");
        searchVo.setMaker("全部");

        searchVo.setLowPrice(-1.0);
        searchVo.setHighPrice(-1.0);
        searchVo.setLowVoucherNumber(-1);
        searchVo.setHighVoucherNumber(-1);

        searchVo.setSortOrder(1);

        ArrayList<VoucherVo> resultList=voucherBlService.getSearchedVoucher(searchVo,"001");

        for(int count=0;count<resultList.size();count++){
            System.out.println(resultList.get(count).toString());
        }
    }

    @Test
    public void testDeleteSelectedVoucher(){
        ArrayList<String> voucherIdList=new ArrayList<>();
        voucherIdList.add("记-20");
        voucherIdList.add("记-21");

        voucherBlService.deleteSelectedVoucher(voucherIdList,"001");
    }

    @Test
    public void testExportToExcel(){

    }
}
