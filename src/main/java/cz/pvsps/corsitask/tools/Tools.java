package cz.pvsps.corsitask.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.Main;
import cz.pvsps.corsitask.exceptions.FileNotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.pvsps.corsitask.Main.stage;
import static cz.pvsps.corsitask.tools.FileManager.loadJSON_File;
import static cz.pvsps.corsitask.tools.FileManager.saveJSON_File;

public class Tools {

    private static Logger LOGGER = Logger.getLogger(Tools.class.getName());

    public static ArrayList<ArrayList<Integer>> loadSequences(String fileName) {
        String jsonString = loadJSON_File(fileName);
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

    public static void changeScene(Constants.FxmlFile fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource(fxmlFile.getPath()));
            Main.stage.setScene(new Scene(root, fxmlFile.getSceneWidth(), fxmlFile.getSceneHeight()));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File named: " + fxmlFile.getName() + " could no be Loaded.");
            // TODO
            Main.stage.close();
            System.exit(100);
        }
        switch (fxmlFile) {
            case TRIAL, TEST_INSTRUCTIONS ->
            {
                Main.stage.setFullScreen(true);
                Main.stage.setFullScreenExitHint("");
                Main.stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            }
            default -> Main.stage.setFullScreen(false);
        }
        Main.stage.show();
        LOGGER.log(Level.INFO, "File named: " + fxmlFile.getName() + " has been successfully loaded.");
    }

    public static String getDocumentsPath() {
        return FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
    }

    public static Configuration loadConfiguration()  {
        Configuration configuration;
        try {
            String json_String = loadJSON_File(Constants.CONFIGURATION_LOCATION);
            ObjectMapper mapper = new ObjectMapper();
            configuration = mapper.readValue(json_String, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            configuration = new Configuration();
            saveObjectToJSON(configuration,Constants.CONFIGURATION_LOCATION);
            LOGGER.log(Level.INFO, "Configuration has been created to file: " + Constants.CONFIGURATION_LOCATION + ".");
        }
        LOGGER.log(Level.INFO, "Configuration has been successfully loaded from file: " + Constants.CONFIGURATION_LOCATION + ".");
        return configuration;
    }

    public static void saveObjectToJSON(Object o, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString;
        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        saveJSON_File(new File(filePath), jsonString);
    }

    public static void resize(Pane pane) {
        if (stage.isFullScreen()) {
            Rectangle2D resolution = Screen.getPrimary().getBounds();
            double scale = resolution.getHeight() / pane.getPrefHeight();
            pane.setScaleX(scale);
            pane.setScaleY(scale);
        }
    }

}
