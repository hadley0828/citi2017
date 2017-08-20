package util;

/**
 * Created by zhangzy on 2017/8/15 下午11:37
 * 获得当前的月数
 */
import java.util.Date;
import java.text.SimpleDateFormat;

public class NowString {

    public static String getNowMonth(){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result=String.valueOf(df.format(new Date())).substring(0,7);
        return result;
    }


    public static void main(String[] args) {
        System.out.println(getNowMonth());
    }
}