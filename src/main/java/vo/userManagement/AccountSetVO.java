package vo.userManagement;

/**
 * Created by loohaze on 2017/9/11 下午6:50
 */
public class AccountSetVO {

    private String account_id;

    private String company_id;

    private String company_name;

    private String location;

    private String industry;

    private String chainPlace;

    private String date;

    private String creditCode;

    private String contact;

    public AccountSetVO(String account_id, String company_id, String company_name, String location, String industry, String chainPlace, String date, String creditCode, String contact) {
        this.account_id = account_id;
        this.company_id = company_id;
        this.company_name = company_name;
        this.location = location;
        this.industry = industry;
        this.chainPlace = chainPlace;
        this.date = date;
        this.creditCode = creditCode;
        this.contact = contact;
    }

    public AccountSetVO() {
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getChainPlace() {
        return chainPlace;
    }

    public void setChainPlace(String chainPlace) {
        this.chainPlace = chainPlace;
    }
}

