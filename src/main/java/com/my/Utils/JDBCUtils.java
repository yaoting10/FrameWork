package com.my.Utils;

import java.sql.*;

/**
 * Created with Test
 * User : Ting.Yao
 * Date : 2015/3/15.
 */
public class JDBCUtils {
   /* @Value("${dataSource.url}")*/
    private static String mysqlAddress;

    /*@Value("${dataSource.username}")*/
    private static String mysqlUserName = "root";

   /* @Value("${dataSource.password}")*/
    private static String mysqlPassword = "123";

    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                   mysqlAddress , mysqlUserName, mysqlPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection(Connection con){
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static ResultSet queryResult(Connection con, String sql){
        ResultSet set = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            set = ps.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    public static boolean execute(Connection con, String sql) {
        boolean result = false;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            result = ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
