package presentation.settingController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import vo.userManagement.SubjectsVO;

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


        cellText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                SubjectsVO vo=getTableView().getItems().get(getIndex());
                String direction=vo.getDirection();
                

            }
        });
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