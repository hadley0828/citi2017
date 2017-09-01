package vo.voucher;

import po.VoucherAmountPO;
import po.VoucherPO;
import util.NumberToCN;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/8/13 上午12:19
 */
public class AmountTotalVo {

    private String chineseTotal;//总金额的中文大学数字
    private double debitAmount; //借方金额
    private double creditAmount;//贷方金额

    public AmountTotalVo(){
        super();
    }

    public AmountTotalVo(ArrayList<VoucherAmountPO> amountPOArrayList){
        if(amountPOArrayList.size()!=0){
            double debitNumber=0.0;
            double creditNumber=0.0;

            for(int count=0;count<amountPOArrayList.size();count++){
                VoucherAmountPO onePo=amountPOArrayList.get(count);
                debitNumber=debitNumber+onePo.getDebitAmount();
                creditNumber=creditNumber+onePo.getCreditAmount();

            }

            this.chineseTotal=NumberToCN.number2CNMontrayUnit(creditNumber);
            this.debitAmount=debitNumber;
            this.creditAmount=creditNumber;

        }

    }

    @Override
    public String toString() {
        return "AmountTotalVo{" +
                "chineseTotal='" + chineseTotal + '\'' +
                ", debitAmount=" + debitAmount +
                ", creditAmount=" + creditAmount +
                '}';
    }

    public String getChineseTotal() {
        return chineseTotal;
    }

    public void setChineseTotal(String chineseTotal) {
        this.chineseTotal = chineseTotal;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }




}
