package sorters;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Implements bucket sort with integer type array.
 *
 * @author Alex Coover.
 */

public class BucketSort implements SortingInterface<Integer> {
  /**
   * Checks for null or single element array before implementing primary method.
   *
   * @param array The array to sort.
   */
  public void sort(Integer[] array) {
    if (array != null && array.length > 1) {
      bucketSort(array);
    }
  }

  /**
   * Receives array and implements a bucket sort with the number of buckets
   * being the square root of the length of the array. The buckets are array lists
   * stored in the main buckets array. Index is calculated and numbers are crudely
   * sorted into their buckets, then the buckets are sorted and then returned to
   * the original array in order.
   *
   * @param array the array to sort.
   */

  private void bucketSort(Integer[] array) {
    // Array length
    int n = array.length;

    // Number of buckets
    int size = (int) Math.sqrt(n);

    // Max and min values found
    int minValue = array[0];
    int maxValue = array[0];
    for (int value : array) {
      if (value < minValue) {
        minValue = value;
      }
      if (value > maxValue) {
        maxValue = value;
      }
    }

    // Range calculated for future indexing
    int range = (maxValue - minValue) / size + 1;

    // Array of array lists created and populated with empty array lists
    @SuppressWarnings("unchecked")
    ArrayList<Integer>[] buckets = new ArrayList[size];
    for (int i = 0; i < size; i++) {
      buckets[i] = new ArrayList<>();
    }

    // All values from incoming array are sorted into their corresponding bucket
    for (int num : array) {
      int index = ((num - minValue) / range);
      buckets[index].add(num);
    }

    // Each bucket is sorted
    for (int i = 0; i < size; i++) {
      Collections.sort(buckets[i]);
    }

    // Sorted buckets are emptied in order to original array
    int index = 0;
    for (ArrayList<Integer> bucket : buckets) {
      for (Integer num : bucket) {
        array[index++] = num;
      }
    }
  }
}
