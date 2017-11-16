package dao;

import bean.BookBean;

import java.util.List;

public interface BookDao {

    List<BookBean> fetchBookList() throws Exception;

    int deleteBookById(int id) throws Exception;
}
