package sorters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;

class QuickSortWithFirstElementPivotTest {

  private static final class ExposeSelectPivotForFirstElementQuickSort<
          T extends Comparable<T>>
          extends QuickSortWithFirstElementPivot<T> {
    public int exposeSelectPivot(
            final T[] array, final int leftIndex, final int rightIndex) {
      return selectPivot(array, leftIndex, rightIndex);
    }
  }

  @Test
  void quickSortWithFirstElementPivot() {
    QuickSort<Integer> quickSorter = new QuickSortWithFirstElementPivot<>();

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
    ExposeSelectPivotForFirstElementQuickSort<Integer> quickSorter =
            new ExposeSelectPivotForFirstElementQuickSort<>();
    assertEquals(0, quickSorter.exposeSelectPivot(
            new Integer[]{1, 2, 3}, 0, 2));
    assertEquals(2, quickSorter.exposeSelectPivot(
            new Integer[]{1, 2, 3, 4, 5}, 2, 4));
  }
}
