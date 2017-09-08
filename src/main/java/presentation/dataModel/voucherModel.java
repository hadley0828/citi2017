package presentation.dataModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class voucherModel {

    private final SimpleStringProperty abstracts;
    private final SimpleStringProperty subject;
    private final SimpleDoubleProperty debit;
    private final SimpleDoubleProperty credit;

    public voucherModel(String abstracts, String subject, double debit, double credit) {
        this.abstracts = new SimpleStringProperty(abstracts);
        this.subject = new SimpleStringProperty(subject);
        this.debit = new SimpleDoubleProperty(credit);
        this.credit = new SimpleDoubleProperty(debit);
    }

    public SimpleStringProperty abstractsProperty() {
        return abstracts;
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public SimpleDoubleProperty debitProperty() {
        return debit;
    }

    public SimpleDoubleProperty creditProperty() {
        return credit;
    }
}
