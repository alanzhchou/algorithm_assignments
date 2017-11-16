package bean;

/**
 * Created by ZH-AlanChou on 2017/11/16.
 */
public class BookBean {
    private int id;
    private String bookName;
    private String author;
    private double price;
    private String addingDate;

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public String getAddingDate() {
        return addingDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAddingDate(String addingDate) {
        this.addingDate = addingDate;
    }
}
