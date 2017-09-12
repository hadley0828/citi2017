package vo.userManagement;

/**
 * Created by loohaze on 2017/9/11 下午7:13
 */
public class SubjectsInitialVO {

    private String subejcts_id;

    private String subjects_name;

    private String direction;

    private double peroidRemain;

    private double debitSum; //借方

    private double creditSum; //贷方

    private double yearRemain;

    private String type;

    public SubjectsInitialVO() {
    }

    public String getSubejcts_id() {
        return subejcts_id;
    }

    public void setSubejcts_id(String subejcts_id) {
        this.subejcts_id = subejcts_id;
    }

    public String getSubjects_name() {
        return subjects_name;
    }

    public void setSubjects_name(String subjects_name) {
        this.subjects_name = subjects_name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public double getPeroidRemain() {
        return peroidRemain;
    }

    public void setPeroidRemain(double peroidRemain) {
        this.peroidRemain = peroidRemain;
    }

    public double getDebitSum() {
        return debitSum;
    }

    public void setDebitSum(double debitSum) {
        this.debitSum = debitSum;
    }

    public double getCreditSum() {
        return creditSum;
    }

    public void setCreditSum(double creditSum) {
        this.creditSum = creditSum;
    }

    public double getYearRemain() {
        return yearRemain;
    }

    public void setYearRemain(double yearRemain) {
        this.yearRemain = yearRemain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
