package design_mode.iterator.code1;

import java.util.Iterator;

/**
 * @author yujh
 * @date 2022/11/10 13:57
 */
public class BookShelfIterator implements Iterator {
    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < bookShelf.getLength() ? true : false;
    }

    @Override
    public Object next() {
        return bookShelf.getBookAt(index++);
    }
}
