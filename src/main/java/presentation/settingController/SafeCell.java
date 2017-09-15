package presentation.settingController;

import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import vo.Inventory.SafeInventoryVo;
import vo.userManagement.SubjectsVO;

import static presentation.settingController.QCController.isSet;

/**
 * Created by Chris on 2017/9/14.
 */
public class SafeCell extends TableCell<SafeInventoryVo,Boolean> {
    final TextField cellText = new TextField();


    SafeCell(){
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
    }

    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {

        super.updateItem(t, empty);
        if(!empty) {
                setGraphic(cellText);
        }
    }
}
