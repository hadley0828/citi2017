package util.DataBaseInitial;

import data.SqlManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by loohaze on 2017/9/12 下午3:39
 */
public class IndustryTableInitial {

    static SqlManager sqlManager = SqlManager.getSqlManager();

    public static void intialIndustryTable() throws IOException {
        sqlManager.getConnection();

        FileReader fileReader = new FileReader(IndustryTableInitial.class.getClassLoader().getResource("industry.txt").getFile());
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String input;
        while ((input = bufferedReader.readLine()) != null){
            String[] data = input.split(":");
            String[] subIndustry = data[1].split(",");
            for (String s : subIndustry){
                List<Object> params = new ArrayList<>();
                params.add(data[0]);
                params.add(s);
                String sql = sqlManager.appendSQL("insert into industry values",params.size());
                sqlManager.executeUpdateByList(sql,params);
            }
        }

        sqlManager.commit();
    }

    public static void main(String[] args) throws IOException {
        IndustryTableInitial.intialIndustryTable();
    }
}
