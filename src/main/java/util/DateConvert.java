package util;



import java.lang.*;

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
    public static java.lang.String periodToMonth(java.lang.String accountingPeriod){
        java.lang.String str=accountingPeriod.substring(0,accountingPeriod.length()-1);

        java.lang.String[] yearAndMonth=str.split("年第");
        java.lang.String year=yearAndMonth[0];
        java.lang.String month=yearAndMonth[1];

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
    public static java.lang.String monthToPeriod(java.lang.String yearMonth){
        java.lang.String[] yearAndMonth=yearMonth.split("-");
        java.lang.String year=yearAndMonth[0];
        java.lang.String month=yearAndMonth[1];

        if(month.charAt(0)=='0'){
            month=month.substring(1);
        }

        return year+"年第"+month+"期";
    }


    public static ArrayList<String> getBetweenMonthList(java.lang.String startMonth, java.lang.String endMonth){
        ArrayList<String> resultList=new ArrayList<>();


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
            java.lang.String str = aa.format(ss);
            str = str.substring(0, str.lastIndexOf("-"));

            if(str.length()!=7){
                str=str.split("-")[0]+"-0"+str.split("-")[1];
            }
            list.add(str);
        }

        for (int i = 0; i < list.size(); i++) {
            resultList.add((String) list.get(i));
        }

        return resultList;
    }


    /**
     * 获得两个月份之间的全部月份
     * @param startMonth
     * @param endMonth
     * @return
     */
    public static HashSet<String> getBetweenMonth(java.lang.String startMonth, java.lang.String endMonth){
        HashSet<String> resultSet=new HashSet<>();

        ArrayList<String> betweenMonthList=getBetweenMonthList(startMonth,endMonth);


        for (int i = 0; i < betweenMonthList.size(); i++) {
            resultSet.add((String) betweenMonthList.get(i));
        }

        return resultSet;
    }



    public static java.lang.String getCurrentMonth(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    /**
     * 获得一个月的第一天日期
     * @param month 2017-08
     * @return
     */
    public static java.lang.String getMonthFirstDate(String month){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        java.lang.String oneDate=month+"-01";
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(oneDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        java.lang.String first = format.format(c.getTime());

        return first;
    }

    /**
     * 获得一个月的最后一天日期
     * @param month
     * @return
     */
    public static java.lang.String getMonthLastDate(String month){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        java.lang.String oneDate=month+"-01";

        Calendar ca = Calendar.getInstance();
        try {
            ca.setTime(format.parse(oneDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        java.lang.String last = format.format(ca.getTime());

        return last;
    }

    /**
     * 添加一个月
     * @param month
     * @return
     */
    public static String addOneMonth(String month){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dstr=month+"-01";

        Date date= null;
        try {
            date = sdf.parse(dstr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);

        calendar.add(calendar.MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动


        return sdf.format(calendar.getTime()).substring(0,7);
    }

    /**
     * 获得一个日期的列表中的最早的月数
     * @param dateList
     * @return
     */
    public static String getFirstMonth(ArrayList<String> dateList){
        Collections.sort(dateList);

        return dateList.get(0).substring(0,7);
    }


    public static String addNMonth(String month,int n){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dstr=month+"-01";

        Date date= null;
        try {
            date = sdf.parse(dstr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);

        calendar.add(calendar.MONTH, n);//把日期往后增加一个月.整数往后推,负数往前移动


        return sdf.format(calendar.getTime()).substring(0,7);
    }

    /**
     * 减去一个月
     * @param month
     * @return
     */
    public static String minusOneMonth(String month){

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dstr=month+"-01";

        Date date= null;
        try {
            date = sdf.parse(dstr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);

        calendar.add(calendar.MONTH, -1);//把日期往后增加一个月.整数往后推,负数往前移动


        return sdf.format(calendar.getTime()).substring(0,7);
    }

    /**
     * 减去n个月
     * @param month
     * @param n
     * @return
     */
    public static String minusNMonth(String month,int n){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String dstr=month+"-01";

        Date date= null;
        try {
            date = sdf.parse(dstr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(date);

        calendar.add(calendar.MONTH, -n);//把日期往后增加一个月.整数往后推,负数往前移动


        return sdf.format(calendar.getTime()).substring(0,7);
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(monthToPeriod("2017-12"));
//        System.out.println(periodToMonth("2017年第8期"));
//        System.out.println(getBetweenMonth("2017-04","2017-04").size());
//
//        ArrayList<String> monthList=getBetweenMonthList("2016-04","2017-04");
//        for(int count=0;count<monthList.size();count++){
//            System.out.println(monthList.get(count));
//        }

//        String date="2010-04-10";
//        System.out.println(date.substring(0,date.lastIndexOf("-")));

//        System.out.println(getCurrentMonth());
//        System.out.println(getMonthFirstDate("2010-07"));
//        System.out.println(getMonthLastDate("2012-02"));

//        System.out.println(addOneMonth("2016-10"));
//        System.out.println(addNMonth("2016-10",8));

//        ArrayList<String> dateList=new ArrayList<>();
//        dateList.add("2016-10-01");
//        dateList.add("2017-07-03");
//        dateList.add("2016-12-05");
//        dateList.add("2015-10-08");
//        dateList.add("2013-06-07");
//        System.out.println(getFirstMonth(dateList));

        System.out.println(minusOneMonth("2017-07"));
        System.out.println(minusNMonth("2018-05",10));
    }
}
