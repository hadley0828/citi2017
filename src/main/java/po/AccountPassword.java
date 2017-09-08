package po;

/**
 * Created by loohaze on 2017/9/8 上午7:57
 */
public class AccountPassword {

    private String companyID;

    private String password;

    public AccountPassword() {
    }

    public AccountPassword(String companyID, String password) {
        this.companyID = companyID;
        this.password = password;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
