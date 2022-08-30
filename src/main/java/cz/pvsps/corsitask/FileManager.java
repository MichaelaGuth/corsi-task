package cz.pvsps.corsitask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileManager {

    public static String loadJSON_File(String fileName) {
        String jsonString = "";
        try {
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fileInputStream.read(data);
            fileInputStream.close();
            jsonString = new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }

    public static void saveJSON_File(String fileName, String jsonString) {
        try (FileWriter fileWriter = new FileWriter(fileName)){
            fileWriter.write(jsonString);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
