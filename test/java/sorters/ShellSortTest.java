package sorters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ShellSortTest {
  ShellSort<Integer> shellSorter = new ShellSort<>();

  @Test
  void emptyTest() {
    Integer[] emptyArray = new Integer[]{};
    shellSorter.sort(emptyArray);
    assertArrayEquals(new Integer[]{}, emptyArray);
  }

  @Test
  void oneValueTest() {
    Integer[] arrayWithOneValue = new Integer[]{5};
    shellSorter.sort(arrayWithOneValue);
    assertArrayEquals(new Integer[]{5}, arrayWithOneValue);
  }

  @Test
  void twoValueTest() {
    Integer[] arrayWithTwoValues = new Integer[]{5, 2};
    shellSorter.sort(arrayWithTwoValues);
    assertArrayEquals(new Integer[]{2, 5}, arrayWithTwoValues);
  }

  @Test
  void eightValueTest() {

    Integer[] arrayWithOverTwoValues = new Integer[]{1, 0, 5, 2, -5, 8, 12, 3};
    shellSorter.sort(arrayWithOverTwoValues);
    assertArrayEquals(new Integer[]{-5, 0, 1, 2, 3, 5, 8, 12},
            arrayWithOverTwoValues);
  }

  @Test
  void thousandValueTest() {
    int n = 1000;
    Integer[] largeArray = new Integer[n];
    for (int i = 0; i < n; i++) {
      largeArray[i] = (int) Math.round(Math.random() * n);
    }
    shellSorter.sort(largeArray);
    boolean sorted = true;
    for (int i = 0; i < n - 1; i++) {
      if (largeArray[i] > largeArray[i + 1]) {
        sorted = false;
        break;
      }
    }
    assert sorted;
  }
}
