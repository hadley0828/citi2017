package presentation.componentController;

import businesslogic.BalanceSheetImpl;
import businesslogicservice.BalanceSheetService;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import presentation.viewController.BalanceSheetController;

import java.awt.event.MouseEvent;
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

//    private BalanceSheetController controller=new BalanceSheetController();

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

    private IntegerProperty yearProperty=new SimpleIntegerProperty();
    private IntegerProperty monthProperty=new SimpleIntegerProperty();
    private StringProperty date=new SimpleStringProperty();

    Calendar calendar=Calendar.getInstance();
    int this_year=calendar.get(Calendar.YEAR);
    int this_month=calendar.get(Calendar.MONTH)+1;

    String theEarly=balanceSheetService.getEarliestTime();
    String theLate=balanceSheetService.getLatestTime();

    public ArrayList<String> months=balanceSheetService.getMiddleMonth(theEarly,theLate);


    @FXML
    public void initialize(){

        if(voucher_num==0){
            setDate(this_year,this_month);
            setListener();
        }else{
//            System.out.print(balanceSheetService.getLatestTime());
            setDate(Integer.parseInt(balanceSheetService.getLatestTime().substring(0,4)),Integer.parseInt(balanceSheetService.getLatestTime().split("-")[1]));
            setListener();
        }

    }

    public void setDate(Integer y, Integer m){
        yearProperty.setValue(y);
        year.setText(y+"");
        monthProperty.setValue(m);
        month.setText(m+"");
    }
    public void changePro(){
        yearProperty.setValue(Integer.parseInt(year.getText()));
        monthProperty.setValue(Integer.parseInt(month.getText()));
    }

    public int getYear(){
        return yearProperty.getValue();
    }
    public int getMonth(){
        return monthProperty.getValue();
    }
    //    public Integer getYear(){
//        return yearProperty.getValue();
//    }
    public IntegerProperty monthProperty(){
        return monthProperty;
    }
//    public Integer getMonth(){
//        return monthProperty.getValue();
//    }

    public String getDate(){
//        System.out.println(yearProperty.get());
        return yearProperty.get()+"-"+monthProperty.get();
    }

    DropShadow shadow=new DropShadow();
    public void setListener(){
//        int curr_index=months.indexOf(getDate());

        last_month.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if (months.indexOf(getDate())>0) {
//                    System.out.print(getDate());
                    last_month.setEffect(shadow);
                }
            }
        });
        last_month.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                last_month.setEffect(null);
            }
        });

        later_month.setOnMouseEntered(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
//                System.out.print(months.size());
                if(months.indexOf(getDate())<(months.size()-1)) {
                    later_month.setEffect(shadow);
                }
            }
        });
        later_month.setOnMouseExited(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
//                last_month.setStyle("-fx-background-color: rgb(248,133,92)");
                later_month.setEffect(null);
            }
        });
//        last_month.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
//            @Override
//            public void handle(javafx.scene.input.MouseEvent event) {
//                if(months.indexOf(getDate())>0) {
//                    year.setText(months.get(months.indexOf(getDate()) - 1).substring(0,4));
//                    month.setText(months.get(months.indexOf(getDate()) - 1).split("-")[1]);
//                    changePro();
//                }
//            }
//        });
//        later_month.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
//            @Override
//            public void handle(javafx.scene.input.MouseEvent event) {
//                if(months.indexOf(getDate())<months.size()-1) {
//                    year.setText(months.get(months.indexOf(getDate()) + 1).substring(0,4));
//                    month.setText(months.get(months.indexOf(getDate()) + 1).split("-")[1]);
//                    changePro();
//                }
//            }
//        });

        if (months.indexOf(getDate())<months.size()-1){
            later_month.setStyle("");
        }
        if (months.indexOf(getDate())>0){
            last_month.setStyle("-fx-stroke: rgb(255,135,98)");
        }

    }
    public ArrayList<String> getMidMonths(){
        return months;
    }
    public ImageView getLast(){
        return last_month;
    }
    public ImageView getLater(){
        return later_month;
    }
    public Label getYL(){
        return year;
    }
    public Label getML(){
        return month;
    }





}
