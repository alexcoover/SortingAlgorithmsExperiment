package sorters;

import java.util.Random;

/**
 * Implements Quick Sort using a random element for pivot selection.
 *
 * @param <T> The data type for the array.
 * @author Aune Mitchell
 */
public class QuickSortWithRandomElementPivot<
        T extends Comparable<T>>
        extends QuickSort<T> {

  /**
   * Random object used to generate the random pivot index.
   */
  private static final Random RANDOM = new Random();

  /**
   * Selects the pivot randomly.
   *
   * @param array      The array to sort.
   * @param leftIndex  The left index of the array.
   * @param rightIndex The right index of the array.
   * @return The randomly selected pivot index.
   */
  @Override
  protected int selectPivot(
          final T[] array, final int leftIndex, final int rightIndex) {
    // Generate a random number between the left and right indexes
    return RANDOM.nextInt(rightIndex - leftIndex + 1) + leftIndex;
  }
}
