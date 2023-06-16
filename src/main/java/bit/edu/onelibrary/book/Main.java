package bit.edu.onelibrary.book;

import bit.edu.onelibrary.book.dao.BookDao;
import bit.edu.onelibrary.book.dto.BookDto;
import bit.edu.onelibrary.book.parser.JsonParser;
import bit.edu.onelibrary.book.service.BookService;
import bit.edu.onelibrary.util.FileUtils;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Main {
    public static void main(String[] args) {
        // BookDao 객체를 생성합니다.
        BookDao bookDao = new BookDao();
        // BookService 객체를 생성합니다. 생성자에 BookDao 객체를 전달합니다.
        BookService bookService = new BookService(bookDao);

        // BookService 객체의 findBookList() 메소드를 호출하여 책 목록을 조회합니다.
        List<BookDto> bookList = bookService.findBookList();

        // 조회한 책 목록을 JSON 문자열로 변환합니다.
        String bookListJsonString = JsonParser.listToJsonString(bookList);

        try {
            // FileUtils 클래스의 writeJsonFile() 메소드를 호출하여 JSON 파일을 생성합니다.
            FileUtils.writeJsonFile(bookListJsonString);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("실패");
        }

//        long bookNo = 1L;
//        BookDto bookByBookNo = bookService.findBookByBookNo(bookNo);
//        String bookJsonString = JsonParser.objectToJsonString(bookByBookNo);
//
//        try {
//            FileUtils.writeJsonFile(bookJsonString);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("실패");
//        }
    }
}