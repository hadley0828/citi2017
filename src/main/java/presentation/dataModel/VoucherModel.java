package presentation.dataModel;

import javafx.beans.property.SimpleStringProperty;

public class VoucherModel {
    private final SimpleStringProperty abstracts;
    private final SimpleStringProperty subject;
    private final SimpleStringProperty debit;
    private final SimpleStringProperty credit;

    public VoucherModel(String abstracts, String subject, String debit, String credit) {
        this.abstracts = new SimpleStringProperty(abstracts);
        this.subject = new SimpleStringProperty(subject);
        this.debit = new SimpleStringProperty(debit);
        this.credit = new SimpleStringProperty(credit);
    }

    public SimpleStringProperty abstractsProperty() {
        return abstracts;
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public SimpleStringProperty debitProperty() {
        return debit;
    }

    public SimpleStringProperty creditProperty() {
        return credit;
    }

    public void setAbstracts(String ab) {
        abstracts.set(ab);
    }

    public void setSubject(String su) {
        subject.set(su);
    }

    public void setDebit(String de) {
        debit.set(de);
    }

    public void setCredit(String cr) {
        credit.set(cr);
    }

    public String getAbstracts() {
        return abstracts.get();
    }

    public String getSubject() {
        return subject.get();
    }

    public String getDebit() {
        if(debit.get().isEmpty())
            return "0";
        return debit.get();
    }

    public String getCredit() {
        if (credit.get().isEmpty())
            return "0";
        return credit.get();
    }
}
