package bit.edu.onelibrary.book.service;

import bit.edu.onelibrary.book.dao.BookDao;
import bit.edu.onelibrary.book.dto.BookDto;
import bit.edu.onelibrary.book.exception.BookNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BookService {

    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<BookDto> findBookList() {
        List<BookDto> books;

        try {
            books = bookDao.findAllBooks();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public BookDto findBookByBookNo(Long bookNo) {

        BookDto bookDto;
        try {
            bookDto = bookDao.findBookByBookNo(bookNo)
                .orElseThrow(BookNotFoundException::new);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return bookDto;
    }

}
