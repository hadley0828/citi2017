package util;

/**
 * Created by zhangzy on 2017/8/16 下午10:23
 */
public class Student {

    private String name;
    private int age;
    private String grade;

    public Student() {
    }

    public Student(String name, int age, String grade) {
        super();
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", grade=" + grade
                + "]";
    }
}