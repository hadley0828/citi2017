package presentation.viewController.performanceAppraisal;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by YZ on 2017/9/12.
 */
public class OperationVO {
    String enterprise;
    Double property_rotate;
    Double stock_rotate;
    Double ontime;
    Double back;

    public OperationVO(String enterprise,Double property_rotate,Double stock_rotate,Double ontime,Double back){
        this.enterprise=enterprise;
        this.property_rotate=property_rotate;
        this.stock_rotate=stock_rotate;
        this.ontime=ontime;
        this.back=back;
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

    public Double getProperty_rotate(){return property_rotate;}
    public void setProperty_rotate(Double p){
        this.property_rotate=p;
    }
    public DoubleProperty getPropertyProperty(){
        return new SimpleDoubleProperty(property_rotate);
    }

    public Double getStock_rotate(){return stock_rotate;}
    public void setStock_rotate(Double s){
        this.stock_rotate=s;
    }
    public DoubleProperty getStockProperty(){
        return new SimpleDoubleProperty(stock_rotate);
    }
    public Double getOntime(){return ontime;}
    public void setOntime(Double o){
        this.ontime=o;
    }
    public DoubleProperty getOntimeProperty(){
        return new SimpleDoubleProperty(ontime);
    }

    public Double getBack(){return back;}
    public void setBack(Double o){
        this.back=o;
    }
    public DoubleProperty getBackProperty(){
        return new SimpleDoubleProperty(back);
    }

}
