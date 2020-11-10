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
import ooga.model.checkerboard.BlockConfigStructure;


public class FileReader {


    private final String fileName;
    private Resources resources;


    public FileReader(String gameType, String patternType) {
        this.fileName = "configuration/" + gameType + "_" + patternType + ".csv";
        System.out.println(fileName);
//        try{
//            readInAllData();
//        }
//        catch(Exception e){
//            throw new FileException(String.format(resources.getString("CannotReadFile"), fileName), e);
//        }
    }


    // ------------------ Helper Methods ------------------

    /**
     * Reads in dimensions, alive/dead values from start.csv Method written by Robert Duvall - Piazza
     *
     * @return List of each row from the configuration file
     */
    public BlockConfigStructure readInAllData() throws FileException {
        try {
            InputStream streamData = CSVReader.class.getClassLoader().getResourceAsStream(fileName);
            assert streamData != null;
            CSVReader csvReader = new com.opencsv.CSVReader(
                    new InputStreamReader(streamData));
            List<String[]> allData = csvReader.readAll();
//            String gameType = allData.get(0)[0];
            allData.remove(0);
            List<List<Integer>> allConfig = new ArrayList<>();
            for (String[] configLine: allData){
                List<Integer> configList = new ArrayList<>();
                for (String config: configLine){
                    configList.add(Integer.parseInt(config));
                }
                allConfig.add(configList);
            }
            return new BlockConfigStructure(allConfig);

        } catch (Exception e) {
            e.printStackTrace();
            throw new FileException(String.format(resources.getString("CannotReadFile"), fileName), e);
        }
    }
}
