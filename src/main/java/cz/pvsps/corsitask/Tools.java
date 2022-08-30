package cz.pvsps.corsitask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.pvsps.corsitask.result.SequenceScore;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.WindowEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.pvsps.corsitask.FileManager.loadJSON_File;
import static cz.pvsps.corsitask.FileManager.saveJSON_File;

public class Tools {

    private static Logger LOGGER = Logger.getLogger(Tools.class.getName());

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

    public static void saveResults(ArrayList<SequenceScore> sequenceScores, String fileName) {
        JSONArray jsonResults = new JSONArray();

        for (SequenceScore sequenceScore :
                sequenceScores) {
            JSONObject jsonResult = new JSONObject();
            jsonResult.put("correctSequence", sequenceScore.getCorrectSequence());
            jsonResult.put("userSequence", sequenceScore.getUserSequence());
            jsonResult.put("userTime", sequenceScore.getUserTime());
            jsonResults.add(jsonResult);
        }

        saveJSON_File(fileName, jsonResults.toJSONString());
    }

    public static void changeScene(Constants.FxmlFile fxmlFile) throws IOException {

            Parent root = FXMLLoader.load(Main.class.getResource(fxmlFile.getPath()));
            Main.stage.setScene(new Scene(root, fxmlFile.getSceneWidth(), fxmlFile.getSceneHeight()));
            Main.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    event.consume();
                }
            });
            Main.stage.show();
            LOGGER.log(Level.INFO, "File named: " + fxmlFile.getName() + " has been successfully loaded.");

    }

    public static String getDocumentsPath() {
        return FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
    }

    public static Configuration loadConfiguration()  {
        String json_String = loadJSON_File(getDocumentsPath()+"\\Corsi Test\\settings.json");
        ObjectMapper mapper = new ObjectMapper();
        Configuration configuration;
        try {
            configuration = mapper.readValue(json_String, Configuration.class);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
        return configuration;
    }

    public static void saveConfiguration(Configuration configuration) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(configuration);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        saveJSON_File(getDocumentsPath()+Constants.CONFIGURATION_LOCATION, jsonString);
    }

}
