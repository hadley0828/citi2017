package businesslogic;

import businesslogicservice.AccountBooksBlService;
import org.junit.Before;
import org.junit.Test;
import vo.accountBook.BookSearchVo;

import static org.junit.Assert.*;

/**
 * Created by zhangzy on 2017/9/12 下午7:09
 */
public class AccountBooksBlImplTest {

    private AccountBooksBlService accountBooksBlService;

    @Before
    public void setUp() throws Exception{
        accountBooksBlService=new AccountBooksBlImpl();
    }

    @Test
    public void getAllExistedSubjectId() throws Exception {
        System.out.println(accountBooksBlService.getAllExistedSubjectId("001"));

    }

    @Test
    public void getOneSubjectDetail() throws Exception {
        BookSearchVo searchVo=new BookSearchVo();
        searchVo.setStartPeriod("2017年第6期");
        searchVo.setEndPeriod("2017年第6期");
        searchVo.setHighLevel(1);
        searchVo.setLowLevel(1);

        System.out.println(accountBooksBlService.getOneSubjectDetail("1001",searchVo,"001"));


    }

    @Test
    public void getAllSubjectTotal() throws Exception {
        BookSearchVo searchVo=new BookSearchVo();
        searchVo.setStartPeriod("2017年第6期");
        searchVo.setEndPeriod("2017年第7期");
        searchVo.setHighLevel(1);
        searchVo.setLowLevel(1);

        System.out.println(accountBooksBlService.getAllSubjectTotal(searchVo,"001"));


    }

    @Test
    public void getOneSubjectTotal() throws Exception {


    }

    @Test
    public void getBalanceTableAllClauses() throws Exception {
        BookSearchVo searchVo=new BookSearchVo();
        searchVo.setStartPeriod("2017年第6期");
        searchVo.setEndPeriod("2017年第7期");
        searchVo.setHighLevel(1);
        searchVo.setLowLevel(1);

        System.out.println(accountBooksBlService.getBalanceTableAllClauses(searchVo,"001"));

    }

    @Test
    public void getGatherTableAllClauses() throws Exception {
    }

    @Test
    public void getAllSubjectPeriodEndPrice() throws Exception {
    }

}