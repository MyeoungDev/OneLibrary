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

    // List<BookDto>를 JSON 문자열로 변환하는 메소드입니다.
    public static String listToJsonString(List<BookDto> list) {
        // JSONArray.toJSONString() 메소드를 사용하여 List<BookDto>를 JSON 문자열로 변환합니다.
        return JSONArray.toJSONString(list);
    }

    // BookDto를 JSON 문자열로 변환하는 메소드입니다.
    public static String bookDtoToJsonString(BookDto bookDto) {
        // JSONObject 객체를 생성합니다.
        JSONObject jsonObject = new JSONObject();

        // JSONObject 객체에 책 정보를 추가합니다.
        jsonObject.put("bookNo", bookDto.getBookNo());
        jsonObject.put("title", bookDto.getTitle());
        jsonObject.put("content", bookDto.getContent());
        jsonObject.put("author", bookDto.getAuthor());
        jsonObject.put("categoryName", bookDto.getCategoryName());

        // JSONObject.toJSONString() 메소드를 사용하여 JSONObject 객체를 JSON 문자열로 변환합니다.
        return jsonObject.toJSONString();
    }

}