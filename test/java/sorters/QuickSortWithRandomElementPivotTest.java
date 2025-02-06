package sorters;

import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuickSortWithRandomElementPivotTest {

  private static final class ExposeSelectPivotForRandomElementQuickSort<
          T extends Comparable<T>>
          extends QuickSortWithRandomElementPivot<T> {
    public int exposeSelectPivot(
            final T[] array, final int leftIndex, final int rightIndex) {
      return selectPivot(array, leftIndex, rightIndex);
    }
  }

  @Test
  void quickSortWithRandomElementPivot() {
    QuickSort<Integer> quickSorter = new QuickSortWithRandomElementPivot<>();

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
    assertArrayEquals(
            new Integer[]{-5, 0, 1, 2, 3, 5, 8, 12}, arrayWithOverTwoValues);
  }

  @Test
  void selectPivot() {
    ExposeSelectPivotForRandomElementQuickSort<Integer> quickSorter =
            new ExposeSelectPivotForRandomElementQuickSort<>();

    // Create an array of size 100,000 and fill with integers from 0 through 9
    Integer[] array = new Integer[100000];
    Random random = new Random();
    for (int i = 0; i < array.length; i++) {
      array[i] = random.nextInt(10);
    }

    // Pick a pivot randomly from the array 100,000 times
    ArrayList<Integer> results = new ArrayList<>();
    for (int i = 0; i < array.length; i++) {
      results.add(array[quickSorter.exposeSelectPivot(
              array, 0, array.length - 1)]);
    }

    // Verify that the integers 0 through 10
    // are contained in the results,
    // to demonstrate randomness of selection,
    // as the odds of this not occurring are near zero
    for (int i = 0; i < 10; i++) {
      assertTrue(results.contains(i));
    }
  }
}
