package util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by loohaze on 2017/8/12.
 */
public class DatesUtil {

    /**
     * 处理日期 yyyy-mm
     *
     * @param date
     * @return Map<String,String>
     */
    public static Map<String,String> datesParser(String date){
        String[] datelist = date.split("-");
        Map<String,String> map = new HashMap<>();
        map.put("year",datelist[0]);
        map.put("month",datelist[1].charAt(0)=='0'? datelist[1].substring(1):datelist[1]);

        return map;
    }



    public static void main(String[] args) {
        Map<String,String> map = DatesUtil.datesParser("2018-09");
        System.out.println(map.get("year"));
        System.out.println(map.get("month"));
    }
}
