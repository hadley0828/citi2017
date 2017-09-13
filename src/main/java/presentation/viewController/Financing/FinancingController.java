package presentation.viewController.Financing;

import businesslogic.SupplyChainImpl;
import businesslogicservice.SupplyChainService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Created by YZ on 2017/9/9.
 */
public class FinancingController {
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
//        getDebtChoice.getItems().addAll(service.AcountReceivable("001",""));
        getDebtChoice.getItems().addAll("1","2","3");
        getDebtChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if ((Integer)newValue==0){
                    getDebtMortgage.setDisable(false);
                }
            }
        });

//        getDebtNum.setText((service.getNetReceivables("001",getDebtChoice.getSelectionModel().getSelectedItem().toString(),"")+""));
        getDebtNum.setText("12345");

        getDebtMortgage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                advice.setText((service.ReceivableFinacing(Double.parseDouble(getDebtMortgage.getText()),"",""))+"");
                advice.setText("12345");
            }
        });

        setSecond();
    }

    public void setSecond(){
        stockMortgage.setDisable(true);
        advice2.setDisable(true);
        stockNum.setDisable(true);
//        stockChoice.getItems().addAll(service.InventoryTypes("001",""));
        stockChoice.getItems().addAll("1","2","#");
        stockChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                stockMortgage.setDisable(false);
            }
        });
//        stockNum.setText(service.getNetInventory("001","","")+"");
        stockNum.setText("1234567");
        stockMortgage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                advice2.setText("hhh");
            }
        });

    }
}
