package po;

/**
 * Created by 费慧通 on 2017/9/11.
 *
 * 行业财务指标
 */
public class IndexPo {
    String industry;    //行业
    double excellent_value;     //优秀值
    double fine_value;      //良好值
    double average_value;   //平均值
    double low_value;   //较低值
    double bad_value;   //较差值
    String index_name;

    public IndexPo() {
    }

    public IndexPo(String industry, double excellent_value, double fine_value, double average_value, double low_value, double bad_value, String index_name) {
        this.industry = industry;
        this.excellent_value = excellent_value;
        this.fine_value = fine_value;
        this.average_value = average_value;
        this.low_value = low_value;
        this.bad_value = bad_value;
        this.index_name = index_name;
    }

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name;
    }

    public String getIndustry() {
        return industry;
    }

    public double getExcellent_value() {
        return excellent_value;
    }

    public double getFine_value() {
        return fine_value;
    }

    public double getAverage_value() {
        return average_value;
    }

    public double getLow_value() {
        return low_value;
    }

    public double getBad_value() {
        return bad_value;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setExcellent_value(double excellent_value) {
        this.excellent_value = excellent_value;
    }

    public void setFine_value(double fine_value) {
        this.fine_value = fine_value;
    }

    public void setAverage_value(double average_value) {
        this.average_value = average_value;
    }

    public void setLow_value(double low_value) {
        this.low_value = low_value;
    }

    public void setBad_value(double bad_value) {
        this.bad_value = bad_value;
    }
}
