package po;

import java.sql.Date;

/**
 * Created by loohaze on 2017/9/12 下午7:45
 */
public class CreditItemPO {

    private String type;

    private String itemID;

    private String companyName;

    private Date debitDate;

    private Date ddl;

    private double creditNum;

    private String discountPolicy;

    private Date discountDate;

    private String comment;

    public CreditItemPO() {
    }

    public CreditItemPO(String type, String itemID, String companyName, Date debitDate, Date ddl, double creditNum, String discountPolicy, Date discountDate, String comment) {
        this.type = type;
        this.itemID = itemID;
        this.companyName = companyName;
        this.debitDate = debitDate;
        this.ddl = ddl;
        this.creditNum = creditNum;
        this.discountPolicy = discountPolicy;
        this.discountDate = discountDate;
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getDebitDate() {
        return debitDate;
    }

    public void setDebitDate(Date debitDate) {
        this.debitDate = debitDate;
    }

    public Date getDdl() {
        return ddl;
    }

    public void setDdl(Date ddl) {
        this.ddl = ddl;
    }

    public double getCreditNum() {
        return creditNum;
    }

    public void setCreditNum(double creditNum) {
        this.creditNum = creditNum;
    }

    public String getDiscountPolicy() {
        return discountPolicy;
    }

    public void setDiscountPolicy(String discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public Date getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(Date discountDate) {
        this.discountDate = discountDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
