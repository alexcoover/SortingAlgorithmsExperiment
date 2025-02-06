package generators;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Generates PNG files of charts from CSV datasets using JFreeChart.
 *
 * @author Aune Mitchell
 */
public class ChartGenerator {

  /** Width for the image in pixels. */
  private static final int IMAGE_WIDTH = 600;
  /** Height for the image in pixels. */
  private static final int IMAGE_HEIGHT = 400;
  /** Color for the line in the chart. */
  private static final Color CHART_LINE_COLOR = Color.BLACK;
  /** Color for the background of the chart. */
  private static final Color CHART_BACKGROUND_COLOR = Color.WHITE;
  /** Label for x axis. */
  private static final String X_AXIS_LABEL = "Array Size";
  /** Label for y axis. */
  private static final String Y_AXIS_LABEL = "Runtimes (Microseconds)";

  /**
   * Creates the chart image from the CSV.
   *
   * @param csvFile The CSV file object.
   * @param fileNameForImage The file name for the generated image.
   * @param parentDirForImages The parent directory for the generated image.
   * @param chartTitle The title of the chart.
   * @return The full path for the generated image with date-time marker.
   * @throws FileNotFoundException If the CSV file is not found.
   * @throws IOException If the image file or directory cannot be created.
   */
  public String createChartImageFromCsv(
          final File csvFile,
          final String fileNameForImage,
          final String parentDirForImages,
          final String chartTitle) throws FileNotFoundException, IOException {

    // Throw exception if any parameter is null
    if (csvFile == null || fileNameForImage == null
            || parentDirForImages == null || chartTitle == null) {
      throw new IllegalArgumentException("Parameters cannot be null.");
    }

    // Throw exception if any String parameter is empty
    if (fileNameForImage.isEmpty()
            || parentDirForImages.isEmpty()
            || chartTitle.isEmpty()) {
      throw new IllegalArgumentException(
              "Directory or title strings cannot be empty.");
    }

    XYSeriesCollection chartData = createDatasetFromFile(csvFile);
    return createChart(chartData,
            fileNameForImage,
            parentDirForImages,
            chartTitle);
  }

  /**
   * Creates a XYSeriesCollection dataset from a CSV.
   * Runtime complexity: O(N), N is the number of lines in the CSV.
   *
   * @param csvFile The CSV file object.
   * @return The XYSeriesCollection generated using the CSV data.
   * @throws FileNotFoundException If the CSV file is not found.
   */
  private XYSeriesCollection createDatasetFromFile(
          final File csvFile) throws FileNotFoundException {
    // Create the XYSeries object to add the data to
    XYSeries xySeries = new XYSeries("Runtimes (Microseconds)");

    // Reads CSV using Scanner
    Scanner fileScanner = new Scanner(csvFile);
    while (fileScanner.hasNextLine()) {
      // Read each line, stripped of whitespace
      String line = fileScanner.nextLine().strip();
      // If the line is not empty, pull the x and y values
      if (!line.isEmpty()) {
        String[] xyValues = line.split(",");
        // If there are more than 3 values on the line
        // the required format is not followed,
        // so throw exception
        if (xyValues.length != 2) {
          throw new IllegalArgumentException(
                  "CSV needs to contain x,y values (comma delimited), "
                          + "with each x,y value being a number "
                          + "on its own line.");
        }

        try {
          // Pull x and y values from the xyValues array
          double x = Double.parseDouble(xyValues[0].trim());
          double y = Double.parseDouble(xyValues[1].trim());
          xySeries.add(x, y);
        } catch (NumberFormatException e) {
          // Throw exception if the x,y values are not numbers
          throw new IllegalArgumentException(
                  "x,y values in CSV must be numbers.");
        }
      }
    }
    fileScanner.close();

    // Throw IllegalArgumentException if there's no data in the CSV
    if (xySeries.isEmpty()) {
      throw new IllegalArgumentException("CSV has no valid data.");
    }

    // Create XYSeriesCollection and add the xySeries to it
    XYSeriesCollection xyDataSet = new XYSeriesCollection();
    xyDataSet.addSeries(xySeries);

    return xyDataSet;
  }

  /**
   * Creates the chart PNG.
   *
   * @param xyDataSet The XYSeriesCollection dataset.
   * @param fileNameForImage The file name for the generated image.
   * @param parentDirForImages The parent directory for the generated image.
   * @param chartTitle The title of the chart.
   * @return The path for the generated image with date-time marker.
   * @throws IOException If the image file or directory cannot be created.
   */
  private String createChart(
          final XYSeriesCollection xyDataSet,
          final String fileNameForImage,
          final String parentDirForImages,
          final String chartTitle) throws IOException {

    // Create the chart using the provided title, labels, and dataset
    JFreeChart chart = ChartFactory.createXYLineChart(
            chartTitle,
            X_AXIS_LABEL,
            Y_AXIS_LABEL,
            xyDataSet,
            PlotOrientation.VERTICAL,
            false, false, false);

    // Make the x,y points and the line visible on the chart
    // and set the line color
    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
    renderer.setSeriesShapesVisible(0, true);
    renderer.setSeriesLinesVisible(0, true);
    renderer.setSeriesPaint(0, CHART_LINE_COLOR);

    // Set the background color and add the renderer settings to the plot
    XYPlot xyPlot = chart.getXYPlot();
    xyPlot.setBackgroundPaint(CHART_BACKGROUND_COLOR);
    xyPlot.setRenderer(renderer);

    // Create a file using the full path for the generated image
    String fullPathForImage = createFullPathForImage(
            fileNameForImage,
            parentDirForImages);
    File generatedImage = new File(fullPathForImage);

    // Save the chart as a PNG
    ChartUtilities.saveChartAsPNG(
            generatedImage,
            chart,
            IMAGE_WIDTH,
            IMAGE_HEIGHT);

    return generatedImage.getPath();
  }

  /**
   * Creates the full path for the generated image.
   *
   * @param fileNameForImage The file name for the generated image.
   * @param parentDirForImages The parent directory for the generated image.
   * @return The full path for the generated image with date-time marker.
   * @throws IOException If the directory couldn't be created
   *                     or already exists as a file.
   */
  private String createFullPathForImage(final String fileNameForImage,
                                        final String parentDirForImages)
          throws IOException {

    GeneratorHelpers generatorHelpers = new GeneratorHelpers();
    String pathPrefix = generatorHelpers.validateDirectory(parentDirForImages);

    // Format the file name
    String fileName = generatorHelpers.formatFileName(fileNameForImage);

    // Adds timestamp to filename
    String dateTimePrefix = generatorHelpers.getDateTimeString() + "_";

    // Return full path for image
    return pathPrefix + dateTimePrefix + fileName;
  }
}
