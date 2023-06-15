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
        BookDao bookDao = new BookDao();
        BookService bookService = new BookService(bookDao);

        List<BookDto> bookList = bookService.findBookList();

        String bookListJsonString = JsonParser.listToJsonString(bookList);

        try {
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
