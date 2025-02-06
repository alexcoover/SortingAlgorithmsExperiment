package generators;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ChartGeneratorTest {

  private static final String TEMP_DIR_FOR_IMAGES = "chart-gen-temp-image/";
  private static final String TEMP_DIR_FOR_CSVS = "chart-gen-temp-csv/";
  private static ChartGenerator chartGenerator;
  private static File validCsv;
  private static File badFormatCsv;
  private static File noDataCsv;
  private static File stringCsv;
  private static File testDirForCsv;

  @BeforeAll
  static void setUp() {
    chartGenerator = new ChartGenerator();

    // Create temp dir for CSVs
    testDirForCsv = new File(TEMP_DIR_FOR_CSVS);
    if (!testDirForCsv.exists()) {
      boolean madeTestDirForCsv = testDirForCsv.mkdir();
      if (!madeTestDirForCsv) {
        Assertions.fail("Unexpected exception when creating directory.");
      }
    }

    // Create a valid CSV for testing
    validCsv = new File(TEMP_DIR_FOR_CSVS + "junit-test-valid-csv.csv");
    try {
      FileWriter fileWriterForValidCsv = new FileWriter(validCsv);
      fileWriterForValidCsv.write("1,5\n");
      fileWriterForValidCsv.write("10,30");
      fileWriterForValidCsv.close();
    } catch (IOException e) {
      Assertions.fail("Unexpected exception when creating csv: " + e);
    }

    // Create a bad format CSV for testing
    badFormatCsv = new File(TEMP_DIR_FOR_CSVS
            + "junit-test-bad-format-csv.csv");
    try {
      FileWriter fileWriterForBadFormatCsv = new FileWriter(badFormatCsv);
      fileWriterForBadFormatCsv.write("1,2,3\n");
      fileWriterForBadFormatCsv.close();
    } catch (IOException e) {
      Assertions.fail("Unexpected exception when creating csv: " + e);
    }

    // Create a CSV with no data for testing
    noDataCsv = new File(TEMP_DIR_FOR_CSVS + "junit-test-no-data-csv.csv");
    try {
      FileWriter fileWriterForNoDataCsv = new FileWriter(noDataCsv);
      fileWriterForNoDataCsv.write("\n");
      fileWriterForNoDataCsv.close();
    } catch (IOException e) {
      Assertions.fail("Unexpected exception when creating csv: " + e);
    }

    // Create a CSV with String data for testing
    stringCsv = new File(TEMP_DIR_FOR_CSVS + "junit-test-string-csv.csv");
    try {
      FileWriter fileWriterForStringCsv = new FileWriter(stringCsv);
      fileWriterForStringCsv.write("a,b\n");
      fileWriterForStringCsv.close();
    } catch (IOException e) {
      Assertions.fail("Unexpected exception when creating csv: " + e);
    }
  }

  @AfterAll
  static void deleteTempFilesAndDirs() {
    // Delete temp files created for testing
    File testDirForImages = new File(TEMP_DIR_FOR_IMAGES);
    File[] testDirs = new File[]{testDirForImages, testDirForCsv};
    for (File testDir : testDirs) {
      File[] testFiles = testDir.listFiles();
      if (testFiles != null) {
        for (File testFile : testFiles) {
          boolean fileDeleted = testFile.delete();
          if (!fileDeleted) {
            System.out.println("Unable to delete test file. "
                    + "Please delete manually.");
          }
        }
      }
      boolean dirDeleted = testDir.delete();
      if (!dirDeleted) {
        System.out.println("Unable to delete test directory. "
                + "Please delete manually.");
      }
    }
  }

  @Test
  void createChartImageFromNonExistentCsv() {
    // Confirm IllegalArgumentException is thrown with null CSV
    assertThrows(IllegalArgumentException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    null, "imagePath.png", TEMP_DIR_FOR_IMAGES, "Chart Title"));

    // Confirm FileNotFoundException is thrown with nonexistent CSV
    File nonExistentFile = new File("invalid.csv");
    assertThrows(FileNotFoundException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    nonExistentFile, "imagePath.png",
                    TEMP_DIR_FOR_IMAGES, "Chart Title"));
  }

  @Test
  void createChartImageFromNoDataCsv() {
    // Confirm IllegalArgumentException is thrown
    // with CSV with no data
    assertThrows(IllegalArgumentException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    noDataCsv, "junit-test-chart.png",
                    TEMP_DIR_FOR_IMAGES, "Chart Title"));
  }

  @Test
  void createChartImageFromStringCsv() {
    // Confirm IllegalArgumentException is thrown
    // with CSV with no data
    assertThrows(IllegalArgumentException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    stringCsv, "junit-test-chart.png",
                    TEMP_DIR_FOR_IMAGES, "Chart Title"));
  }

  @Test
  void createChartImageFromBadFormatCsv() {
    // Confirm IllegalArgumentException is thrown
    // with CSV with multiple items per line
    assertThrows(IllegalArgumentException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    badFormatCsv, "junit-test-chart.png",
                    TEMP_DIR_FOR_IMAGES, "Chart Title"));
  }

  @Test
  void createChartImagesWithBadParentDirs() {
    String validImageFilePathString = "junit-test-chart-image-bad-parent.png";
    // Confirm IllegalArgument exceptions are thrown with valid CSV,
    // valid image path, and invalid parent directory
    assertThrows(IllegalArgumentException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    validCsv, validImageFilePathString,
                    "", "JUnit Test Chart"));
    assertThrows(IllegalArgumentException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    validCsv, validImageFilePathString,
                    null, "JUnit Test Chart"));
  }

  @Test
  void createChartImagesWithBadImagePath() {
    // Confirm IllegalArgumentException is thrown with null or empty file path
    assertThrows(IllegalArgumentException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    validCsv, null, TEMP_DIR_FOR_IMAGES, "Chart Title"));
    assertThrows(IllegalArgumentException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    validCsv, "", TEMP_DIR_FOR_IMAGES, "Chart Title"));

    // Confirm IOException is thrown with invalid image file path
    String invalidImagePath = "/fake/directory/read-only-image.png";
    assertThrows(IOException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    validCsv, invalidImagePath,
                    TEMP_DIR_FOR_IMAGES, "Chart Title"));
  }

  @Test
  void createChartImagesWithBadTitle() {
    // Confirm IllegalArgumentException is thrown with null or empty chart title
    assertThrows(IllegalArgumentException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    validCsv, "abc.png", TEMP_DIR_FOR_IMAGES, null));
    assertThrows(IllegalArgumentException.class,
            () -> chartGenerator.createChartImageFromCsv(
                    validCsv, "abc.png", TEMP_DIR_FOR_IMAGES, ""));
  }

  @Test
  void createChartImagesFromGoodParams() {
    String validImageFilePathString = "junit-test-chart-image.png";
    // Confirm image is generated successfully
    // with valid CSV and valid image path
    String fullImagePath = "";
    try {
      fullImagePath = chartGenerator.createChartImageFromCsv(
              validCsv, validImageFilePathString,
              TEMP_DIR_FOR_IMAGES, "JUnit Test Chart");
    } catch (IOException e) {
      Assertions.fail("Unexpected exception when generating image: " + e);
    }
    final Path validImagePath = Paths.get(fullImagePath);
    assertTrue(Files.exists(validImagePath));
  }
}
