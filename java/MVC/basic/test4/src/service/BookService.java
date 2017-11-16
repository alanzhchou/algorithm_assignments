package service;

import bean.BookBean;
import java.util.List;

public interface BookService {
    List<BookBean> fetchBookList();
    int deleteBookById(int id);
}
