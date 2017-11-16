package dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.UserinfoBean;
import util.DBUtil;

public class UserinfoDaoImpl implements UserinfoDao {
    @Override
    public int login(String	username,	String	password) throws Exception {
        int result = 0;
        DBUtil dbutil = new DBUtil();
        Connection connection = dbutil.getConnection();
        String sql = "SELECT count(*) FROM	userinfo WHERE	username=?	AND	password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        dbutil.closeDBResource(connection,preparedStatement,resultSet);
        return result;
    }

    @Override
    public int registerUserinfo(UserinfoBean userinfoBean) throws Exception {
        int result = 0;
        DBUtil dbutil = new DBUtil();
        Connection connection = dbutil.getConnection();
        String sql = "insert into userinfo (username, password) values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userinfoBean.getUsername());
        preparedStatement.setString(2, userinfoBean.getPassword());
        result = preparedStatement.executeUpdate();
        connection.close();
//        dbutil.closeDBResource(connection,preparedStatement,resultSet);
        return result;
    }
}
