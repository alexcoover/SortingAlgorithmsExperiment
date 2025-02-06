package generators;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helper class for reports package.
 *
 * @author Aune Mitchell
 */
class GeneratorHelpers {

  /**
   * Formats the directory to end in a slash.
   * Confirms existence of the provided directory.
   * Creates the directory if it doesn't exist.
   *
   * @param parentDir The path for the directory.
   * @return The path for the directory.
   * @throws IOException If the directory couldn't be created
   *                     or already exists as a file.
   */
  protected String validateDirectory(final String parentDir)
          throws IOException {
    String formattedDir = parentDir;
    // If the parent directory provided doesn't end in a slash, add it
    if (!parentDir.endsWith("/")) {
      formattedDir += "/";
    }

    File directoryFile = new File(formattedDir);

    // If the file doesn't exist
    if (!directoryFile.exists()) {
      // Attempts to create the directory
      boolean madeDir = directoryFile.mkdirs();
      // If directory creation fails, throw IOException
      if (!madeDir) {
        throw new IOException("Unable to create parent directory.");
      }
    } else if (!directoryFile.isDirectory()) {
      // Throw exception if the file exists but is not a directory
      throw new IllegalArgumentException(
              "A file was passed instead of a directory.");
    }

    // Return the formatted directory
    return formattedDir;
  }

  /**
   * Gets the local date time as a String.
   *
   * @return The local date time as a String.
   */
  protected String getDateTimeString() {
    // Create local date time formatted String
    LocalDateTime localDateTime = LocalDateTime.now();
    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd-HH-mm-ss");
    return localDateTime.format(dateTimeFormat);
  }

  /**
   * Removes undesired characters from the file name.
   * Undesired characters are characters that are not
   * alphanumeric, periods, slashes, underscores, or dashes.
   *
   * @param fileName Original file name.
   * @return File name with non-alphanumeric or non-period characters removed.
   */
  protected String formatFileName(final String fileName) {
    return fileName.replaceAll("[^a-zA-Z0-9._/\\-]", "");
  }
}
