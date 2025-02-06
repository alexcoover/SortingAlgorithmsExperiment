package sorters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BucketSortTest {
  BucketSort bucketSorter = new BucketSort();
  @Test
  void sort() {
    bucketSorter.sort(null);

    Integer[] empty = new Integer[]{};
    bucketSorter.sort(empty);
    assertArrayEquals(new Integer[]{}, empty);

    Integer[] one = new Integer[]{1};
    bucketSorter.sort(one);
    assertArrayEquals(new Integer[]{1}, one);

    Integer[] two = new Integer[]{2, 1};
    bucketSorter.sort(two);
    assertArrayEquals(new Integer[]{1, 2}, two);

    Integer[] three = new Integer[]{0, 2, 1};
    bucketSorter.sort(three);
    assertArrayEquals(new Integer[]{0, 1, 2}, three);

    Integer[] four = new Integer[]{12, 0, 444, 55555};
    bucketSorter.sort(four);
    assertArrayEquals(new Integer[]{0, 12, 444, 55555}, four);

    Integer[] five = new Integer[]{333, 12000, 2, 999, 40};
    bucketSorter.sort(five);
    assertArrayEquals(new Integer[]{2, 40, 333, 999, 12000}, five);

    Integer[] six = new Integer[]{40000, 0, 999, 12345, 987, 123};
    bucketSorter.sort(six);
    assertArrayEquals(new Integer[]{0, 123, 987, 999, 12345, 40000}, six);

    Integer[] ten = new Integer[]{40000, 0, 999, 12345, 987, 123, 777, 7777, 4321, 1};
    bucketSorter.sort(ten);
    assertArrayEquals(new Integer[]{0, 1, 123, 777, 987, 999, 4321, 7777, 12345, 40000}, ten);
    
    }
}
