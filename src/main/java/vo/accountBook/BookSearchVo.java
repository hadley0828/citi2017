package vo.accountBook;

/**
 * 账簿功能按条件筛选的搜索类
 * Created by zhangzy on 2017/9/8 下午8:27
 */
public class BookSearchVo {

    private String startPeriod;     //开始的会计期间
    private String endPeriod;       //结束的会计期间
    private String startSubjectId;  //起始科目
    private String endSubjectId;    //结束科目
    private int lowLevel;           //小的科目级别
    private int highLevel;          //大的科目级别

    public BookSearchVo(){
        super();
    }

    @Override
    public String toString() {
        return "BookSearchVo{" +
                "startPeriod='" + startPeriod + '\'' +
                ", endPeriod='" + endPeriod + '\'' +
                ", startSubjectId='" + startSubjectId + '\'' +
                ", endSubjectId='" + endSubjectId + '\'' +
                ", lowLevel=" + lowLevel +
                ", highLevel=" + highLevel +
                '}';
    }

    public String getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }

    public String getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }

    public String getStartSubjectId() {
        return startSubjectId;
    }

    public void setStartSubjectId(String startSubjectId) {
        this.startSubjectId = startSubjectId;
    }

    public String getEndSubjectId() {
        return endSubjectId;
    }

    public void setEndSubjectId(String endSubjectId) {
        this.endSubjectId = endSubjectId;
    }

    public int getLowLevel() {
        return lowLevel;
    }

    public void setLowLevel(int lowLevel) {
        this.lowLevel = lowLevel;
    }

    public int getHighLevel() {
        return highLevel;
    }

    public void setHighLevel(int highLevel) {
        this.highLevel = highLevel;
    }



}
