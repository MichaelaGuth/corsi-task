package cz.pvsps.corsitask;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import cz.pvsps.corsitask.result.Result;
import org.json.simple.*;

import static cz.pvsps.corsitask.JSON_FileManager.loadJSON_File;
import static cz.pvsps.corsitask.JSON_FileManager.saveJSON_File;

public class Tools {

    public static ArrayList<ArrayList<Integer>> loadSequences(String fileName) {
        String jsonString = loadJSON_File(fileName);

        // TODO check if the json file is in good condition
        //if (jsonString.matches("[0-9]")

        Object obj = JSONValue.parse(jsonString);
        JSONArray array = (JSONArray) obj;
        ArrayList<ArrayList<Integer>> sequences = new ArrayList<>();
        for (Object item : array) {
            JSONArray tmp = (JSONArray) item;
            ArrayList<Integer> sequence = new ArrayList<>();
            for (Object o : tmp) {
                Long value = (Long) o;
                sequence.add(value.intValue());
            }
            sequences.add(sequence);
        }
        return sequences;
    }

    public static void saveResults(ArrayList<Result> results, String fileName) {
        JSONArray jsonResults = new JSONArray();

        for (Result result :
                results) {
            JSONObject jsonResult = new JSONObject();
            jsonResult.put("correctSequence",result.getCorrectSequence());
            jsonResult.put("userSequence",result.getUserSequence());
            jsonResult.put("userTime",result.getUserTime());
            jsonResults.add(jsonResult);
        }

        saveJSON_File(fileName, jsonResults.toJSONString());
    }
}
