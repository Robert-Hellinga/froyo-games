package ooga.fileHandler;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import ooga.exceptions.FileException;



public class FileReader {

  private String filePath;
  private Resources error;

  /**
   * Constructor specifically for reading in a new game's starting configuration.
   * Exists to make reflection easier
   * @param gameType - the type of game being played, used as part of file path
   * @param patternType - the type of pattern being used
   *                    (i.e default, test, any relevant custom patterns)
   */
  public FileReader(String gameType, String patternType) {
    this("configuration/" + gameType + "_" + patternType + ".csv");
  }

  /**
   *
   * @param filePathName
   */
  public FileReader(String filePathName) {
    this.filePath = filePathName;
    error = new Resources(Resources.ERROR_MESSAGES_FILE);

  }

  // ------------------ Helper Methods ------------------

  /**
   * Reads in data from a CSV file as a List<List<Integer>> , the primary format of a BlockStructure
   *
   * @return a CSVFile's output as a List<List<Integer>>
   * @author rbh13
   */
  public List<List<Integer>> readBlockLayout() {
    List<String[]> grid = readData();
    String gameType = grid.get(0)[0];
    grid.remove(0);
    List<List<Integer>> allConfig = new ArrayList<>();
    for (String[] configLine : grid) {
      List<Integer> configList = new ArrayList<>();
      for (String config : configLine) {
        configList.add(Integer.parseInt(config));
      }
      allConfig.add(configList);
    }

    return allConfig;
  }


  /***
   * Reads in data from a CSV file
   * @return A List<String> where each row is a line in the CSV file </String>
   * @author  rbh13, Robert Duvall - code primarily repurposed from simulation project
   * @throws FileException
   */
  public List<String[]> readData() throws FileException {
    try {
      InputStream streamData = CSVReader.class.getClassLoader().getResourceAsStream(filePath);
      assert streamData != null;
      CSVReader csvReader = new com.opencsv.CSVReader(
          new InputStreamReader(streamData));
      return csvReader.readAll();

    } catch (IOException | FileException | CsvException e) {
      throw new FileException(String.format(error.getString("CannotReadFile"), filePath), e);
    }
  }

  public void setFilePath(String newPath) {
    filePath = newPath;
  }
}