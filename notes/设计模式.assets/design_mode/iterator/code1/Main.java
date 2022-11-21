package design_mode.iterator.code1;

import java.util.Iterator;

/**
 * @author yujh
 * @date 2022/11/10 14:02
 */
public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("Around thr World in 80 Days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("Cinderella"));
        bookShelf.appendBook(new Book("Daddy Long Legs"));
        Iterator iterator = bookShelf.iterator();
        while (iterator.hasNext()){
            System.out.println(((Book)iterator.next()).getName());
        }
    }
}
