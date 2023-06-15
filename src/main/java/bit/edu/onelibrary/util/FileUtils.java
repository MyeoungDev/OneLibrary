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
    private static final String JSON_FILE_PATH = "/Users/gwanii/Documents/douzone/lib-ui/assets/js/book.json";

    public static void writeJsonFile(String value) throws IOException {
        File file = new File(JSON_FILE_PATH);

        try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {
            fileWriter.write(value);
        }

    }
}
