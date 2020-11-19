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

  public FileReader(String gameType, String patternType) {
    this("configuration/" + gameType + "_" + patternType + ".csv");
  }

  public FileReader(String filePathName) {
    this.filePath = filePathName;
    error = new Resources(Resources.ERROR_MESSAGES_FILE);

  }

  // ------------------ Helper Methods ------------------

  /**
   * Reads in dimensions, alive/dead values from start.csv Method written by Robert Duvall - Piazza
   *
   * @return List of each row from the configuration file
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