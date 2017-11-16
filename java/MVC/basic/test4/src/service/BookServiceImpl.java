package service;

import bean.BookBean;
import dao.BookDao;
import dao.BookDaoImpl;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public List<BookBean> fetchBookList() {
        List<BookBean> bookList=null;
        try{
            bookList = bookDao.fetchBookList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public int deleteBookById(int id) {
        int reslut = 0;
        try {
            reslut = bookDao.deleteBookById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
        return reslut;
    }
}
