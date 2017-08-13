package po;

/**
 * Created by loohaze on 2017/8/7.
 *
 * v_id --> 凭证id
 * path --> 路径
 */
public class ReceiptsPO {

    private String v_id;

    private String path;

    public ReceiptsPO(String v_id, String path) {
        this.v_id = v_id;
        this.path = path;
    }

    public ReceiptsPO() {
    }

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
