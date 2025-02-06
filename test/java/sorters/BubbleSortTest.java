package sorters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {
  BubbleSort<Integer> bubbleSorter = new BubbleSort<>();

  @Test
  void sort() {
    Integer[] emptyArray = new Integer[]{};
    bubbleSorter.sort(emptyArray);
    assertArrayEquals(new Integer[]{}, emptyArray);

    Integer[] arrayWithOneValue = new Integer[]{5};
    bubbleSorter.sort(arrayWithOneValue);
    assertArrayEquals(new Integer[]{5}, arrayWithOneValue);

    Integer[] arrayWithTwoValues = new Integer[]{5, 2};
    bubbleSorter.sort(arrayWithTwoValues);
    assertArrayEquals(new Integer[]{2, 5}, arrayWithTwoValues);

    Integer[] arrayWithOverTwoValues = new Integer[]{1, 0, 5, 2, -5, 8, 12, 3};
    bubbleSorter.sort(arrayWithOverTwoValues);
    assertArrayEquals(new Integer[]{-5, 0, 1, 2, 3, 5, 8, 12},
            arrayWithOverTwoValues);

  }
}