package presentation.settingController;

import businesslogic.SettingImpl;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import presentation.screenController.ControlledScreen;
import presentation.screenController.ScreensController;
import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsVO;

import java.util.ArrayList;

/**
 * Created by Chris on 2017/9/9.
 */
public class QCController implements ControlledScreen{
    public static Boolean isSet=false;

    private ObservableList<SafeInventoryVo> list;



    @FXML
    private Button addButton;
    @FXML
    private Tab Balance;

    @FXML
    private  Tab debit;

    @FXML
    private Tab cleanBalance;

    @FXML
    private Tab income;

    @FXML
    private Tab fee;

    @FXML
    TableView<SubjectsVO> BalanceTable;

    @FXML
    TableColumn<SubjectsVO,String> code1;

    @FXML
    TableColumn<SubjectsVO,String> name1;

    @FXML
    TableColumn<SubjectsVO,String> direction1;

    @FXML
    TableColumn<SubjectsVO,Boolean> debit1;

    @FXML
    TableColumn<SubjectsVO,Boolean> credit1;

    @FXML
    TableColumn<SubjectsVO,Boolean> QC1;
    @FXML
    TableColumn<SubjectsVO,String> NC1;

    @FXML
    TableView<SubjectsVO> debitTable;

    @FXML
    TableColumn<SubjectsVO,String> code2;

    @FXML
    TableColumn<SubjectsVO,String> name2;

    @FXML
    TableColumn<SubjectsVO,String> direction2;

    @FXML
    TableColumn<SubjectsVO,Boolean> QC2;
    @FXML
    TableColumn<SubjectsVO,Boolean> debit2;
    @FXML
    TableColumn<SubjectsVO,Boolean> credit2;
    @FXML
    TableColumn<SubjectsVO,String> NC2;

    @FXML
    TableView<SubjectsVO> cleanBalanceTable;

    @FXML
    TableColumn<SubjectsVO,String> code3;

    @FXML
    TableColumn<SubjectsVO,String> name3;

    @FXML
    TableColumn<SubjectsVO,String> direction3;

    @FXML
    TableColumn<SubjectsVO,Boolean> QC3;
    @FXML
    TableColumn<SubjectsVO,String> NC3;
    @FXML
    TableColumn<SubjectsVO,Boolean> debit3;
    @FXML
    TableColumn<SubjectsVO,Boolean> credit3;


    @FXML
    TableView<SubjectsVO> incomeTable;

    @FXML
    TableColumn<SubjectsVO,String> code4;

    @FXML
    TableColumn<SubjectsVO,String> name4;

    @FXML
    TableColumn<SubjectsVO,String> direction4;

    @FXML
    TableColumn<SubjectsVO,String> NC4;
    @FXML
    TableColumn<SubjectsVO,Boolean> debit4;
    @FXML
    TableColumn<SubjectsVO,Boolean> credit4;
    @FXML
    TableColumn<SubjectsVO,Boolean> QC4;

    @FXML
    TableView<SubjectsVO> FeeTable;

    @FXML
    TableColumn<SubjectsVO,String> code5;

    @FXML
    TableColumn<SubjectsVO,String> name5;

    @FXML
    TableColumn<SubjectsVO,String> direction5;

    @FXML
    TableColumn<SubjectsVO,Boolean> QC5;

    @FXML
    TableColumn<SubjectsVO,Boolean> debit5;
    @FXML
    TableColumn<SubjectsVO,Boolean> credit5;
    @FXML
    TableColumn<SubjectsVO,String> NC5;

    @FXML
    TableView<SafeInventoryVo> safeTable;
    @FXML
    TableColumn<SafeInventoryVo,Boolean> safeName;
    @FXML
    TableColumn<SafeInventoryVo,Boolean> safeVariety;
    @FXML
    TableColumn<SafeInventoryVo,Boolean>safeSum;


    @FXML
    public void initialize(){
//        try {
            SettingImpl impl = new SettingImpl();
            ArrayList<SubjectsVO> list = impl.getAllSubjects();
            ArrayList<SubjectsVO> list1=new ArrayList<>();
            ArrayList<SubjectsVO> list2=new ArrayList<>();
            ArrayList<SubjectsVO> list3=new ArrayList<>();
            ArrayList<SubjectsVO> list4=new ArrayList<>();
            ArrayList<SubjectsVO> list5=new ArrayList<>();

            for(int i=0;i<list.size();i++){
                String type=list.get(i).getType();
                if(type.equals("资产")){
                    list1.add(list.get(i));

                }else if(type.equals("负债")){
                    list2.add(list.get(i));

                }else if(type.equals("净资产")){
                    list3.add(list.get(i));

                }else if(type.equals("收入")){
                    list4.add(list.get(i));

                }else if(type.equals("费用")){
                    list5.add(list.get(i));

                }
            }

            ObservableList<SubjectsVO> data1 = FXCollections.observableArrayList(list1);
            ObservableList<SubjectsVO> data2 = FXCollections.observableArrayList(list2);
            ObservableList<SubjectsVO> data3 = FXCollections.observableArrayList(list3);
            ObservableList<SubjectsVO> data4 = FXCollections.observableArrayList(list4);
            ObservableList<SubjectsVO> data5 = FXCollections.observableArrayList(list5);

            BalanceTable.setItems(data1);
            code1.setCellValueFactory(new PropertyValueFactory("subjectsID"));
            name1.setCellValueFactory(new PropertyValueFactory("subjectsName"));
            direction1.setCellValueFactory(new PropertyValueFactory("direction"));


            QC1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        QC1.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new ButtonCell();
            }
        });


        debit1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        debit1.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new debitCell();
            }
        });

        credit1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        credit1.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new creditCell();
            }
        });

        NC1.setCellValueFactory(cellData->cellData.getValue().NCProperty());






            debitTable.setItems(data2);
            code2.setCellValueFactory(new PropertyValueFactory("subjectsID"));
            name2.setCellValueFactory(new PropertyValueFactory("subjectsName"));
            direction2.setCellValueFactory(new PropertyValueFactory("direction"));

        debit2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        debit2.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new debitCell();
            }
        });


        credit2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        credit2.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new creditCell();
            }
        });

        QC2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        QC2.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new ButtonCell();
            }
        });
        NC2.setCellValueFactory(cellData->cellData.getValue().NCProperty());



            cleanBalanceTable.setItems(data3);
            code3.setCellValueFactory(new PropertyValueFactory("subjectsID"));
            name3.setCellValueFactory(new PropertyValueFactory("subjectsName"));
            direction3.setCellValueFactory(new PropertyValueFactory("direction"));
        debit3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        debit3.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new debitCell();
            }
        });

        credit3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        credit3.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new creditCell();
            }
        });

        QC3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        QC3.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new ButtonCell();
            }
        });
        NC3.setCellValueFactory(cellData->cellData.getValue().NCProperty());



            incomeTable.setItems(data4);
            code4.setCellValueFactory(new PropertyValueFactory("subjectsID"));
            name4.setCellValueFactory(new PropertyValueFactory("subjectsName"));
            direction4.setCellValueFactory(new PropertyValueFactory("direction"));

        debit4.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        debit4.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new debitCell();
            }
        });

        credit4.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        credit4.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new creditCell();
            }
        });

        QC4.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        QC4.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new ButtonCell();
            }
        });
        NC4.setCellValueFactory(cellData->cellData.getValue().NCProperty());



            FeeTable.setItems(data5);
            code5.setCellValueFactory(new PropertyValueFactory("subjectsID"));
            name5.setCellValueFactory(new PropertyValueFactory("subjectsName"));
            direction5.setCellValueFactory(new PropertyValueFactory("direction"));

        debit5.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        debit5.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new debitCell();
            }
        });

        credit5.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        credit5.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new creditCell();
            }
        });

        QC5.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectsVO, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SubjectsVO, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        QC5.setCellFactory(new Callback<TableColumn<SubjectsVO, Boolean>, TableCell<SubjectsVO, Boolean>>() {
            @Override
            public TableCell<SubjectsVO, Boolean> call(TableColumn<SubjectsVO, Boolean> p) {
                return new ButtonCell();
            }
        });
        NC5.setCellValueFactory(cellData->cellData.getValue().NCProperty());




        ArrayList<SafeInventoryVo> safelist=new ArrayList<>();

        ObservableList<SafeInventoryVo> safedata = FXCollections.observableArrayList(safelist);
        this.list=safedata;
        safeTable.setItems(this.list);

        safeName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SafeInventoryVo, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SafeInventoryVo, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        safeName.setCellFactory(new Callback<TableColumn<SafeInventoryVo, Boolean>, TableCell<SafeInventoryVo, Boolean>>() {
            @Override
            public TableCell<SafeInventoryVo, Boolean> call(TableColumn<SafeInventoryVo, Boolean> p) {
                return new ChoiceCell();
            }
        });

        safeVariety.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SafeInventoryVo, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SafeInventoryVo, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        safeVariety.setCellFactory(new Callback<TableColumn<SafeInventoryVo, Boolean>, TableCell<SafeInventoryVo, Boolean>>() {
            @Override
            public TableCell<SafeInventoryVo, Boolean> call(TableColumn<SafeInventoryVo, Boolean> p) {
                return new SafeCell();
            }
        });

        safeSum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SafeInventoryVo, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SafeInventoryVo, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        safeSum.setCellFactory(new Callback<TableColumn<SafeInventoryVo, Boolean>, TableCell<SafeInventoryVo, Boolean>>() {
            @Override
            public TableCell<SafeInventoryVo, Boolean> call(TableColumn<SafeInventoryVo, Boolean> p) {
                return new SafeCell();
            }
        });










//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }

    }

    public void addClicked(){
        SafeInventoryVo vo=new SafeInventoryVo("","",0);

        this.list.add(vo);



        safeName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SafeInventoryVo, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SafeInventoryVo, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        safeName.setCellFactory(new Callback<TableColumn<SafeInventoryVo, Boolean>, TableCell<SafeInventoryVo, Boolean>>() {
            @Override
            public TableCell<SafeInventoryVo, Boolean> call(TableColumn<SafeInventoryVo, Boolean> p) {
                return new ChoiceCell();
            }
        });

        safeVariety.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SafeInventoryVo, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SafeInventoryVo, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        safeVariety.setCellFactory(new Callback<TableColumn<SafeInventoryVo, Boolean>, TableCell<SafeInventoryVo, Boolean>>() {
            @Override
            public TableCell<SafeInventoryVo, Boolean> call(TableColumn<SafeInventoryVo, Boolean> p) {
                return new SafeCell();
            }
        });

        safeSum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SafeInventoryVo, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SafeInventoryVo, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue()!=null);
            }
        });
//
        safeSum.setCellFactory(new Callback<TableColumn<SafeInventoryVo, Boolean>, TableCell<SafeInventoryVo, Boolean>>() {
            @Override
            public TableCell<SafeInventoryVo, Boolean> call(TableColumn<SafeInventoryVo, Boolean> p) {
                return new SafeCell();
            }
        });


    }


    @Override
    public void setScreenParent(ScreensController screenPage) {

    }
}
