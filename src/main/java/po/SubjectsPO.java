package po;

/**
 * Created by loohaze on 2017/8/7.
 *
 * id --> 会计科目编号
 * name --> 会计科目名称
 * balances --> 余额
 */
public class SubjectsPO {

    private String id;

    private String name;

    private double balances;


    public SubjectsPO(){

    }

    public SubjectsPO(String id, String name, double balances) {
        this.id = id;
        this.name = name;
        this.balances = balances;
    }

    public double getBalances() {
        return balances;
    }

    public void setBalances(double balances) {
        this.balances = balances;
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
}
