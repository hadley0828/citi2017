package presentation.viewController.performanceAppraisal;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by YZ on 2017/9/12.
 */
public class ProfitAbilityVO {
    String enterprise;
    Double property_pay;
    Double market_purify;

    public ProfitAbilityVO(String enterprise,Double property_pay,Double market_purify){
        this.enterprise=enterprise;
        this.property_pay=property_pay;
        this.market_purify=market_purify;
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

    public Double getProperty_pay(){return property_pay;}
    public void setProperty_pay(Double p){
        this.property_pay=p;
    }
    public DoubleProperty getPropertyProperty(){
        return new SimpleDoubleProperty(property_pay);
    }

    public Double getMarket_purify(){return market_purify;}
    public void setMarket_purify(Double p){
        this.market_purify=p;
    }
    public DoubleProperty getMarketProperty(){
        return new SimpleDoubleProperty(market_purify);
    }
}
