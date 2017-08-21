package util;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/8/21 下午4:31
 */
public class DateConvert {

    /**
     * 把会计期间转换为年月
     * 会计期间-> 2017年第8期
     * 年月-> 2017-08
     * @param accountingPeriod
     * @return
     */
    public static String periodToMonth(String accountingPeriod){
        String str=accountingPeriod.substring(0,accountingPeriod.length()-1);

        String[] yearAndMonth=str.split("年第");
        String year=yearAndMonth[0];
        String month=yearAndMonth[1];

        if(month.length()==1){
            month="0"+month;
        }

        return year+"-"+month;
    }

    /**
     * 把年月转换为会计期间
     * 年月-> 2017-08
     * 会计期间-> 2017年第8期
     * @param yearMonth
     * @return
     */
    public static String monthToPeriod(String yearMonth){
        String[] yearAndMonth=yearMonth.split("-");
        String year=yearAndMonth[0];
        String month=yearAndMonth[1];

        if(month.charAt(0)=='0'){
            month=month.substring(1);
        }

        return year+"年第"+month+"期";
    }

    public static void main(String[] args) {
//        System.out.println(monthToPeriod("2017-12"));
        System.out.println(periodToMonth("2017年第8期"));
    }

}
