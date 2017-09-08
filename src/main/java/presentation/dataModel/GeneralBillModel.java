package presentation.dataModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class GeneralBillModel {
    private final SimpleStringProperty id;
    private final SimpleStringProperty subject;
    private final SimpleStringProperty period;
    private final SimpleStringProperty abstracts;
    private final SimpleDoubleProperty debit;
    private final SimpleDoubleProperty credit;
    private final SimpleStringProperty direction;

    public GeneralBillModel(String id, String subject, String period, String abstracts, double debit, double credit, String direction) {
        this.id = new SimpleStringProperty(id);
        this.subject = new SimpleStringProperty(subject);
        this.period = new SimpleStringProperty(period);
        this.abstracts = new SimpleStringProperty(abstracts);
        this.debit = new SimpleDoubleProperty(credit);
        this.credit = new SimpleDoubleProperty(debit);
        this.direction = new SimpleStringProperty(direction);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public SimpleStringProperty periodProperty() {
        return period;
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

}
