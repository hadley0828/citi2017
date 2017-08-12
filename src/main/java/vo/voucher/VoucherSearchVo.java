package vo.voucher;

/**
 * Created by zhangzy on 2017/8/13 上午12:56
 */
public class VoucherSearchVo {

    private String startPeriod; //会计期间的开始时期
    private String endPeriod;   //会计期间的结束时期
    private String character;   //凭证字
    private String maker;       //制单人
    private String abstracts;   //摘要
    private String subjectId;   //科目
    private double lowPrice;    //金额的下限
    private double highPrice;   //金额的上限
    private int lowVoucherNumber;   //凭证号的下限
    private int highVoucherNumber;  //凭证号的上限
    private int sortOrder;      //排序方式 0代表凭证号排序 1代表凭证日期排序


    public VoucherSearchVo(){
        super();
    }

    @Override
    public String toString() {
        return "VoucherSearchVo{" +
                "startPeriod='" + startPeriod + '\'' +
                ", endPeriod='" + endPeriod + '\'' +
                ", character='" + character + '\'' +
                ", maker='" + maker + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", lowPrice=" + lowPrice +
                ", highPrice=" + highPrice +
                ", lowVoucherNumber=" + lowVoucherNumber +
                ", highVoucherNumber=" + highVoucherNumber +
                ", sortOrder=" + sortOrder +
                '}';
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
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

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public int getLowVoucherNumber() {
        return lowVoucherNumber;
    }

    public void setLowVoucherNumber(int lowVoucherNumber) {
        this.lowVoucherNumber = lowVoucherNumber;
    }

    public int getHighVoucherNumber() {
        return highVoucherNumber;
    }

    public void setHighVoucherNumber(int highVoucherNumber) {
        this.highVoucherNumber = highVoucherNumber;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }


}
