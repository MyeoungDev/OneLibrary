package bit.edu.onelibrary.book.dao;

import bit.edu.onelibrary.book.dto.BookDto;
import bit.edu.onelibrary.util.ConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BookDao {

    // 책 목록을 조회하는 SQL 쿼리입니다.
    private static final String FIND_ALL_BOOK = "SELECT * FROM book";

    // 모든 책을 조회하는 메소드입니다.
    public List<BookDto> findAllBooks() throws SQLException, IOException {
        // ConnectionManager 클래스의 getConnection() 메소드를 호출하여 Connection 객체를 생성합니다.
        Connection connection = ConnectionManager.getConnection();
        // Connection 객체를 사용하여 PreparedStatement 객체를 생성합니다.
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BOOK);

        // PreparedStatement 객체를 사용하여 SQL 쿼리를 실행하고, ResultSet 객체를 반환합니다.
        ResultSet resultSet = preparedStatement.executeQuery();

        // 조회한 책 목록을 저장할 List 객체를 생성합니다.
        List<BookDto> books = new ArrayList<>();
        // ResultSet 객체에서 책 정보를 읽어와서 BookDto 객체를 생성하고, List 객체에 추가합니다.
        while (resultSet.next()) {
            books.add(new BookDto(
                resultSet.getLong("book_no"),
                resultSet.getString("title"),
                resultSet.getString("content"),
                resultSet.getBoolean("rental_check"),
                resultSet.getString("author"),
                resultSet.getBoolean("display_check"),
                resultSet.getString("category_name")
                )
            );
        }

        // 조회한 책 목록을 반환합니다.
        return books;
    }

    // 책 번호를 이용하여 책을 조회하는 메소드입니다.
    public Optional<BookDto> findBookByBookNo(long bookNo) throws SQLException, IOException {
        // ConnectionManager 클래스의 getConnection() 메소드를 호출하여 Connection 객체를 생성합니다.
        Connection connection = ConnectionManager.getConnection();

        // 책 번호를 이용하여 책을 조회하는 SQL 쿼리입니다.
        String sql = "SELECT * FROM book WHERE book_no = ?";

        // Connection 객체를 사용하여 PreparedStatement 객체를 생성합니다.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, bookNo);

        // PreparedStatement 객체를 사용하여 SQL 쿼리를 실행하고, ResultSet 객체를 반환합니다.
        ResultSet resultSet = preparedStatement.executeQuery();

        // 조회한 책 정보를 저장할 BookDto 객체를 생성합니다.
        BookDto book = null;
        // ResultSet 객체에서 책 정보를 읽어와서 BookDto 객체를 생성합니다.
        while (resultSet.next()) {
            book = new BookDto(
                    resultSet.getLong("book_no"),
                    resultSet.getString("title"),
                    resultSet.getString("content"),
                    resultSet.getBoolean("rental_check"),
                    resultSet.getString("author"),
                    resultSet.getBoolean("display_check"),
                    resultSet.getString("category_name")
                );
        }

        // 조회한 책 정보를 Optional 객체로 감싸서 반환합니다.
        return Optional.ofNullable(book);
    }

}