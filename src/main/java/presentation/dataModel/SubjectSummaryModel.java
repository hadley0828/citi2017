package presentation.dataModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class SubjectSummaryModel {
    private final SimpleStringProperty id;
    private final SimpleStringProperty subject;
    private final SimpleDoubleProperty debitSum;
    private final SimpleDoubleProperty creditSum;

    public SubjectSummaryModel(String id, String subject, double debitSum, double creditSum) {
        this.id = new SimpleStringProperty(id);
        this.subject = new SimpleStringProperty(subject);
        this.debitSum = new SimpleDoubleProperty(debitSum);
        this.creditSum = new SimpleDoubleProperty(creditSum);
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public SimpleDoubleProperty debitSumProperty() {
        return debitSum;
    }

    public SimpleDoubleProperty creditSumProperty() {
        return creditSum;
    }

}
