package util;

/**
 * Created by zhangzy on 2017/9/9 下午12:37
 * 根据会计科目的编号判断方向是借还是贷
 */
public class SubjectBalanceHelper {

    /**
     * 根据会计科目的第一个数字来判断方向是借还是贷
     * 借返回1 贷返回-1
     * @param subjectId
     * @return
     */
    public static int getDirection(String subjectId){
        int firstNumber=Integer.valueOf(subjectId.charAt(0)-48);
        if(firstNumber==4){
            return 1;
        }else if(firstNumber==2||firstNumber==3){
            return -1;
        }else if(firstNumber==1){
            if(subjectId.equals("1407")||subjectId.equals("1602")||subjectId.equals("1622")||subjectId.equals("1702")){
                return -1;
            }else{
                return 1;
            }
        }else{
            int secondNumber=Integer.valueOf(subjectId.charAt(1)-48);
            if(secondNumber>3){
                return 1;
            }else{
                return -1;
            }

        }
    }

    /**
     * 根据科目编号获得这个会计科目对应的方向
     * @param subjectId
     * @return
     */
    public static String getDirectionString(String subjectId){
        if(getDirection(subjectId)==1){
            return "借";
        }else if(getDirection(subjectId)==-1){
            return "贷";
        }
        return "平";
    }

    /**
     * 根据科目编号获得会计科目的级别
     * @param subjectId
     * @return
     */
    public static int getSubjectLevel(String subjectId){
        int result=0;

        int length=subjectId.length();

        if(length==4){
            return 1;
        }else if(length==7){
            return 2;
        }else if(length==9){
            return 3;
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println(getDirection("1001"));
        System.out.println(getDirection("2001"));
        System.out.println(getDirection("3001"));
        System.out.println(getDirection("4001"));
        System.out.println(getDirection("5301"));
        System.out.println(getDirection("5401"));
    }
}
