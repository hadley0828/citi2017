package presentation.viewController.supplyChainManagement.financing;

import businesslogic.SupplyChainImpl;
import businesslogicservice.SupplyChainService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import presentation.StaticFactory;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;

/**
 * Created by YZ on 2017/9/9.
 */
public class FinancingController implements ControlledScreen{
    @FXML
    private TextField getDebtNum;
    @FXML
    private TextField getDebtMortgage;
    @FXML
    private TextField advice;
    @FXML
    private TextField stockNum;
    @FXML
    private TextField stockMortgage;
    @FXML
    private TextField advice2;
    @FXML
    private ChoiceBox<String> getDebtChoice;
    @FXML
    private ChoiceBox<String> stockChoice;

    SupplyChainService service=new SupplyChainImpl();
    @FXML
    public void initialize(){
        getDebtMortgage.setDisable(true);
        advice.setDisable(true);
        getDebtNum.setDisable(true);

        getDebtChoice.getItems().addAll(service.AcountReceivable(StaticFactory.getUserVO().getCompanyID(),StaticFactory.getDay()));

//        getDebtChoice.getSelectionModel().select(0);
        getDebtChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                getDebtMortgage.setDisable(false);
//                System.out.println(newValue);
//                System.out.print((service.getNetReceivables("001",newValue,"2017-09-15")+""));
                getDebtNum.setText((service.getNetReceivables(StaticFactory.getUserVO().getCompanyID(),newValue,StaticFactory.getDay())+""));
            }
        });
//        getDebtChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                    getDebtMortgage.setDisable(false);
//                    System.out.println("online"+oldValue);
//                    System.out.print(getDebtChoice.getSelectionModel().getSelectedItem());
//                    getDebtNum.setText((service.getNetReceivables(StaticFactory.getUserVO().getCompanyID(),getDebtChoice.getSelectionModel().getSelectedItem(),StaticFactory.getMonth())+""));
//            }
//        });



        getDebtMortgage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                advice.setText((service.ReceivableFinacing(Double.parseDouble(getDebtMortgage.getText()),StaticFactory.getUserVO().getCompanyID(),StaticFactory.getDay()))+"");
            }
        });
        setSecond();
    }

    public void setSecond(){
        stockMortgage.setDisable(true);
        advice2.setDisable(true);
        stockNum.setDisable(true);
        stockChoice.getItems().addAll(service.InventoryTypes(StaticFactory.getUserVO().getCompanyID(),StaticFactory.getMonth()));
//        stockChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                stockMortgage.setDisable(false);
//                stockNum.setText(service.getNetInventory(StaticFactory.getUserVO().getCompanyID(),stockChoice.getSelectionModel().getSelectedItem(),StaticFactory.getDay())+"");
//            }
//        });
        stockChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                stockMortgage.setDisable(false);
                stockNum.setText(service.getNetInventory(StaticFactory.getUserVO().getCompanyID(),newValue,StaticFactory.getDay())+"");
            }
        });
//        stockNum.setText(service.getNetInventory(StaticFactory.getUserVO().getCompanyID(),stockChoice.getSelectionModel().getSelectedItem(),StaticFactory.getMonth())+"");
        stockMortgage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                advice2.setText((service.PledgeMovables(Double.parseDouble(stockMortgage.getText()),StaticFactory.getUserVO().getCompanyID(),StaticFactory.getDay()))+"");
            }
        });

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}
