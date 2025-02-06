package sorters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CountingSortTest {
  CountingSort countingSorter = new CountingSort();

  @Test
  void emptyTest() {
    Integer[] emptyArray = new Integer[]{};
    countingSorter.sort(emptyArray);
    assertArrayEquals(new Integer[]{}, emptyArray);
  }

  @Test
  void oneValueTest() {
    Integer[] arrayWithOneValue = new Integer[]{5};
    countingSorter.sort(arrayWithOneValue);
    assertArrayEquals(new Integer[]{5}, arrayWithOneValue);
  }

  @Test
  void twoValueTest() {
    Integer[] arrayWithTwoValues = new Integer[]{5, 2};
    countingSorter.sort(arrayWithTwoValues);
    assertArrayEquals(new Integer[]{2, 5}, arrayWithTwoValues);
  }

  @Test
  void maxValueGreaterThanLengthTest() {

    Integer[] arrayMaxGreaterLength = new Integer[]{1, 0, 5, 2, 6, 8, 12, 3};
    countingSorter.sort(arrayMaxGreaterLength);
    assertArrayEquals(new Integer[]{0, 1, 2, 3, 5, 6, 8, 12},
            arrayMaxGreaterLength);
  }

  @Test
  void maxValueLessThanLengthTest() {
    Integer[] arrayMaxLessLength = new Integer[]{1, 0, 5, 2, 6, 6, 4, 2};
    countingSorter.sort(arrayMaxLessLength);
    assertArrayEquals(new Integer[]{0, 1, 2, 2, 4, 5, 6, 6},
            arrayMaxLessLength);
  }

  @Test
  void negativeTest() {
    Integer[] arrayWithNegativeValues = new Integer[]{1, 2, 3, -4};
    boolean exception = false;
    try {
      countingSorter.sort(arrayWithNegativeValues);
    } catch (IllegalArgumentException e) {
      exception = true;
    }
    assert (exception);
  }

  @Test
  void thousandValueTest() {
    int n = 1000;
    Integer[] largeArray = new Integer[n];
    for (int i = 0; i < n; i++) {
      largeArray[i] = (int) Math.round(Math.random() * n);
    }
    countingSorter.sort(largeArray);
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