package vo.accountBook;

/**
 * Created by zhangzy on 2017/9/15 下午2:29
 */
public class SubjectIdAndNameVo {

    String id;
    String name;

    public SubjectIdAndNameVo(){
        super();
    }

    public SubjectIdAndNameVo(String id,String name){
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "SubjectIdAndNameVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
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
