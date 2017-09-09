package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    /**
     * 获得两个月份之间的全部月份
     * @param startMonth
     * @param endMonth
     * @return
     */
    public static HashSet<String> getBetweenMonth(String startMonth,String endMonth){
        HashSet<String> resultSet=new HashSet<>();

        DateFormat aa = DateFormat.getDateInstance();
        Date date1 = null; // 开始日期
        Date date2 = null; // 结束日期
        try {
            date1 = aa.parse(startMonth+"-01");
            date2 = aa.parse(endMonth+"-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        //定义集合存放月份
        List list = new ArrayList();
        //添加第一个月，即开始时间
        list.add(startMonth);
        c1.setTime(date1);
        c2.setTime(date2);
        while (c1.compareTo(c2) < 0) {
            c1.add(Calendar.MONTH, 1);// 开始日期加一个月直到等于结束日期为止
            Date ss = c1.getTime();
            String str = aa.format(ss);
            str = str.substring(0, str.lastIndexOf("-"));

            if(str.length()!=7){
                str=str.split("-")[0]+"-0"+str.split("-")[1];
            }
            list.add(str);
        }

        for (int i = 0; i < list.size(); i++) {
            resultSet.add((String) list.get(i));
        }

        return resultSet;
    }


    public static String getCurrentMonth(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }


    public static void main(String[] args) {
//        System.out.println(monthToPeriod("2017-12"));
//        System.out.println(periodToMonth("2017年第8期"));
//        System.out.println(getBetweenMonth("2017-04","2017-09").size());

//        String date="2010-04-10";
//        System.out.println(date.substring(0,date.lastIndexOf("-")));

        System.out.println(getCurrentMonth());

    }
}
