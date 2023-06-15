package bit.edu.onelibrary.book.parser;

import bit.edu.onelibrary.book.dto.BookDto;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class JsonParser {

    public static String listToJsonString(List<BookDto> list) {
        return JSONArray.toJSONString(list);
    }

    public static String bookDtoToJsonString(BookDto bookDto) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("bookNo", bookDto.getBookNo());
        jsonObject.put("title", bookDto.getTitle());
        jsonObject.put("content", bookDto.getContent());
        jsonObject.put("author", bookDto.getAuthor());
        jsonObject.put("categoryName", bookDto.getCategoryName());

        return jsonObject.toJSONString();
    }



}
