package presentation.dataModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class SubjectBalanceModel {
    private final SimpleStringProperty id;
    private final SimpleStringProperty subject;
    private final SimpleDoubleProperty beginningDebit;
    private final SimpleDoubleProperty beginningCredit;
    private final SimpleDoubleProperty occurredDebit;
    private final SimpleDoubleProperty occurredCredit;
    private final SimpleDoubleProperty endingDebit;
    private final SimpleDoubleProperty endingCredit;

    public SubjectBalanceModel(String id, String subject, double beginningDebit, double beginningCredit, double occurredDebit, double occurredCredit, double endingDebit, double endingCredit) {
        this.id = new SimpleStringProperty(id);
        this.subject = new SimpleStringProperty(subject);
        this.beginningDebit = new SimpleDoubleProperty(beginningDebit);
        this.beginningCredit = new SimpleDoubleProperty(beginningCredit);
        this.occurredDebit = new SimpleDoubleProperty(occurredDebit);
        this.occurredCredit = new SimpleDoubleProperty(occurredCredit);
        this.endingDebit = new SimpleDoubleProperty(endingDebit);
        this.endingCredit = new SimpleDoubleProperty(endingCredit);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public SimpleDoubleProperty beginningDebitProperty() {
        return beginningDebit;
    }

    public SimpleDoubleProperty beginningCreditProperty() {
        return beginningCredit;
    }

    public SimpleDoubleProperty occurredDebitProperty() {
        return occurredDebit;
    }

    public SimpleDoubleProperty occurredCreditProperty() {
        return occurredCredit;
    }

    public SimpleDoubleProperty endingDebitProperty() {
        return endingDebit;
    }

    public SimpleDoubleProperty endingCreditProperty() {
        return endingCredit;
    }
}
