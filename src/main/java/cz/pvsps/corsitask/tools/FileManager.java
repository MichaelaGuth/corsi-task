package cz.pvsps.corsitask.tools;

import cz.pvsps.corsitask.exceptions.FileNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileManager {

    public static String loadJSON_File(String fileName) {
        String jsonString = "";
        File file = new File(fileName);
        jsonString = loadJSON_File(file);
        return jsonString;
    }

    public static String loadJSON_File(File file) {
        String jsonString = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fileInputStream.read(data);
            fileInputStream.close();
            jsonString = new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileNotFoundException(e);
        }
        return jsonString;
    }


    public static void saveJSON_File(File file, String jsonString) {
        file.getParentFile().mkdirs();
        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(jsonString);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
