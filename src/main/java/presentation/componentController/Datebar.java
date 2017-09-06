package presentation.componentController;

import businesslogic.BalanceSheetImpl;
import businesslogicservice.BalanceSheetService;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import presentation.viewController.BalanceSheetController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by YZ on 2017/8/10.
 */
public class Datebar extends HBox{
    @FXML
    private Label year;
    @FXML
    private Label month;
    @FXML
    private ImageView last_month;
    @FXML
    private ImageView later_month;

    private IntegerProperty yearProperty=new SimpleIntegerProperty(1);
    private IntegerProperty monthProperty= new SimpleIntegerProperty(1);
    private StringProperty date=new SimpleStringProperty();

    public Datebar(){
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../../component/Datebar/Datebar.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BalanceSheetService balanceSheetService=new BalanceSheetImpl();

    private int voucher_num=balanceSheetService.getVoucherNumber();

    Calendar calendar=Calendar.getInstance();
    int this_year=calendar.get(Calendar.YEAR);
    int this_month=calendar.get(Calendar.MONTH)+1;

    @FXML
    public void initialize(){
        if(voucher_num==0){
            setDate(this_year,this_month);
        }else{
//            System.out.print(balanceSheetService.getLatestTime());
            setDate(Integer.parseInt(balanceSheetService.getLatestTime().substring(0,4)),Integer.parseInt(balanceSheetService.getLatestTime().substring(5)));
        }
    }
    public IntegerProperty yearProperty(){
        return yearProperty;
    }
    public Integer getYear(){
        return yearProperty.getValue();
    }
    public IntegerProperty monthProperty(){
        return monthProperty;
    }
    public Integer getMonth(){
        return monthProperty.getValue();
    }
    public void setDate(Integer y, Integer m){
        yearProperty.setValue(y);
        year.setText(y+"");
        monthProperty.setValue(m);
        month.setText(m+"");
    }
    public String getDate(){
//        System.out.print(getYear());
        return getYear()+"-"+getMonth();
    }

    public void setDateBar(){
        if (voucher_num!=0){
            String theEarly=balanceSheetService.getEarliestTime();
            String theLate=balanceSheetService.getLatestTime();


        }
    }

    public String dateFormat="yyyy-MM";
    public SimpleDateFormat format=new SimpleDateFormat(dateFormat);
    public ArrayList days(String date1,String date2){
        ArrayList list=new ArrayList();
        list.add(date1);

        return list;

    }
}
