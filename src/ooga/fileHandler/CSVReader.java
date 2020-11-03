package ooga.fileHandler;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.List;

public class CSVReader {
    private String configFile;
    private String gameType;
    private Array[][] grid;
    private Resources resources;

    public CSVReader(String configurationFile) {
        this.configFile = configurationFile;
        this.resources = new Resources(Resources.ERROR_MESSAGES_FILE);
    }

    public CellMatrix getCellMatrix() {
        return cellMatrix;
    }

    // ------------------ Helper Methods ------------------

    /**
     * Reads in dimensions, alive/dead values from start.csv Method written by Robert Duvall - Piazza
     *
     * @return List of each row from the configuration file
     */
    public CellMatrix readInAllData() throws Exception {
        try {
            //configFile = "notreal";
            InputStream streamData = CSVReader.class.getClassLoader().getResourceAsStream(configFile);
            com.opencsv.CSVReader csvReader = new com.opencsv.CSVReader(
                    new InputStreamReader(streamData));
            List<String[]> allData = csvReader.readAll();

            parseData(allData);
            return cellMatrix;

        } catch (Exception e) {
            //throw new FileException(String.format(resources.getString("CannotReadFile"), configFile), e);
            throw e;
        }
    }

    /**
     * Calls helper methods described below to build the appropriate CellMatrix and Grid
     * @param allData
     */
    private void parseData(List<String[]> allData) {
        extractDimensions(allData);
        extractMatrix(allData);
    }

    /**
     * Gets the grid dimensions specified in the initial simulation layout's CSV file
     * @param allData
     */
    private void extractDimensions(List<String[]> allData) {
        String[] dimensions = allData.get(0);
        rows = Integer.parseInt(dimensions[0].replace("\uFEFF", ""));
        columns = Integer.parseInt(dimensions[1].strip());
    }

    /**
     * Uses the property file's configs to create a new CellMatrix with the appropriate rules, grid, and dimensions
     * @param allData
     */
    private void extractMatrix(List<String[]> allData) {
        Constructor constructor = getCellConstructor();
        Cell[][] gridLayout = new Cell[rows][columns];
        Constructor gridConstuctor = getGridConstructor();

        try {
            for (int row = 0; row < rows; row++) {
                String[] rowData = allData.get(row + 1);
                for (int col = 0; col < columns; col++) {
                    int status = Integer.parseInt(rowData[col]);
                    gridLayout[row][col] = (Cell) constructor.newInstance(new Object[] {row, col, status});
                }
            }
            Grid grid = (Grid) gridConstuctor.newInstance(new Object[] {rows, columns, gridLayout});
            cellMatrix = new CellMatrix(rows, columns, grid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Uses the property file's string that specifies the simulation type to create
     * a constructor for that simulation type's cell
     * Code inspired by http://www.avajava.com/tutorials/lessons/how-do-i-create-an-object-via-its-multiparameter-constructor-using-reflection.html
     * @return a generic constructor for the appropriate Cell
     */
    private Constructor getCellConstructor() {
        try {
            String cellClassName = ("model.cell." + simulationType + "Cell");
            Class cellClass = Class.forName(cellClassName);
            return cellClass.getConstructor(int.class, int.class, int.class);

        } catch (Exception e) {
            throw new NullPointerException();
        }
    }

    private Constructor getGridConstructor(){
        try{
            String gridName = ("model.cellMatrix." + gridType + "Grid");
            Class gridClass = Class.forName(gridName);
            return gridClass.getConstructor(int.class, int.class, Cell[][].class);
        } catch (Exception e){
            throw new NullPointerException();
        }
    }
}
