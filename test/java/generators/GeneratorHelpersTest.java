package generators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GeneratorHelpersTest {

  private static GeneratorHelpers generatorHelpers;

  @BeforeAll
  static void setUp() {
    generatorHelpers = new GeneratorHelpers();
  }

  @Test
  void validateDirectory() {
    // Confirm directory is created and slash is added if missing
    String dirPathNoSlash = "";
    try {
      dirPathNoSlash = generatorHelpers.validateDirectory(
              "test-generator-helper");
    } catch (IOException e) {
      Assertions.fail("Unexpected failure to create directory.");
    }
    assertEquals("test-generator-helper/", dirPathNoSlash);
    File dirFileNoSlash = new File(dirPathNoSlash);
    assertTrue(Files.exists(dirFileNoSlash.toPath()));

    // Confirm IllegalArgumentException is thrown
    // when passing an existing file as a directory
    File file = new File("test-generator-helper-file.txt");
    try {
      boolean createdFile = file.createNewFile();
      if (!createdFile) {
        Assertions.fail("Unexpected failure to create file.");
      }
    } catch (IOException e) {
      Assertions.fail("Unexpected failure to create file.");
    }
    assertThrows(IllegalArgumentException.class,
            () -> generatorHelpers.validateDirectory(file.getPath()));

    // Confirm directory is created with normal directory
    String dirPath = "";
    try {
      dirPath = generatorHelpers.validateDirectory(
              "test-generator-helper-valid/");
    } catch (IOException e) {
      Assertions.fail("Unexpected failure to create directory.");
    }
    assertEquals("test-generator-helper-valid/", dirPath);
    File dirFile = new File(dirPath);
    assertTrue(Files.exists(dirFile.toPath()));

    File[] files = new File[]{dirFile, dirFileNoSlash, file};
    // Delete temp files
    for (File tempFileOrDir : files) {
      try {
        Files.delete(tempFileOrDir.toPath());
      } catch (IOException e) {
        System.out.println("Unable to delete test file or directory. "
                + "Please delete manually.");
      }
    }
  }

  @Test
  void getDateTimeString() {
    // Confirm date time String matches expected format
    String dateTimeString = generatorHelpers.getDateTimeString();
    String dateTimePattern = "\\d{4}-\\d{2}-\\d{2}-\\d{2}-\\d{2}-\\d{2}";
    assertTrue(Pattern.matches(dateTimePattern, dateTimeString));
  }

  @Test
  void formatFileName() {
    // Confirm undesired characters are removed
    String before = "abc123./-!@#$%^&*";
    assertEquals("abc123./-", generatorHelpers.formatFileName(before));
  }
}
