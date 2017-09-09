package presentation.dataModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class DetailBillModel {
    private final SimpleStringProperty date;
    private final SimpleStringProperty id;
    private final SimpleStringProperty subject;
    private final SimpleStringProperty abstracts;
    private final SimpleDoubleProperty debit;
    private final SimpleDoubleProperty credit;
    private final SimpleStringProperty direction;
    private final SimpleDoubleProperty balance;

    public DetailBillModel(String date, String id, String subject, String abstracts, double debit, double credit, String direction, double balance) {
        this.date = new SimpleStringProperty(date);
        this.id = new SimpleStringProperty(id);
        this.subject = new SimpleStringProperty(subject);
        this.abstracts = new SimpleStringProperty(abstracts);
        this.debit = new SimpleDoubleProperty(credit);
        this.credit = new SimpleDoubleProperty(debit);
        this.direction = new SimpleStringProperty(direction);
        this.balance = new SimpleDoubleProperty(balance);
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public SimpleStringProperty abstractsProperty() {
        return abstracts;
    }

    public SimpleDoubleProperty debitProperty() {
        return debit;
    }

    public SimpleDoubleProperty creditProperty() {
        return credit;
    }

    public SimpleStringProperty directionProperty() {
        return direction;
    }

    public SimpleDoubleProperty balanceProperty() {
        return balance;
    }

}
