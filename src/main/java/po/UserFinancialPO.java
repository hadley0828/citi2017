package po;

/**
 * Created by loohaze on 2017/9/12 上午9:35
 */
public class UserFinancialPO {

    private String userID;

    private String name;

    private String address;

    private String legalPerson; //法定代表人

    private String financialKey; //金融许可证号

    private String legalPersonQualification; //法人资格信贷证

    public UserFinancialPO() {

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getFinancialKey() {
        return financialKey;
    }

    public void setFinancialKey(String financialKey) {
        this.financialKey = financialKey;
    }

    public String getLegalPersonQualification() {
        return legalPersonQualification;
    }

    public void setLegalPersonQualification(String legalPersonQualification) {
        this.legalPersonQualification = legalPersonQualification;
    }
}
