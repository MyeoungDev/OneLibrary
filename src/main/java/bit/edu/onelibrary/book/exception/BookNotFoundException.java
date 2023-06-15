package bit.edu.onelibrary.book.exception;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BookNotFoundException extends RuntimeException {

    private static final String message = "해당 책을 찾을 수 없습니다.";
    public BookNotFoundException() {
        super(message);
    }
}
