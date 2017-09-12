package po;

/**
 * Created by loohaze on 2017/9/12 上午9:33
 */
public class UserCompanyPO {

    private String userID;

    private String accountID;

    private String type;

    private String companyID;

    public UserCompanyPO(String userID, String accountID, String type, String companyID) {
        this.userID = userID;
        this.accountID = accountID;
        this.type = type;
        this.companyID = companyID;
    }

    public UserCompanyPO() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }
}
