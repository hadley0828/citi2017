package presentation.viewController;

/**
 * Created by YZ on 2017/9/10.
 */
public class StaticFactory {
    static String raw_material;

    public static void setRaw_material(String a){
        raw_material=a;
    }
    public static String getRaw_material(){
        return raw_material;
    }

    static String date;
    public static void setDate(String date1){
        date=date1;
    }
    public static String getDate(){
        return date;
    }


}
