package generators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ReportGeneratorTest {

  private static final String PARENT_DIR_FOR_CSVS = "report-gen-csv/";
  private static ReportGenerator reportGenerator;
  private static HashMap<Integer, Double> goodHashMap;

  @BeforeAll
  static void setUp() {
    // Create report generator for testing
    reportGenerator = new ReportGenerator();

    // Create good HashMap for testing
    goodHashMap = new HashMap<>();
    for (int exponent = 2; exponent <= 15; exponent++) {
      goodHashMap.put((int) Math.pow(2, exponent), (double) exponent);
    }
  }

  @AfterAll
  static void deleteTempFilesAndDirs() {
    // Delete temp files created for testing
    File testDir = new File(PARENT_DIR_FOR_CSVS);
    File[] testFiles = testDir.listFiles();
    if (testFiles != null) {
      for (File testFile : testFiles) {
        boolean fileDeleted = testFile.delete();
        if (!fileDeleted) {
          System.out.println("Unable to delete test file."
                  + "Please delete manually.");
        }
      }
    }
    boolean dirDeleted = testDir.delete();
    if (!dirDeleted) {
      System.out.println("Unable to delete test directory."
              + "Please delete manually.");
    }
  }

  @Test
  void generateReportWithInvalidHashMap() {
    // Confirm null or empty HashMap throws exception
    assertThrows(IllegalArgumentException.class,
            () -> reportGenerator.generateReport(
                    null,
                    "test.csv",
                    PARENT_DIR_FOR_CSVS));
    HashMap<Integer, Double> emptyHashMap = new HashMap<>();
    assertThrows(IllegalArgumentException.class,
            () -> reportGenerator.generateReport(
                    emptyHashMap,
                    "test.csv",
                    PARENT_DIR_FOR_CSVS));

    // Confirm too small or too large HashMap throws exception
    // (size must be 14)
    HashMap<Integer, Double> tooSmallHashMap = new HashMap<>();
    tooSmallHashMap.put(1, 23.0);
    assertThrows(IllegalArgumentException.class,
            () -> reportGenerator.generateReport(
                    tooSmallHashMap,
                    "test.csv",
                    PARENT_DIR_FOR_CSVS));
    HashMap<Integer, Double> tooLargeHashMap = new HashMap<>();
    for (int i = 0; i < 20; i++) {
      tooLargeHashMap.put(i, (double) i);
    }
    assertThrows(IllegalArgumentException.class,
            () -> reportGenerator.generateReport(
                    tooLargeHashMap,
                    "test.csv",
                    PARENT_DIR_FOR_CSVS));

    // Confirm HashMap missing array size throws exception
    HashMap<Integer, Double> missingValueHashMap = new HashMap<>();
    for (int exponent = 2; exponent <= 14; exponent++) {
      missingValueHashMap.put((int) Math.pow(2, exponent), 5.0);
    }
    missingValueHashMap.put(234, 5.0);
    assertThrows(IllegalArgumentException.class,
            () -> reportGenerator.generateReport(
                    missingValueHashMap,
                    "test.csv",
                    PARENT_DIR_FOR_CSVS));
  }

  @Test
  void generateReportWithInvalidFileOrDirNames() {
    // Confirm null or empty file or directory name throws exception
    assertThrows(IllegalArgumentException.class,
            () -> reportGenerator.generateReport(
                    goodHashMap,
                    null,
                    PARENT_DIR_FOR_CSVS));
    assertThrows(IllegalArgumentException.class,
            () -> reportGenerator.generateReport(
                    goodHashMap,
                    "",
                    PARENT_DIR_FOR_CSVS));
    assertThrows(IllegalArgumentException.class,
            () -> reportGenerator.generateReport(
                    goodHashMap,
                    "test.csv",
                    null));
    assertThrows(IllegalArgumentException.class,
            () -> reportGenerator.generateReport(
                    goodHashMap,
                    "test.csv",
                    ""));
  }

  @Test
  void generateReportWithValidNames() {
    // Confirm report is generated successfully
    File csvFileNameWithExtension = null;
    try {
      csvFileNameWithExtension = reportGenerator.generateReport(
              goodHashMap,
              "test-with-extension.csv",
              PARENT_DIR_FOR_CSVS);
    } catch (IOException e) {
      Assertions.fail("Unexpected IOException when creating CSV.");
    }
    assertTrue(Files.exists(csvFileNameWithExtension.toPath()));

    // Confirm report is generated successfully with name missing .csv extension
    File csvFileNameNoExtension = null;
    try {
      csvFileNameNoExtension = reportGenerator.generateReport(
              goodHashMap,
              "test-without-extension",
              PARENT_DIR_FOR_CSVS);
    } catch (IOException e) {
      Assertions.fail("Unexpected IOException when creating CSV.");
    }
    assertTrue(Files.exists(csvFileNameNoExtension.toPath()));
  }

  @Test
  void checkFileContents() {
    // Generate file successfully
    File csv = null;
    try {
      csv = reportGenerator.generateReport(
              goodHashMap,
              "test.csv",
              PARENT_DIR_FOR_CSVS);
    } catch (IOException e) {
      Assertions.fail("Unexpected IOException when creating CSV.");
    }
    assertTrue(Files.exists(csv.toPath()));
    ArrayList<String> fileContents = new ArrayList<>();

    // Read generated file
    try (Scanner scanner = new Scanner(csv)) {
      while (scanner.hasNextLine()) {
        fileContents.add(scanner.nextLine());
      }
    } catch (FileNotFoundException e) {
      Assertions.fail("Unexpected file not found when reading CSV contents.");
    }

    // Confirm data points are in CSV in expected format
    ArrayList<String> expected = new ArrayList<>();
    for (int i = 2; i <= 15; i++) {
      expected.add((int) Math.pow(2, i) + "," + (double) i);
    }
    for (String line : expected) {
      assertTrue(fileContents.contains(line));
    }
  }
}
