package sorters;

/**
 * Interface for sorting algorithms that sort arrays.
 *
 * @param <T> The data type of the array to sort.
 * @author Aune Mitchell
 */
public interface SortingInterface<T extends Comparable<T>> {

  /**
   * Sorts the array.
   *
   * @param array The array to sort.
   */
  void sort(T[] array);
}
