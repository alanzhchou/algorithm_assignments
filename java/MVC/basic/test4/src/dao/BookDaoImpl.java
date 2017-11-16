package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bean.BookBean;
import util.DBUtil;

public class BookDaoImpl implements BookDao {
    DBUtil dbutil = new DBUtil();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;
    ResultSetMetaData metaData = null;

    @Override
    public List<BookBean> fetchBookList() throws Exception{
        List<BookBean> bookBeanList=null;
        connection = dbutil.getConnection();
        String sql="select * from book";
        preparedStatement=connection.prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();
        bookBeanList=new ArrayList<BookBean>();
        while(resultSet.next()){
            BookBean bookBean=new BookBean();
            bookBean.setId(resultSet.getInt("bookId"));
            bookBean.setBookName(resultSet.getString("bookname"));
            bookBean.setAuthor(resultSet.getString("author"));
            bookBean.setPrice(resultSet.getDouble("price"));
            bookBean.setAddingDate(resultSet.getString("dateAdded"));
            bookBeanList.add(bookBean);
        }
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return bookBeanList;
    }

    @Override
    public int deleteBookById(int id) throws Exception {
        int result=0;
        connection=dbutil.getConnection();
        String sql="delete from book where bookId=?";
        preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        result=preparedStatement.executeUpdate();
        dbutil.closeDBResource(connection, preparedStatement, resultSet);
        return result;
    }
}
