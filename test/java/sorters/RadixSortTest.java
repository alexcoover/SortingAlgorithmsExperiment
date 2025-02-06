package sorters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RadixSortTest {
  RadixSort radixSorter = new RadixSort();

  @Test
  void sort() {
    Integer[] emptyArray = new Integer[]{};
    radixSorter.sort(emptyArray);
    assertArrayEquals(new Integer[]{}, emptyArray);

    Integer[] arrayWithOneValue = new Integer[]{5};
    radixSorter.sort(arrayWithOneValue);
    assertArrayEquals(new Integer[]{5}, arrayWithOneValue);

    Integer[] arrayWithTwoValues = new Integer[]{5, 2};
    radixSorter.sort(arrayWithTwoValues);
    assertArrayEquals(new Integer[]{2, 5}, arrayWithTwoValues);

    Integer[] arrayWithOverTwoValues = new Integer[]{1, 0, 5, 2, 6, 8, 12, 3};
    radixSorter.sort(arrayWithOverTwoValues);
    assertArrayEquals(new Integer[]{0, 1, 2, 3, 5, 6, 8, 12},
            arrayWithOverTwoValues);

    Integer[] arrayWithValuesOfSevenDigits = new Integer[]{94810142, 46331192, 18344839, 28675982,
            1, 0, 5, 2, 6, 8, 12, 3};
    radixSorter.sort(arrayWithValuesOfSevenDigits);
    assertArrayEquals(new Integer[]{0, 1, 2, 3, 5, 6, 8, 12,
                    18344839, 28675982, 46331192, 94810142},
            arrayWithValuesOfSevenDigits);

    Integer[] arrayWithNegativeIntegers = new Integer[]{1, 0, -5, 2, 6, 8, 12, 3};
    assertThrows(IllegalArgumentException.class, () -> radixSorter.sort(arrayWithNegativeIntegers));
  }
}