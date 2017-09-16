package presentation.settingController;

import businesslogic.SettingImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import presentation.StaticFactory;
import presentation.warningController.RunWarning;
import vo.userManagement.SubjectsInitialVO;
import vo.userManagement.SubjectsVO;

import java.util.ArrayList;

/**
 * Created by Chris on 2017/9/14.
 */
public class ButtonCell extends TableCell<SubjectsVO,Boolean> {
    final TextField cellText = new TextField();

    ButtonCell(){
//        cellButton.setOnAction(new EventHandler<ActionEvent>(){
//            @Override
//            public void handle(ActionEvent t) {
////                	viewcontrol.OrderClicked();
//                try{
//                    OrderVO ordervo = getTableView().getItems().get( getIndex() );
//                    String hotelid=ordervo.getHotel_id().get();
//                    HotelController hotelController=new HotelController();
//                    viewcontrol.hotelLevel.setText(hotelController.getNmaeById(hotelid));
//                    viewcontrol.businessCircle.setText(hotelController.viewHotel(hotelController.getNmaeById(hotelid)).getHotelArea().get());
//                    viewcontrol.checkinTime.setText(ordervo.getExecuteTime().get());
//                    viewcontrol.roomType.setText(ordervo.getRoomType().get());
//                    viewcontrol.roomNum.setText(Integer.toString(ordervo.getNumber().get()));
//                    viewcontrol.peopleNum.setText(Integer.toString(ordervo.getPeople().get()));
//                    viewcontrol.price.setText(Integer.toString(ordervo.getOrderPrice().get()));
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });


        cellText.setText("0");
        cellText.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SubjectsVO vo=getTableView().getItems().get(getIndex());
                vo.setQC(cellText.getText());
                if(vo.getCredit().isEmpty()){
                    vo.setCredit("0");

                }else if(vo.getDebit().isEmpty()){
                    vo.setDebit("0");
                }else if(vo.getQC().isEmpty()){
                    vo.setQC("0");
                }
                else {
                    if (vo.getDirection().equals("借")) {
                        int NC = Integer.parseInt(vo.getQC()) + Integer.parseInt(vo.getCredit()) - Integer.parseInt(vo.getDebit());
                        vo.setNC(String.valueOf(NC));


                    } else if (vo.getDirection().equals("贷")) {
                        int NC = Integer.parseInt(vo.getQC()) - Integer.parseInt(vo.getCredit()) + Integer.parseInt(vo.getDebit());
                        vo.setNC(String.valueOf(NC));

                    }
                }

                ArrayList<SubjectsInitialVO> list=new ArrayList<>();
                SubjectsInitialVO SIvo=new SubjectsInitialVO();
                char id=vo.getSubjectsID().charAt(0);
                String type="";
                if(id=='1'){
                    type="资产";

                }else if(id=='2'){
                    type="负债";

                }else if(id=='3'){
                    type="净资产";

                }else if(id=='4'){
                    type="收入";
                }else if(id=='5'){
                    type="费用";

                }
                SIvo.setType(type);
                SIvo.setSubjects_name(vo.getSubjectsName());
                SIvo.setSubejcts_id(vo.getSubjectsID());
                SIvo.setDirection(vo.getDirection());
                SIvo.setCreditSum(Double.parseDouble(vo.getCredit()));
                SIvo.setDebitSum(Double.parseDouble(vo.getDebit()));
                SIvo.setPeroidRemain(Double.parseDouble(vo.getQC()));
                SIvo.setYearRemain(Double.parseDouble(vo.getNC()));
                list.add(SIvo);
                SettingImpl impl=new SettingImpl();
                Boolean rm=impl.setInitialSubjects(list, StaticFactory.getUserVO().getCompanyID());
                if(rm==false){
                    RunWarning rw=new RunWarning();
                    rw.SetWarning("上传失败！");
                    rw.start(new Stage());
                }



            }
        });
//        cellText.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                SubjectsVO vo=getTableView().getItems().get(getIndex());
//                vo.setQC(cellText.getText());
//                if(vo.getCredit().isEmpty()){
//                    vo.setCredit("0");
//
//                }else if(vo.getDebit().isEmpty()){
//                    vo.setDebit("0");
//                }else if(vo.getQC().isEmpty()){
//                    vo.setQC("0");
//                }
//                else {
//                    if (vo.getDirection().equals("借")) {
//                        int NC = Integer.parseInt(vo.getQC()) + Integer.parseInt(vo.getCredit()) - Integer.parseInt(vo.getDebit());
//                        vo.setNC(String.valueOf(NC));
//
//
//                    } else if (vo.getDirection().equals("贷")) {
//                        int NC = Integer.parseInt(vo.getQC()) - Integer.parseInt(vo.getCredit()) + Integer.parseInt(vo.getDebit());
//                        vo.setNC(String.valueOf(NC));
//
//                    }
//                }
//
//                ArrayList<SubjectsInitialVO> list=new ArrayList<>();
//                SubjectsInitialVO SIvo=new SubjectsInitialVO();
//                char id=vo.getSubjectsID().charAt(0);
//                String type="";
//                if(id=='1'){
//                    type="资产";
//
//                }else if(id=='2'){
//                    type="负债";
//
//                }else if(id=='3'){
//                    type="净资产";
//
//                }else if(id=='4'){
//                    type="收入";
//                }else if(id=='5'){
//                    type="费用";
//
//                }
//                SIvo.setType(type);
//                SIvo.setSubjects_name(vo.getSubjectsName());
//                SIvo.setSubejcts_id(vo.getSubjectsID());
//                SIvo.setDirection(vo.getDirection());
//                SIvo.setCreditSum(Double.parseDouble(vo.getCredit()));
//                SIvo.setDebitSum(Double.parseDouble(vo.getDebit()));
//                SIvo.setPeroidRemain(Double.parseDouble(vo.getQC()));
//                SIvo.setYearRemain(Double.parseDouble(vo.getNC()));
//                list.add(SIvo);
//                SettingImpl impl=new SettingImpl();
//                Boolean rm=impl.setInitialSubjects(list, StaticFactory.getUserVO().getCompanyID());
//                if(rm==false){
//                    RunWarning rw=new RunWarning();
//                    rw.SetWarning("上传失败！");
//                    rw.start(new Stage());
//                }
//
//
//
//
//
//
//
//
//
//
//                //调用接口
//
//            }
//        });
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            setGraphic(cellText);
        }
    }
}
