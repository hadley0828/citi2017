package util;

import vo.voucher.VoucherVo;

import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangzy on 2017/9/1 上午11:51
 * 用来对一个VoucherVo的list进行排序的工具类
 */
public class SortHelper {
    /**
     * 把得到的凭证列表按照凭证号进行排序 记 收 付 转
     * @param beforeList
     * @return
     */
    public static ArrayList<VoucherVo> getVoucherNumberSort(ArrayList<VoucherVo> beforeList){
        Collections.sort(beforeList,COMPARATORONE);
        return beforeList;
    }

    /**
     * 把得到的凭证列表按照凭证日期来进行排序
     * @param beforeList
     * @return
     */
    public static ArrayList<VoucherVo> getVoucherDateSort(ArrayList<VoucherVo> beforeList){
        Collections.sort(beforeList,COMPARATORTWO);
        return beforeList;
    }

    /**
     * 凭证号的比较器
     */
    private static final Comparator<VoucherVo> COMPARATORONE=new Comparator<VoucherVo>() {

        private Collator cmp=Collator.getInstance(Locale.CHINA);

        @Override
        public int compare(VoucherVo o1, VoucherVo o2) {
            String voucherId1=o1.getVoucherId();
            String voucherId2=o2.getVoucherId();

            if(null==voucherId1){
                if(null==voucherId2){
                    return 0;
                }else{
                    return 1;
                }
            }else if(null==voucherId2){
                return -1;
            }else{
                String Character1=voucherId1.split("-")[0];
                int number1=Integer.valueOf(voucherId1.split("-")[1]);
                String Character2=voucherId2.split("-")[0];
                int number2=Integer.valueOf(voucherId2.split("-")[1]);

                if(!Character1.equals(Character2)){
                    return cmp.compare(Character1,Character2);
                }else{
                    if(number1<number2){
                        return -1;
                    }else{
                        return 1;
                    }
                }

            }
        }
    };

    /**
     * 凭证日期的比较器
     */
    private static final Comparator<VoucherVo> COMPARATORTWO=new Comparator<VoucherVo>() {

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public int compare(VoucherVo o1, VoucherVo o2) {
            String date1=o1.getDate();
            String date2=o2.getDate();

            Date d1,d2;
            try{
                d1=format.parse(date1);
                d2=format.parse(date2);


            } catch (ParseException e) {
                e.printStackTrace();
                return 0;
            }

            if(d1.before(d2)){
                return -1;
            }else{
                return 1;
            }
        }
    };

    public static void main(String[] args) {
//        ArrayList<VoucherVo> voList=new ArrayList<>();
//        VoucherVo vo1=new VoucherVo();
//        vo1.setDate("2010-08-20");
//        VoucherVo vo2=new VoucherVo();
//        vo2.setDate("2009-07-13");
//        VoucherVo vo3=new VoucherVo();
//        vo3.setDate("2008-09-20");
//        VoucherVo vo4=new VoucherVo();
//        vo4.setDate("2009-10-10");
//        voList.add(vo1);
//        voList.add(vo2);
//        voList.add(vo3);
//        voList.add(vo4);
//
//        Collections.sort(voList,COMPARATORTWO);
//
//        ArrayList<VoucherVo> resultList=getVoucherDateSort(voList);
//
//        for(int count=0;count<resultList.size();count++){
//            System.out.println(resultList.get(count).getDate());
//        }

        ArrayList<VoucherVo> voList=new ArrayList<>();
        VoucherVo vo1=new VoucherVo();
        vo1.setVoucherId("记-1");
        VoucherVo vo2=new VoucherVo();
        vo2.setVoucherId("收-10");
        VoucherVo vo3=new VoucherVo();
        vo3.setVoucherId("记-9");
        VoucherVo vo4=new VoucherVo();
        vo4.setVoucherId("记-8");
        VoucherVo vo5=new VoucherVo();
        vo5.setVoucherId("收-5");
        voList.add(vo1);
        voList.add(vo2);
        voList.add(vo3);
        voList.add(vo4);
        voList.add(vo5);

        ArrayList<VoucherVo> resultList=getVoucherNumberSort(voList);

        for(int count=0;count<resultList.size();count++){
            System.out.println(resultList.get(count).getVoucherId());
        }
    }
}
