package design_mode.iterator.code2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yujh
 * @date 2022/11/10 13:53
 */
public class BookShelf implements Aggregate {
//    private Book[] books;
    private List<Book> books;
    private int last = 0;

    public BookShelf() {
        this.books = new ArrayList<>();
    }

    public Book getBookAt() {
        return books.remove(0);
    }

    public void appendBook(Book book) {
        this.books.add(book);
        last++;
    }

    public int getLength() {
        return last;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
