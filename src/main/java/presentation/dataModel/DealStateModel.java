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

    public void setName(String na) {
        name.set(na);
    }

    public void setDebitTime(String de) {
        debitTime.set(de);
    }

    public void setCreditTerm(String cr) {
        creditTerm.set(cr);
    }

    public void setDiscount(String dis) {
        discount.set(dis);
    }

    public void setMoney(String mon) {
        money.set(mon);
    }

    public void setDiscountTerm(String dt) {
        discountTerm.set(dt);
    }

    public void setRemark(String re) {
        remark.set(re);
    }

    public String getName() {
        return name.get();
    }

    public String getDiscount() {
        return discount.get();
    }

    public String getDebitTime() {
        return debitTime.get();
    }

    public String getCreditTerm() {
        return creditTerm.get();
    }

    public String getMoney() {
        return money.get();
    }

    public String getDiscountTerm() {
        return discountTerm.get();
    }

    public String getRemark() {
        return remark.get();
    }

}
