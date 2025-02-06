package generators;

import java.util.Arrays;
import java.util.Random;

/**
 * Generates integer arrays, each with different sorted properties.
 *
 * @author Joel Yang
 */
public class ArrayGenerator {
  private static final int INT_MAX = 40001; // 0 <= arr[i] < INT_MAX
  private static final int ARRAY_EXP_MIN = 2; // min size of array: 2^2
  private static final int ARRAY_EXP_MAX = 15; // max size of array: 2^15
  private static final Random RANDOM = new Random();

  private final Integer[] arrayRandom; // unsorted array
  private final Integer[] arrayFullySorted; // sorted array, ascending
  private final Integer[] array75PercentSorted; // 75% sorted array
  private final Integer[] array50PercentSorted; // 50% sorted array
  private final Integer[] arrayReverseSorted; // sorted array, descending

  private final int arraySize;

  /**
   * Contains five arrays, all containing the same elements. The arrays are:
   * random, fully sorted ascending, 75% sorted, 50% sorted, and fully sorted
   * descending. The size of all arrays is 2^n, where n is between 2 and 15.
   *
   * @param exponent The size of the array will be 2^exponent.
   */
  public ArrayGenerator(int exponent) {
    // don't want exponents not between 2 and 15
    if (exponent > ARRAY_EXP_MAX || exponent < ARRAY_EXP_MIN) {
      throw new IllegalArgumentException("exponent must be between " + ARRAY_EXP_MIN
              + " and " + ARRAY_EXP_MAX + "!");
    }

    arraySize = (int) Math.pow(2.0, exponent);
    arrayRandom = generateRandomArray();
    arrayFullySorted = generateSortedArray(1.00);
    array75PercentSorted = generateSortedArray(0.75);
    array50PercentSorted = generateSortedArray(0.50);
    arrayReverseSorted = generateReverseSortedArray();
  }

  /**
   * Default getter for the random array.
   *
   * @return A random array of integers.
   */
  public Integer[] getArrayRandom() {
    return arrayRandom;
  }

  /**
   * Default getter for the fully sorted array.
   *
   * @return A fully sorted (ascending) array of integers.
   */
  public Integer[] getArrayFullySorted() {
    return arrayFullySorted;
  }

  /**
   * Default getter for the 75% sorted array.
   *
   * @return A 75% sorted (ascending) array of integers.
   */
  public Integer[] getArray75PercentSorted() {
    return array75PercentSorted;
  }

  /**
   * Default getter for the 50% sorted array.
   *
   * @return A 50% sorted (ascending) array of integers.
   */
  public Integer[] getArray50PercentSorted() {
    return array50PercentSorted;
  }

  /**
   * Default getter for the fully reverse sorted array.
   *
   * @return A fully sorted (descending) array of integers.
   */
  public Integer[] getArrayReverseSorted() {
    return arrayReverseSorted;
  }

  /**
   * Default getter for the array size for all arrays.
   *
   * @return The array size for all arrays.
   */
  public int getArraySize() {
    return arraySize;
  }

  /**
   * Generates an array of random integers from 0 to INT_MAX with size 2^exponent.
   *
   * @return The array of random integers.
   */
  private Integer[] generateRandomArray() {
    // size of the array is 2^exp
    Integer[] out = new Integer[arraySize];

    // elements are random
    for (int i = 0; i < arraySize; i++) {
      out[i] = RANDOM.nextInt(INT_MAX);
    }

    return out;
  }

  /**
   * Generates an array of integers with the same elements as all other arrays, sorting it from the
   * left up to a certain percentage sorted.
   *
   * @param percentSorted The percent of the array to be sorted, from the left.
   * @return An array sorted by a given percentage.
   */
  private Integer[] generateSortedArray(double percentSorted) {
    // don't want percentages that aren't between 0% and 100%
    if (percentSorted > 1.00 || percentSorted < 0.00) {
      throw new IllegalArgumentException("percent sorted must be between 0.00 and 1.00!");
    }

    Integer[] out = arrayRandom.clone();

    // sort percentSorted of the array. if 0%, nothing is sorted.
    int sortedIndices = (int) Math.round(percentSorted * arraySize);

    // if sortedIndices is, for example, 1.0, then this sorts the array
    // from index 0 to arraySize - 1. last arg is exclusive
    Arrays.sort(out, 0, sortedIndices);

    return out;
  }

  // simply reverse the sorted array we've already created

  /**
   * Generates an array of integers with the same elements as all other arrays, sorted
   * in descending order.
   *
   * @return An array of integers sorted in descending order.
   */
  private Integer[] generateReverseSortedArray() {
    Integer[] out = arrayFullySorted.clone();
    reverseArray(out);
    return out;
  }

  /** Reverses an array of integers, i.e. swaps index k with array length - 1 - k
   * up to array length / 2.
   *
   * @param arr The array to be reversed.
   */
  private void reverseArray(Integer[] arr) {
    for (int i = 0; i < arraySize / 2; i++) {
      int tmp = arr[i];
      arr[i] = arr[arraySize - 1 - i];
      arr[arraySize - 1 - i] = tmp;
    }
  }
}
