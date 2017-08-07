package dataTest;


import data.InitalDBImpl;
import dataservice.InitialDB;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by loohaze on 2017/8/7.
 */

public class InitialDBTest {

    private InitialDB initialDB;

    @Before
    public void setUp() throws Exception{
        initialDB = new InitalDBImpl();
    }

    @Test
    public void test(){
        boolean result = false;
        try {
            result = initialDB.createDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(true,result);
    }

}
