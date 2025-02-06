package sorters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class InsertionSortTest {
  InsertionSort<Integer> insertionSorter = new InsertionSort<>();

  @Test
  void empty() {
    Integer[] emptyArray = new Integer[]{};
    insertionSorter.sort(emptyArray);
    assertArrayEquals(new Integer[]{}, emptyArray);
  }

  @Test
  void oneValue() {
    Integer[] arrayWithOneValue = new Integer[]{5};
    insertionSorter.sort(arrayWithOneValue);
    assertArrayEquals(new Integer[]{5}, arrayWithOneValue);
  }

  @Test
  void twoValues() {
    Integer[] arrayWithTwoValues = new Integer[]{5, 2};
    insertionSorter.sort(arrayWithTwoValues);
    assertArrayEquals(new Integer[]{2, 5}, arrayWithTwoValues);
  }

  @Test
  void overTwoValues() {
    Integer[] arrayWithOverTwoValues = new Integer[]{1, 0, 5, 2, -5, 8, 12, 3};
    insertionSorter.sort(arrayWithOverTwoValues);
    assertArrayEquals(new Integer[]{-5, 0, 1, 2, 3, 5, 8, 12},
            arrayWithOverTwoValues);

  }
}
