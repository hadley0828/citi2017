package businesslogic;

import businesslogicservice.AccountBooksBlService;
import vo.accountBook.*;

import java.util.ArrayList;

/**
 * Created by zhangzy on 2017/8/7 下午10:45
 */
public class AccountBooksBlImpl implements AccountBooksBlService {

    @Override
    public ArrayList<String> getAllExistedSubjectId(String factoryId) {
        return null;
    }

    @Override
    public DetailAccountVo getOneSubjectDetail(String subjectId, BookSearchVo searchVo, String factoryId) {
        return null;
    }

    @Override
    public ArrayList<TotalAccountVo> getAllSubjectTotal(BookSearchVo searchVo, String factoryId) {
        return null;
    }

    @Override
    public TotalAccountVo getOneSubjectTotal(String subjectId, BookSearchVo searchVo, String factoryId) {
        return null;
    }

    @Override
    public ArrayList<BalanceTableOneClause> getBalanceTableAllClauses(BookSearchVo searchVo, String factoryId) {
        return null;
    }

    @Override
    public ArrayList<GatherTableOneClause> getGatherTableAllClauses(BookSearchVo searchVo, String factoryId) {
        return null;
    }
}
