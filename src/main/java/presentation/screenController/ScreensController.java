package presentation.screenController;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.HashMap;

/**
 * Molloh
 * 2017/9/5
 * custom StackPane，add animated effect to it
 */
public class ScreensController extends StackPane {

    private HashMap<String, Node> screens = new HashMap<>();

    private static volatile ScreensController INSTANCE = null;

    private ScreensController() {
    }

    public static ScreensController getInstance() {
        if(INSTANCE == null) {
            synchronized (ScreensController.class) {
                //when more than two threads run into the first null check same time, to avoid instanced more than one time, it needs to be checked again.
                if (INSTANCE == null) {
                    INSTANCE = new ScreensController();
                }
            }
        }

        return INSTANCE;
    }

    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = myLoader.load();
            ControlledScreen myScreenController = myLoader.getController();
            myScreenController.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setScreen(final String name) {

        if(screens.get(name) != null) { //screen loaded
            //final DoubleProperty opacity = opacityProperty();

            //Is there is more than one screen
            if(!getChildren().isEmpty()){
                //remove displayed screen
                getChildren().remove(0);
                //add new screen
                getChildren().add(0, screens.get(name));
                /*
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(opacity,1.0)),
                        new KeyFrame(new Duration(400),
                                t -> {
                                     //remove displayed screen
                                     getChildren().remove(0);
                                     //add new screen
                                     getChildren().add(0, screens.get(name));
                                     Timeline fadeIn = new Timeline(
                                             new KeyFrame(Duration.ZERO,
                                                     new KeyValue(opacity, 0.0)),
                                             new KeyFrame(new Duration(400),
                                                     new KeyValue(opacity, 1.0)));
                                     fadeIn.play();
                                 },
                                new KeyValue(opacity, 0.0)));
                fade.play();
                */
            } else {
                //no one else been displayed, then just show
                getChildren().add(screens.get(name));
                /*
                setOpacity(0.0);
                getChildren().add(screens.get(name));
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(800),
                                new KeyValue(opacity, 1.0)));
                fadeIn.play();
                */
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!\n");
            return false;
        }
    }

    public boolean unloadScreen(String name) {
        if(screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }

}
