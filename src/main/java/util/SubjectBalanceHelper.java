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
        if(firstNumber==1||firstNumber==4){
            return 1;
        }else if(firstNumber==2||firstNumber==3){
            return -1;
        }else{
            int secondNumber=Integer.valueOf(subjectId.charAt(1)-48);
            if(secondNumber>3){
                return 1;
            }else{
                return -1;
            }

        }
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