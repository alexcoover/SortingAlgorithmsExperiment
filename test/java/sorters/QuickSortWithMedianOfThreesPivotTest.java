package sorters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;

class QuickSortWithMedianOfThreesPivotTest {

  private static final class ExposeSelectPivotForMedianQuickSort<
          T extends Comparable<T>>
          extends QuickSortWithMedianOfThreesPivot<T> {
    public int exposeSelectPivot(
            final T[] array, final int leftIndex, final int rightIndex) {
      return selectPivot(array, leftIndex, rightIndex);
    }
  }

  @Test
  void quickSortWithMedianOfThreesPivot() {
    QuickSort<Integer> quickSorter = new QuickSortWithMedianOfThreesPivot<>();

    Integer[] nullArray = null;
    quickSorter.sort(nullArray);
    assertNull(nullArray);
    assertDoesNotThrow(() -> quickSorter.sort(nullArray));

    Integer[] emptyArray = new Integer[]{};
    quickSorter.sort(emptyArray);
    assertArrayEquals(new Integer[]{}, emptyArray);

    Integer[] arrayWithOneValue = new Integer[]{5};
    quickSorter.sort(arrayWithOneValue);
    assertArrayEquals(new Integer[]{5}, arrayWithOneValue);

    Integer[] arrayWithTwoValues = new Integer[]{5, 2};
    quickSorter.sort(arrayWithTwoValues);
    assertArrayEquals(new Integer[]{2, 5}, arrayWithTwoValues);

    Integer[] arrayWithOverTwoValues = new Integer[]{1, 0, 5, 2, -5, 8, 12, 3};
    quickSorter.sort(arrayWithOverTwoValues);
    assertArrayEquals(new Integer[]{-5, 0, 1, 2, 3, 5, 8, 12},
            arrayWithOverTwoValues);
  }

  @Test
  void selectPivot() {
    ExposeSelectPivotForMedianQuickSort<Integer> quickSorter =
            new ExposeSelectPivotForMedianQuickSort<>();

    assertEquals(0, quickSorter.exposeSelectPivot(new Integer[]{3, 5}, 0, 1));
    assertEquals(0, quickSorter.exposeSelectPivot(new Integer[]{5, 3}, 0, 1));

    assertEquals(0, quickSorter.exposeSelectPivot(
            new Integer[]{3, 5, 2}, 0, 2));
    assertEquals(1, quickSorter.exposeSelectPivot(
            new Integer[]{10, 6, 4}, 0, 2));
    assertEquals(2, quickSorter.exposeSelectPivot(
            new Integer[]{-5, 15, 5}, 0, 2));
    assertEquals(1, quickSorter.exposeSelectPivot(
            new Integer[]{7, 7, 7}, 0, 2));

    assertEquals(4, quickSorter.exposeSelectPivot(
            new Integer[]{1, 4, 2, 10, 6, 3, 42}, 2, 6));
  }
}
