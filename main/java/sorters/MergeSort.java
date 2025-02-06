package sorters;

/**
 * Implements merge sort with generic type array.
 *
 * @param <T> Data received in array.
 * @author Alex Coover.
 */

public class MergeSort<T extends Comparable<T>> implements SortingInterface<T> {
  /**
   * Checks for null array before calling merge sort.
   *
   * @param array The array to sort.
   */

  public void sort(T[] array) {
    if (array != null && array.length > 1) {
      // 0 is beginning index length - 1 is end index
      mergeSort(array, 0, array.length - 1);
    }
  }

  /**
   * Receives array to be sorted, start index and end index.
   * Recursively calls itself, splitting each array in half until
   * it no longer can, then it calls merge function.
   *
   * @param array Array to be sorted.
   * @param start Beginning index of array.
   * @param end   End index of array.
   */

  private void mergeSort(T[] array, int start, int end) {
    // Base case
    if (start >= end) {
      return;
    }

    // Find array midpoint
    int mid = (start + end) / 2;

    // Split first half then last half
    mergeSort(array, start, mid);
    mergeSort(array, mid + 1, end);

    // Merge halves together
    merge(array, start, mid, end);
  }

  /**
   * Merges two halves of an array while sorting them.
   *
   * @param array Array being merged.
   * @param start Beginning index.
   * @param mid   Middle index.
   * @param end   End index.
   */

  @SuppressWarnings("unchecked")
  private void merge(T[] array, int start, int mid, int end) {
    // Create two temporary arrays to store left and right halves
    T[] left = (T[]) new Comparable[mid - start + 1];
    T[] right = (T[]) new Comparable[end - mid];

    // Populate temp arrays with corresponding halves from incoming array
    System.arraycopy(array, start, left, 0, left.length);
    System.arraycopy(array, mid + 1, right, 0, right.length);

    // Init left and right index
    int leftIndex = 0;
    int rightIndex = 0;

    // Init current index
    int currentIndex = start;

    // Loop to sort and merge temp arrays into main array
    while (leftIndex < left.length && rightIndex < right.length) {
      // Compare left and right array values
      if (left[leftIndex].compareTo(right[rightIndex]) <= 0) {
        // If left index value is smaller or equal insert into main array
        array[currentIndex] = left[leftIndex];
        leftIndex++;
      } else {
        // Insert right index value into array
        array[currentIndex] = right[rightIndex];
        rightIndex++;
      }
      currentIndex++;
    }

    // Catch and insert any remaining values from left array
    while (leftIndex < left.length) {
      array[currentIndex++] = left[leftIndex++];
    }

    // Catch and insert any remaining values from right array
    while (rightIndex < right.length) {
      array[currentIndex++] = right[rightIndex++];
    }
  }
}
