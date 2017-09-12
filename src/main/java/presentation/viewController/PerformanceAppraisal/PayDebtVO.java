package presentation.viewController.PerformanceAppraisal;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by YZ on 2017/9/12.
 */
public class PayDebtVO {
    String enterprise;
    Double balance;

    public PayDebtVO(String enterprise,Double balance){
        this.enterprise=enterprise;
        this.balance=balance;
    }
    public String getEnterprise(){
        return enterprise;
    }
    public void setEnterprise(String e){
        this.enterprise = e;
    }
    public StringProperty getEnterpriseProperty(){
        return new SimpleStringProperty(enterprise);
    }

    public Double getBalance(){
        return balance;
    }
    public void setBalance(Double balance){
        this.balance=balance;
    }
    public DoubleProperty getBalanceProperty(){
        return new SimpleDoubleProperty(balance);
    }
}
