package generators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

/**
 * Generates the report from the provided data HashMap.
 *
 * @author Aune Mitchell
 */
public class ReportGenerator {

  /**
   * Generates the report from the provided HashMap.
   *
   * @param data             The HashMap with Integer keys and Double values.
   * @param fileNameForCsv   The file name for the generated CSV.
   * @param parentDirForCsvs The parent directory for the generated CSV.
   * @return The generated file.
   * @throws IOException If the CSV file cannot be created or written to.
   */
  public File generateReport(final HashMap<Integer, Double> data,
                             final String fileNameForCsv,
                             final String parentDirForCsvs)
          throws IOException {
    // Throw exceptions if params are null or empty
    if (data == null || data.isEmpty()
            || fileNameForCsv == null || fileNameForCsv.isEmpty()
            || parentDirForCsvs == null || parentDirForCsvs.isEmpty()) {
      throw new IllegalArgumentException("Parameters cannot be null or empty.");
    }

    // Validate the data
    validateData(data);

    // Validate the parent directory
    GeneratorHelpers generatorHelpers = new GeneratorHelpers();
    String formattedParentDir = generatorHelpers.validateDirectory(
            parentDirForCsvs);

    // Format the file name
    String formattedCsvName = generatorHelpers.formatFileName(fileNameForCsv);

    // Validate the file name
    if (!fileNameForCsv.endsWith(".csv")) {
      formattedCsvName += ".csv";
    }

    // Adds timestamp to filename
    String dateTimePrefix = generatorHelpers.getDateTimeString() + "_";
    formattedCsvName = dateTimePrefix + formattedCsvName;

    // Creates the CSV and returns it
    return createCsv(data, formattedParentDir, formattedCsvName);
  }

  /**
   * Validates the provided HashMap data.
   *
   * @param data The HashMap of Integer keys and Double values.
   * @throws IllegalArgumentException if there aren't 14 keys or if
   *                                  a required array size is
   *                                  missing from the keys.
   */
  private void validateData(final HashMap<Integer, Double> data) {
    // Get the set of keys from the data HashMap
    Set<Integer> dataKeys = data.keySet();

    // Throw exception if the size isn't equal to 14
    // since we need 14 arrays
    if (dataKeys.size() != 14) {
      throw new IllegalArgumentException("Keys must be the 14 array sizes.");
    }

    // Throw exception if missing a key for the required array sizes
    for (int exponent = 2; exponent <= 15; exponent++) {
      int arraySize = (int) Math.pow(2, exponent);
      if (!dataKeys.contains(arraySize)) {
        throw new IllegalArgumentException(
                "Data is missing required array size: " + arraySize);
      }
    }
  }

  /**
   * Creates a CSV from the provided data, parent directory, and CSV file name.
   * Runtime complexity: O(N), N is the number of keys in the data HashMap.
   *
   * @param data The HashMap containing Integer keys and Double values.
   * @param parentDir The parent directory for the CSV file.
   * @param csvName The file name for the CSV.
   * @return The CSV File object.
   * @throws IOException if the file cannot be created or written to.
   */
  private File createCsv(final HashMap<Integer, Double> data,
                         final String parentDir,
                         final String csvName) throws IOException {
    // Create the CSV file
    File csv = new File(parentDir + csvName);
    // Try with resources block automatically closes the FileWriter object
    try (FileWriter csvWriter = new FileWriter(csv)) {
      // For each key value pair, add it as a new line to the CSV
      for (Integer key : data.keySet()) {
        csvWriter.write(key + "," + data.get(key) + "\n");
      }
    }
    return csv;
  }
}
