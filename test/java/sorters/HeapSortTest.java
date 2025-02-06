package sorters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {
  HeapSort<Integer> heapSorter = new HeapSort<>();

  @Test
  void sort() {
    Integer[] emptyArray = new Integer[]{};
    heapSorter.sort(emptyArray);
    assertArrayEquals(new Integer[]{}, emptyArray);

    Integer[] arrayWithOneValue = new Integer[]{5};
    heapSorter.sort(arrayWithOneValue);
    assertArrayEquals(new Integer[]{5}, arrayWithOneValue);

    Integer[] arrayWithTwoValues = new Integer[]{5, 2};
    heapSorter.sort(arrayWithTwoValues);
    assertArrayEquals(new Integer[]{2, 5}, arrayWithTwoValues);

    Integer[] arrayWithOverTwoValues = new Integer[]{1, 0, 5, 2, -5, 8, 12, 3};
    heapSorter.sort(arrayWithOverTwoValues);
    assertArrayEquals(new Integer[]{-5, 0, 1, 2, 3, 5, 8, 12},
            arrayWithOverTwoValues);

  }
}