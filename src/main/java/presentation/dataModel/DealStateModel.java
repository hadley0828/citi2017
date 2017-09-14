package presentation.dataModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class DealStateModel {
    private final SimpleStringProperty name;
    private final SimpleStringProperty debitTime;
    private final SimpleStringProperty creditTerm;
    private final SimpleStringProperty discount;
    private final SimpleStringProperty money;
    private final SimpleStringProperty discountTerm;
    private final SimpleStringProperty remark;

    public DealStateModel(String name, String debitTime, String creditTerm, String discount, String money, String discountTerm, String remark) {
        this.name = new SimpleStringProperty(name);
        this.debitTime = new SimpleStringProperty(debitTime);
        this.creditTerm = new SimpleStringProperty(creditTerm);
        this.discount = new SimpleStringProperty(discount);
        this.money = new SimpleStringProperty(money);
        this.discountTerm = new SimpleStringProperty(discountTerm);
        this.remark = new SimpleStringProperty(remark);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty debitTimeProperty() {
        return debitTime;
    }

    public SimpleStringProperty creditTermProperty() {
        return creditTerm;
    }

    public SimpleStringProperty discountProperty() {
        return discount;
    }

    public SimpleStringProperty discountTermProperty() {
        return discountTerm;
    }

    public SimpleStringProperty remarkProperty() {
        return remark;
    }

    public SimpleStringProperty moneyProperty() {
        return money;
    }

//    public void setName(String na) {
//        this.name
//}
}
