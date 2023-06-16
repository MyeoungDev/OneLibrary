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

    // 생성자에서 BookDao 객체를 받아와서 멤버 변수에 할당합니다.
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    // 모든 책을 조회하는 메소드입니다.
    public List<BookDto> findBookList() {
        List<BookDto> books;

        try {
            // BookDao 객체의 findAllBooks() 메소드를 호출하여 모든 책을 조회합니다.
            books = bookDao.findAllBooks();
        } catch (SQLException | IOException e) {
            // 예외가 발생한 경우 RuntimeException 예외를 던집니다.
            throw new RuntimeException(e);
        }

        // 조회한 책 목록을 반환합니다.
        return books;
    }

    // 책 번호를 이용하여 책을 조회하는 메소드입니다.
    public BookDto findBookByBookNo(Long bookNo) {
        BookDto bookDto;

        try {
            // BookDao 객체의 findBookByBookNo() 메소드를 호출하여 책을 조회합니다.
            // Optional을 사용하여 null 체크를 수행합니다.
            bookDto = bookDao.findBookByBookNo(bookNo)
                .orElseThrow(BookNotFoundException::new);
        } catch (SQLException | IOException e) {
            // 예외가 발생한 경우 RuntimeException 예외를 던집니다.
            throw new RuntimeException(e);
        }

        // 조회한 책 정보를 반환합니다.
        return bookDto;
    }

}
