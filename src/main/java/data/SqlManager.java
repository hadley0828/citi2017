package data;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by loohaze on 2017/8/6.
 */
public class SqlManager {

    /**
     * singleton
     */
    private static SqlManager sqlManager;


    /**
     * database information
     */
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = null;
    private static String IP = null;
    private static String USER = null;
    private static String PASSWORD = null;
    private static String DB = null;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;


    static{
        Properties prop = new Properties();
        try{
            InputStream in = new BufferedInputStream(new FileInputStream(SqlManager.class.getClassLoader().getResource("citi.properties").getFile()));
            prop.load(in);
            IP = prop.getProperty("db_ip");
            USER = prop.getProperty("db_user");
            PASSWORD = prop.getProperty("db_password");
            DB = prop.getProperty("db");
            URL = "jdbc:mysql://" + IP + "/" + DB + "?useSSL=false&characterEncoding=utf8";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造函数 初始化
     */
    private SqlManager(){
        try{
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("找不到驱动程序类，加载驱动失败");
            e.printStackTrace();
        }
    }

    /**
     * 单例
     * @return
     */
    public static SqlManager getSqlManager(){
        if(sqlManager != null){
            return sqlManager;
        }
        return new SqlManager();
    }

    /**
     * 获得数据库连接
     * @return
     */
    public synchronized Connection getConnection(){
        try{
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 提交更改
     */
    public void commit(){
        try{
            connection.commit();
        } catch (SQLException e) {
            System.out.println("提交更改失败");
            e.printStackTrace();
        }
    }

    /**
     * 释放Connection资源
     */
    public void releaseConnection(){
        if(connection != null){
            try{
                connection.commit();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 释放PreparedStatement资源
     */
    public void releasePreparedStatement(){
        if(preparedStatement != null){
            try{
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放Statement资源
     */
    public void releaseStatement(){
        if(preparedStatement != null){
            try{
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放ResultSet资源
     */
    public void releaseResultSet(){
        if(resultSet != null){
            try{
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放所有资源
     */
    public void releaseAll(){
        releasePreparedStatement();
        releaseResultSet();
        releaseConnection();
    }

    /**
     * 更新数据库的SQL语句的统一方法
     * @param sql
     * @return
     */
    public boolean executeUpdate(String sql){
        int affectedLine = 0;
        try{
            statement = connection.createStatement();
            affectedLine = statement.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            releaseStatement();
        }
        return affectedLine > 0;

    }

    /**
     * 更新数据库的SQL语句的统一方法
     * @param sql
     * @param params
     * @return
     */
    public boolean executeUpdateByList(String sql, List<Object> params){
        boolean result = false;

        int affectedLine = 0;

        try{
            preparedStatement = connection.prepareStatement(sql);
            if(params != null){
                for(int i = 0; i < params.size(); i++){
                    preparedStatement.setObject(i+1, params.get(i));
                }
            }
            affectedLine = preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            releasePreparedStatement();
        }

        result = affectedLine > 0;
        return result;
    }

    /**
     * 查询单条信息
     * @param sql
     * @param params
     * @return
     */
    public Map<String,Object> querySimple(String sql,Object[] params){
        Map<String, Object> map = new HashMap<String , Object>();
        try{
            preparedStatement = connection.prepareStatement(sql);
            if(params != null && params.length != 0){
                for(int i = 0; i < params.length; i++){
                    preparedStatement.setObject(i+1,params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int col_length = resultSetMetaData.getColumnCount();
            while(resultSet.next()){
                for(int i = 0; i < col_length; i ++){
                    String key = resultSetMetaData.getColumnName(i+1);
                    Object value = resultSet.getObject(key);
                    if(value == null){
                        value = "";
                    }
                    map.put(key, value);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return map;
    }

    /**
     * 查询多条信息
     * @param sql
     * @param params
     * @return
     */
    public ArrayList<Map<String,Object>> queryMulti(String sql,Object[] params){
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String , Object>>();

        try{
            preparedStatement = connection.prepareStatement(sql);
            if(params != null && params.length != 0){
                for(int i = 0 ; i < params.length ; i++){
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData  resultSetMetaData = resultSet.getMetaData();
            int col_length = resultSetMetaData.getColumnCount();
            while(resultSet.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                for(int i = 0; i < col_length; i ++){
                    String key =resultSetMetaData.getColumnName(i+1);
                    Object value = resultSet.getObject(key);
                    if(value == null){
                        value = "";
                    }
                    map.put(key, value);
                }
                list.add(map);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 补全SQL 参数
     * @param sql
     * @param num
     * @return
     */
    public String appendSQL(String sql,int num){
        sql += "(";
        for(int i = 0; i < num; i++){
            if(i == num - 1){
                sql += "?)";
                break;
            }
            sql += "?,";
        }
        return sql;
    }

    public static void main(String[] args) throws SQLException{
        try{
            SqlManager.getSqlManager().getConnection();
            System.out.println("success!");
        }catch (Exception e){
            System.out.println("fail!");
        }

    }
}
