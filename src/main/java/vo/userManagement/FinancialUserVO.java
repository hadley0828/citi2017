package vo.userManagement;

/**
 * Created by loohaze on 2017/9/11 下午9:39
 */
public class FinancialUserVO {

    private String id;

    private String name; //名称

    private String address; //地址

    private String legalPerson; //法定代表人

    private String financialKey; //金融许可证号

    private String legalPersonQualification; //法人资格信贷证

    public FinancialUserVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
