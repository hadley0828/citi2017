package util;


import vo.voucher.SubjectBasicVo;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/9/12 上午10:03
 * 用来实现获取全部科目的筛选的工具类
 */
public class SubjectBasicHelper {

    public static int getIndexOfSubjectsList(ArrayList<SubjectBasicVo> voList,String subjectId){

        if(voList.size()==0){
            return -1;
        }else{
            for(int count=0;count<voList.size();count++){
                String oneSubjectId=voList.get(count).getSubjectId();
                if(oneSubjectId.equals(subjectId)){
                    return count;
                }


            }
        }
        return -1;
    }
}
