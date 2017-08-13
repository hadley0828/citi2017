package po;

import java.sql.Date;

/**
 * Created by loohaze on 2017/8/7.
 *
 * companyName --> 公司名称
 * location --> 公司位置
 * industry --> 行业
 * startDate --> 开始时间
 */
public class AccountSetPO {

    private String companyName;

    private String location;

    private String industry;

    private Date startDate;

    public AccountSetPO(String companyName, String location, String industry, Date startDate) {
        this.companyName = companyName;
        this.location = location;
        this.industry = industry;
        this.startDate = startDate;
    }

    public AccountSetPO() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
