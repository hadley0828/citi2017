package presentation.viewController;

import vo.userManagement.FinancialUserVO;
import vo.userManagement.UserVO;
import vo.voucher.VoucherSearchVo;

import java.time.LocalDate;

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

    static String producer_raw_material;

    public static void setProducer_Raw_material(String a){
        producer_raw_material=a;
    }
    public static String getProducer_Raw_material(){
        return producer_raw_material;
    }

    static String producer_raw_date;
    public static void setproducer_raw_date(String date1){
        producer_raw_date=date1;
    }
    public static String getproducer_raw_date(){
        return producer_raw_date;
    }


    static String producer_product;

    public static void setProducer_product(String a){
        producer_product=a;
    }
    public static String getProducer_product(){
        return producer_product;
    }

    static String producer_product_date;
    public static void setproducer_product_date(String date1){
        producer_product_date=date1;
    }
    public static String getproducer_product_date(){
        return producer_product_date;
    }


    static String distributor_product;

    public static void setdistributor_product(String a){
        distributor_product=a;
    }
    public static String getdistributor_product(){
        return distributor_product;
    }

    static String distributor_product_date;
    public static void setdistributor_product_date(String date1){
        distributor_product_date=date1;
    }
    public static String getdistributor_product_date(){
        return distributor_product_date;
    }

    static UserVO userVO;
    public static void setUserVO(UserVO uservo){
        userVO=uservo;
    }
    public static UserVO getUserVO(){
        return userVO;
    }

    static FinancialUserVO FUserVO;
    public static  void setFinancialUserVO(FinancialUserVO vo){
        FUserVO=vo;
    }
    public static FinancialUserVO getFUserVO(){
        return FUserVO;
    }


    public static String getMonth(){
        LocalDate now = LocalDate.now();
        return now.toString().substring(0,7);
    }




}
