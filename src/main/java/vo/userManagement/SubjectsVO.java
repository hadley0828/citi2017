package vo.userManagement;

/**
 * Created by loohaze on 2017/9/11 下午9:24
 */
public class SubjectsVO {

    private String subjectsID;

    private String subjectsName;

    private String direction;

    private String type;

    public SubjectsVO(String subjectsID, String subjectsName, String direction, String type) {
        this.subjectsID = subjectsID;
        this.subjectsName = subjectsName;
        this.direction = direction;
        this.type = type;
    }

    public SubjectsVO() {
    }

    public String getSubjectsID() {
        return subjectsID;
    }

    public void setSubjectsID(String subjectsID) {
        this.subjectsID = subjectsID;
    }

    public String getSubjectsName() {
        return subjectsName;
    }

    public void setSubjectsName(String subjectsName) {
        this.subjectsName = subjectsName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
