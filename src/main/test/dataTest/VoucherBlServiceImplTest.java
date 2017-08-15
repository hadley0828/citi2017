package dataTest;

import businesslogic.VoucherBlImpl;
import businesslogicservice.VoucherBlService;
import org.junit.Before;
import org.junit.Test;
import vo.voucher.VoucherAmountVo;
import vo.voucher.VoucherVo;

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
        vo.setVoucherId("记-10");
        vo.setDate("2017-04-10");
        vo.setAddedReceipts(true);
        vo.setVoucherMaker("hadley");
        vo.setRemark("this is a voucher three");

        VoucherAmountVo voucherAmountVo=new VoucherAmountVo();
        voucherAmountVo.setVoucherId("记-10");
        voucherAmountVo.setAmountId("5");
        voucherAmountVo.setAbstracts("利息收入");
        voucherAmountVo.setSubject("1001");
        voucherAmountVo.setDebitAmount(20000);
        voucherAmountVo.setCreditAmount(0);
        ArrayList<VoucherAmountVo> amountVoArrayList=new ArrayList<>();
        amountVoArrayList.add(voucherAmountVo);

        vo.setAmountList(amountVoArrayList);

        voucherBlService.saveOneVoucher(vo);


    }
}
