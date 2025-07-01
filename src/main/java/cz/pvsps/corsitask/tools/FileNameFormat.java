package cz.pvsps.corsitask.tools;

import cz.pvsps.corsitask.result.Score;

import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.pvsps.corsitask.Constants.*;

public class FileNameFormat {

    private static final Logger LOGGER = Logger.getLogger(FileNameFormat.class.getName());
    private final String format;

    private final String example;

    public FileNameFormat()
    {
        this.format = "";
        this.example = "";
    }

    public FileNameFormat(String format, String example) {
        this.format = format;
        this.example = example;
    }

    public String getFormat() {
        return format;
    }

    public String getExample() {
        return example;
    }

    public static FileNameFormat findFileNameFormat(String format) {
        if (format.equals(DATE_SURNAME_NAME_TIME.getFormat())) {
            return DATE_SURNAME_NAME_TIME;
        } else if (format.equals(ID.getFormat())) {
            return ID;
        } else if (format.equals(DATE_ID_TIME.getFormat())) {
            return DATE_ID_TIME;
        } else {
            LOGGER.log(Level.WARNING, "File name format is unknown. Setting to default value (DATE_ID_TIME) ...");
            return DATE_ID_TIME;
        }
    }

    public String getFileName(Score score) {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH_mm_ss");

        if (this == DATE_SURNAME_NAME_TIME) {
            return score.getTestDate().format(date) + " " + score.getPatientSurname() + " " + score.getPatientName() + " " + score.getTestDate().format(time);
        } else if (this == DATE_ID_TIME) {
            return score.getTestDate().format(date) + " " + score.getPatientID() + " " + score.getTestDate().format(time);
        } else if (this == ID) {
            return score.getPatientID().toString();
        } else {
            LOGGER.log(Level.WARNING, "File name format is unknown. Setting to default value (DATE_ID_TIME) ...");
            return score.getTestDate().format(date) + " " + score.getPatientID() + " " + score.getTestDate().format(time);
        }
    }

    public boolean containsID() {
        return this != DATE_SURNAME_NAME_TIME;
    }

    public String getDirectoryName(Score score) {

        // TODO check if user with the same name, surname and dateOfBirth doesnt already have a folder
        // database (hibernate?)
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (containsID()) {
            return score.getPatientSurname() + " " + score.getPatientName() + " " + score.getPatientBirthdate().format(date);
        } else {
            return score.getPatientID().toString();
        }
    }
}
