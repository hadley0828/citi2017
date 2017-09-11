package util;

import data.SqlManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by loohaze on 2017/8/7.
 */
public class SubjectsTableInitial {

    static SqlManager sqlManager = SqlManager.getSqlManager();

    public SubjectsTableInitial(){
        super();
    }


    public static void fillSubjectsTable(){

        try {
            sqlManager.getConnection();

            FileReader fileReader = new FileReader(SubjectsTableInitial.class.getClassLoader().getResource("subjects.txt").getFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String input = bufferedReader.readLine();
            while((input = bufferedReader.readLine()) != null){
                String[] data = input.split(" ");
                List<Object> params = new ArrayList<Object>();
                params.add(data[0]);
                params.add(data[1]);

                String sql = sqlManager.appendSQL("INSERT INTO subjects VALUES",params.size());
                System.out.println(sql+ data[0] + data[1]);
                sqlManager.executeUpdateByList(sql,params);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlManager.commit();
    }


    public static void main(String[] args) {
        SubjectsTableInitial.fillSubjectsTable();
    }
}
