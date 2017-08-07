package dataservice;

import java.sql.SQLException;

/**
 * Created by loohaze on 2017/8/7.
 */
public interface InitialDB {

    public boolean createDB() throws SQLException;
}
