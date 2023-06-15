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

    private static final String FIND_ALL_BOOK = "SELECT * FROM book";

    public List<BookDto> findAllBooks() throws SQLException, IOException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BOOK);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<BookDto> books = new ArrayList<>();
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

        return books;
    }

    public Optional<BookDto> findBookByBookNo(long bookNo) throws SQLException, IOException {
        Connection connection = ConnectionManager.getConnection();

        String sql = "SELECT * FROM book WHERE book_no = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, bookNo);

        ResultSet resultSet = preparedStatement.executeQuery();

        BookDto book = null;
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

        return Optional.ofNullable(book);
    }

}
