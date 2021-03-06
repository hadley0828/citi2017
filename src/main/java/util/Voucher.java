package util;

import po.VoucherAmountPO;
import vo.voucher.VoucherAmountVo;

/**
 * 用作excel操作的凭证类
 * Created by zhangzy on 2017/9/3 下午12:44
 */
public class Voucher {
    private String date;    //日期
    private String voucherId;   //凭证号
    private String abstracts;   //摘要
    private String subjectId;   //科目编码
    private double debitCurrency;   //借方本币
    private double creditCurrency;  //贷方本币

    @Override
    public String toString() {
        return "Voucher{" +
                "date='" + date + '\'' +
                ", voucherId='" + voucherId + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", debitCurrency=" + debitCurrency +
                ", creditCurrency=" + creditCurrency +
                '}';
    }

    public Voucher(){

    }

    public Voucher(VoucherAmountPO po,String date){
        super();
        try{
            this.date=date;
            this.voucherId=po.getV_id();
            this.abstracts=po.getDigest();
            this.subjectId=po.getSubject();
            this.debitCurrency=po.getDebitAmount();
            this.creditCurrency=po.getCreditAmount();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Voucher(VoucherAmountVo vo,String date){
        super();
        try{
            this.date=date;
            this.voucherId=vo.getVoucherId();
            this.abstracts=vo.getAbstracts();
            this.subjectId=vo.getSubject();
            this.debitCurrency=vo.getDebitAmount();
            this.creditCurrency=vo.getCreditAmount();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public Voucher(String date,String voucherId,String abstracts,String subjectId,double debitCurrency,double creditCurrency){
        super();
        this.date=date;
        this.voucherId=voucherId;
        this.abstracts=abstracts;
        this.subjectId=subjectId;
        this.debitCurrency=debitCurrency;
        this.creditCurrency=creditCurrency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public double getDebitCurrency() {
        return debitCurrency;
    }

    public void setDebitCurrency(double debitCurrency) {
        this.debitCurrency = debitCurrency;
    }

    public double getCreditCurrency() {
        return creditCurrency;
    }

    public void setCreditCurrency(double creditCurrency) {
        this.creditCurrency = creditCurrency;
    }




}
