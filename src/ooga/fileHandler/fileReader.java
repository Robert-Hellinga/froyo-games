package ooga.fileHandler;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.opencsv.CSVReader;
import ooga.exceptions.FileException;


public class fileReader {


    private String fileName;
    private String gameType;
    private Resources resources;


    public fileReader(String csvFileName) {
        this.fileName = csvFileName;
        try{
            readInAllData();
        }
        catch(Exception e){
            throw new FileException(String.format(resources.getString("CannotReadFile"), fileName), e);
        }
    }


    // ------------------ Helper Methods ------------------

    /**
     * Reads in dimensions, alive/dead values from start.csv Method written by Robert Duvall - Piazza
     *
     * @return List of each row from the configuration file
     */
    public List<String[]> readInAllData() throws Exception {
        try {

            InputStream streamData = CSVReader.class.getClassLoader().getResourceAsStream(fileName);
            CSVReader csvReader = new com.opencsv.CSVReader(
                    new InputStreamReader(streamData));
            List<String[]> allData = csvReader.readAll();
            gameType = allData.get(0)[0];

            allData.remove(0);
            return allData;


        } catch (Exception e) {
            throw new FileException(String.format(resources.getString("CannotReadFile"), fileName), e);

        }
    }
}
