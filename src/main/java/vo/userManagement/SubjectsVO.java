package vo.userManagement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by loohaze on 2017/9/11 下午9:24
 */
public class SubjectsVO {

    private String subjectsID;

    private String subjectsName;

    private String direction;

    private String type;


    private StringProperty QC=new SimpleStringProperty("0");

    public String getQC() {
        return QC.get();
    }

    public StringProperty QCProperty() {
        return QC;
    }

    public void setQC(String QC) {
        this.QC.set(QC);
    }

    public String getDebit() {
        return debit.get();
    }

    public StringProperty debitProperty() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit.set(debit);
    }

    public String getCredit() {
        return credit.get();
    }

    public StringProperty creditProperty() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit.set(credit);
    }

    public String getNC() {
        return NC.get();
    }

    public StringProperty NCProperty() {
        return NC;
    }

    public void setNC(String NC) {
        this.NC.set(NC);
    }

    private  StringProperty debit=new SimpleStringProperty("0");

    private StringProperty credit=new SimpleStringProperty("0");

    private  StringProperty NC=new SimpleStringProperty("0");


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
