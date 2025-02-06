package sorters;

/**
 * Implements the <a href="https://en.wikipedia.org/wiki/Heapsort">Heap Sort</a> algorithm.
 *
 * @param <T> The data type used in the array.
 * @author Joel Yang
 */
public class HeapSort<T extends Comparable<T>> implements SortingInterface<T> {

  /**
   * Sorts the array with Heap Sort given that the array exists and has more than one element.
   *
   * @param array The array to be sorted.
   */
  public void sort(final T[] array) {
    if (array != null && array.length > 1) {
      heapSort(array);
    }
  }

  /**
   * Sorts the array using Heap Sort. Max-heapifies the array, then sorts it.
   *
   * @param array The array to be sorted.
   */
  private void heapSort(final T[] array) {
    int n = array.length;

    // Build a max heap
    maxHeapify(array, n);

    // Sort by replacing the first item with the last in heap,
    // remove the new last item, then heapify the new first item.
    for (int i = n - 1; i > 0; i--) {
      swapTwoArrayElements(array, i, 0);

      maxHeapify(array, i, 0);
    }
  }

  /**
   * Max-heapifies an array such that its indices represent the level order
   * traversal of a binary tree.
   *
   * @param array The array to be max-heapified.
   */
  private void maxHeapify(final T[] array, int n) {

    // Heapify each subtree
    for (int i = n / 2 - 1; i > -1; i--) {
      maxHeapify(array, n, i);
    }
  }

  /**
   * "Max-heapifies" a subtree in the form of an array, in which
   * the array's indices represent the level order traversal of a binary tree.
   *
   * @param array The array containing the subtree.
   * @param n The length of the array.
   * @param parentIndex The parent index of the subtree to be max-heapified.
   */
  private void maxHeapify(final T[] array, int n, int parentIndex) {

    // Left and right child indices of the current index given that our array
    // indices represent the level order traversal of a binary tree.
    int leftIndex = (2 * parentIndex) + 1;
    int rightIndex = (2 * parentIndex) + 2;

    // If the left child exists in the array, and its value is comparatively greater
    // than its parent value...
    if (n > leftIndex && array[parentIndex].compareTo(array[leftIndex]) < 0) {
      // Swap the left child with its parent
      swapTwoArrayElements(array, leftIndex, parentIndex);

      // Max heapify the new left child
      maxHeapify(array, n, leftIndex);
    }

    // Do the above for the right child
    if (n > rightIndex && array[parentIndex].compareTo(array[rightIndex]) < 0) {
      swapTwoArrayElements(array, rightIndex, parentIndex);

      maxHeapify(array, n, rightIndex);
    }
  }

  /**
   * Swaps two elements in an array by their indices.
   *
   * @param array       The array.
   * @param firstIndex  The first index.
   * @param secondIndex The second index.
   */
  private void swapTwoArrayElements(final T[] array, int firstIndex, int secondIndex) {
    T temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
  }
}