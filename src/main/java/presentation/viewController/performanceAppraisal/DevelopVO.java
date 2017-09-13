package presentation.viewController.performanceAppraisal;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by YZ on 2017/9/12.
 */
public class DevelopVO {
    String enterprise;
    Double market_increase;
    Double profit_increase;

    public DevelopVO(String enterprise,Double market_increase,Double profit_increase){
        this.enterprise=enterprise;
        this.market_increase=market_increase;
        this.market_increase=market_increase;
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

    public Double getmarket_increase(){return market_increase;}
    public void setmarket_increase(Double p){
        this.market_increase=p;
    }
    public DoubleProperty getMarketProperty(){
        return new SimpleDoubleProperty(market_increase);
    }

    public Double getprofit_increase(){return profit_increase;}
    public void setprofit_increase(Double p){
        this.profit_increase=p;
    }
    public DoubleProperty getProfitProperty(){
        return new SimpleDoubleProperty(profit_increase);
    }

}
