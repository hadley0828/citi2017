package data;

import dataservice.InitialDB;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by loohaze on 2017/8/7.
 */
public class InitalDBImpl implements InitialDB {


    public InitalDBImpl(){
        super();
    }

    /**
     * 创建数据库（读SQL脚本）
     * @return
     */
    public boolean createDB() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        boolean success = false;

        String dbName = "nd";
        try{
            List<String> sqlList = new ArrayList<String>();
            try{
                InputStream sqlFileIn = new BufferedInputStream(new FileInputStream(InitalDBImpl.class.getClassLoader().getResource("sql/tables_set.sql").getFile()));

                StringBuffer sqlSb = new StringBuffer();
                byte[] buff = new byte[1024];
                int byteRead = 0;
                while((byteRead = sqlFileIn.read(buff)) != -1){
                    sqlSb.append(new String(buff,0,byteRead,"utf-8"));
                }

                String[] sqlArr = sqlSb.toString().split( "(;\\s*\\r\\n)|(;\\s*\\n)");

                // 替换数据库名
                int replace = 0;
                for (int i = 0; i < sqlArr.length; i++) {
                    if (replace == 2) {
                        break;
                    }
                    if (sqlArr[i].contains("@@@dbName@@@")) {
                        sqlArr[i] = sqlArr[i].replace("@@@dbName@@@", dbName);
//                        System.out.println(sqlArr[i]);
                        replace++;
                    }
                }

                // 将数组转成LIST并且过滤LOCKTABLE 和注释
                for (int i = 0; i < sqlArr.length; i++) {
                    String sql = sqlArr[i].replaceAll("--.*", "").trim();
                    if (!sql.equals("") && sql.indexOf("LOCK TABLES") != 0
                            && sql.indexOf("UNLOCK TABLES") != 0
                            && sql.indexOf("/*") != 0) {
                        sqlList.add(sql);
                    }
                }

            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                System.out.println("sql script error!");
                e.printStackTrace();
            }
            // 创建数据库链接并执行SQL脚本
            conn = this.getConnection();
            stmt = null;
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for (String sql : sqlList) {
                stmt.addBatch(sql);
            }
            stmt.executeBatch();
            success = true;
        }catch (Exception e){
            // 如果报错，则删除D
            conn = null;
            conn = this.getConnection();
            stmt = null;
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.execute("drop database " + dbName);
            success = false;
        }finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
            return success;
        }
    }

    private Connection getConnection() throws SQLException, IOException,
            ClassNotFoundException {
        Connection con = null;
        String DRIVER = "com.mysql.jdbc.Driver";
        try{
            Class.forName(DRIVER);
        }catch (Exception e){
            System.out.println("驱动加载失败！");
        }

        Properties properties = new Properties();
        properties.load(new BufferedInputStream(new FileInputStream(InitalDBImpl.class.getClassLoader().getResource("citi.properties").getFile())));

        String u = properties.getProperty("db_user");
        String p = properties.getProperty("db_password");
        String ip = properties.getProperty("db_ip");
        String url =  "jdbc:mysql://" + ip + "/" + "" + "?useSSL=false&characterEncoding=utf8";
        con = DriverManager.getConnection(url,u,p);
        return con;
    }
}
