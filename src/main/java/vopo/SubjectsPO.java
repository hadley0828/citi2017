package vopo;

/**
 * Created by loohaze on 2017/8/7.
 *
 * id --> 会计科目编号
 * name --> 会计科目名称
 */
public class SubjectsPO {

    private String id;

    private String name;

    public SubjectsPO(){

    }

    public SubjectsPO(String id, String name){
        this.id = id;
        this.name = name;
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
