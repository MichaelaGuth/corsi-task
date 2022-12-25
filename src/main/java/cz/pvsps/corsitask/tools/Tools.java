package cz.pvsps.corsitask.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.Localization;
import cz.pvsps.corsitask.Main;
import cz.pvsps.corsitask.exceptions.FileNotFoundException;
import cz.pvsps.corsitask.result.Score;
import cz.pvsps.corsitask.settings.Configuration;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.pvsps.corsitask.Main.stage;

public class Tools {

    private static final Logger LOGGER = Logger.getLogger(Tools.class.getName());

    public static ArrayList<ArrayList<Block>> loadSequences(String fileName) {
        String jsonString = loadFile(fileName);
        Object obj = JSONValue.parse(jsonString);
        JSONArray array = (JSONArray) obj;
        ArrayList<ArrayList<Block>> sequences = new ArrayList<>();
        for (Object item : array) {
            JSONArray tmp = (JSONArray) item;
            ArrayList<Block> sequence = new ArrayList<>();
            for (Object o : tmp) {
                Long value = (Long) o;
                sequence.add(new Block(value.intValue()));
            }
            sequences.add(sequence);
        }
        LOGGER.log(Level.INFO, "Sequences have been successfully loaded.");
        return sequences;
    }


    // TODO refactor method
    public static void changeScene(SceneConfig sceneConfig) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(sceneConfig.getPath())));
            if (stage.isFullScreen() && sceneConfig.isFullscreen()){
                stage.getScene().setRoot(root);
            }
            else{
                stage.setScene(new Scene(root, sceneConfig.getWidth(), sceneConfig.getHeight()));
                stage.setFullScreen(sceneConfig.isFullscreen());
            }

            if (sceneConfig.isExitButtonOverridden()) {
                stage.setOnCloseRequest(windowEvent -> {
                    Tools.changeScene(Constants.MENU);
                    windowEvent.consume();
                });
            } else {
                stage.setOnCloseRequest(windowEvent -> {});
            }

            if (!sceneConfig.isFullscreen()){
                stage.centerOnScreen();
            }

            stage.show();
            LOGGER.log(Level.INFO, "File named: " + sceneConfig.getFileName() + " has been successfully loaded.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File named: " + sceneConfig.getFileName() + " could no be Loaded.");
            e.printStackTrace();
            stage.close();
            System.exit(100);
            // TODO
        }
    }

    public static String getDocumentsPath() {
        return FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
    }


    // TODO check with Honza
    public static Configuration loadConfiguration()  {
        Configuration configuration;
        try {
            String json_String = loadFile(Constants.CONFIGURATION_LOCATION);
            ObjectMapper mapper = new ObjectMapper();
            configuration = mapper.readValue(json_String, Configuration.class);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.WARNING, "Configuration file is corrupted. Creating new file with default values...");
            deleteFile(Constants.CONFIGURATION_LOCATION);
            configuration = new Configuration();
            saveObjectToJSONFile(configuration,Constants.CONFIGURATION_LOCATION);
            LOGGER.log(Level.INFO, "Configuration has been created to file: " + Constants.CONFIGURATION_LOCATION + ".");
            return configuration;
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "Configuration file was not found. Creating new file with default values...");
            configuration = new Configuration();
            saveObjectToJSONFile(configuration,Constants.CONFIGURATION_LOCATION);
            LOGGER.log(Level.INFO, "Configuration has been created to file: " + Constants.CONFIGURATION_LOCATION + ".");
            return configuration;
        }
        LOGGER.log(Level.INFO, "Configuration has been successfully loaded from file: " + Constants.CONFIGURATION_LOCATION + ".");
        return configuration;
    }

    public static Localization loadLocalization() {
        Localization localization;
        try {
            String json_String = loadFile(Constants.LOCALIZATION_LOCATION);
            ObjectMapper mapper = new ObjectMapper();
            localization = mapper.readValue(json_String, Localization.class);
        } catch (JsonProcessingException e) {
            LOGGER.log(Level.WARNING, "Localization file is corrupted. Creating new file with default values...");
            deleteFile(Constants.LOCALIZATION_LOCATION);
            localization = new Localization();
            saveObjectToJSONFile(localization,Constants.LOCALIZATION_LOCATION);
            LOGGER.log(Level.INFO, "Localization has been created to file: " + Constants.LOCALIZATION_LOCATION + ".");
            return localization;
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "Localization file was not found. Creating new file with default values...");
            localization = new Localization();
            saveObjectToJSONFile(localization,Constants.LOCALIZATION_LOCATION);
            LOGGER.log(Level.INFO, "Localization has been created to file: " + Constants.LOCALIZATION_LOCATION + ".");
            return localization;
        }
        LOGGER.log(Level.INFO, "Localization has been successfully loaded from file: " + Constants.LOCALIZATION_LOCATION + ".");
        return localization;
    }

    public static void saveObjectToJSONFile(Object o, String filePath) {
        ObjectMapper objectMapper =  JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        String jsonString;
        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        saveFile(new File(filePath), jsonString);
        LOGGER.log(Level.INFO, "Object "+ o.getClass().getName() + "has been successfully saved to file: " + filePath + ".");
    }

    public static Score loadScore(File file) {
        Score score;
        try {
            String json_String = loadFile(file);
            ObjectMapper mapper = JsonMapper.builder()
                    .addModule(new JavaTimeModule())
                    .build();
            score = mapper.readValue(json_String, Score.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        LOGGER.log(Level.INFO, "Results have been successfully loaded from file:" + file.getPath() + ".");
        return score;
    }

    public static void resize(Pane pane) {
        if (stage.isFullScreen()) {
            Rectangle2D resolution = Screen.getPrimary().getBounds();
            double scale = resolution.getHeight() / pane.getPrefHeight();
            pane.setScaleX(scale);
            pane.setScaleY(scale);
        }
    }

    public static String loadFile(String fileName) {
        String fileContents = "";
        File file = new File(fileName);
        fileContents = loadFile(file);
        return fileContents;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static String loadFile(File file) {
        String fileContents = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fileInputStream.read(data);
            fileInputStream.close();
            fileContents = new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileNotFoundException(e);
        }
        return fileContents;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void saveFile(File file, String fileContents) {
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }
        try (FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write(fileContents);
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                LOGGER.log(Level.INFO, "File: " + filePath + " has been successfully deleted.");
            } else {
                LOGGER.log(Level.WARNING, "File: " + filePath + " could not be deleted.");
            }
        } else {
            LOGGER.log(Level.INFO, "File: " + filePath + " could not be, because it does not exist.");
        }
    }

}
