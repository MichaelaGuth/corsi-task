package cz.pvsps.corsitask;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.*;

public class Tools {

    public static ArrayList<int[]> loadSequences() {
        String jsonString = "";
        try {
            File file = new File("sequences.json");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fileInputStream.read(data);
            fileInputStream.close();
            jsonString = new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Object obj = JSONValue.parse(jsonString);
        JSONArray array = (JSONArray) obj;
        ArrayList<int[]> sequences = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONArray tmp = (JSONArray) array.get(i);
            int[] sequence = new int[tmp.size()];
            for (int j = 0; j < tmp.size(); j++) {
                Long value = (Long) tmp.get(j);
                sequence[j] = value.intValue();

            }
            sequences.add(sequence);
        }
        return sequences;
    }
}
