package bit.edu.onelibrary.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class FileUtils {
    // JSON 파일 경로를 상수로 정의합니다.
    private static final String JSON_FILE_PATH = "/Users/gwanii/Documents/douzone/lib-ui/assets/js/book.json";

    // JSON 파일에 값을 쓰는 메소드입니다.
    public static void writeJsonFile(String value) throws IOException {
        // JSON 파일을 나타내는 File 객체를 생성합니다.
        File file = new File(JSON_FILE_PATH);

        // try-with-resources 구문을 사용하여 FileWriter 객체를 생성합니다.
        // FileWriter 객체를 사용하여 JSON 파일에 값을 씁니다.
        try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {
            fileWriter.write(value);
        }
    }
}
