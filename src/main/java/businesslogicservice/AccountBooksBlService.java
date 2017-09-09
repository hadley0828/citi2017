package businesslogicservice;

import vo.accountBook.BookSearchVo;
import vo.accountBook.DetailAccountVo;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/8/7 下午10:44
 * 处理和账簿相关功能的逻辑层接口
 */
public interface AccountBooksBlService {

    //明细账
    public ArrayList<String> getAllExistedSubjectId(String factoryId);

    public DetailAccountVo getOneSubjectDetail(String subjectId, BookSearchVo searchVo,String factoryId);




    //总账


    //科目余额表


    //科目汇总表


}
