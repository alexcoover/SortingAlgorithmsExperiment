package generators;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayGeneratorTest {
  ArrayGenerator gen;
  int n;

  @org.junit.jupiter.api.Test
  void generateArray() {
    gen = new ArrayGenerator(15);
    n = gen.getArraySize();

    Integer[] arrayRandom = gen.getArrayRandom();
    Integer[] arrayFullySorted = gen.getArrayFullySorted();
    Integer[] array75PercentSorted = gen.getArray75PercentSorted();
    Integer[] array50PercentSorted = gen.getArray50PercentSorted();
    Integer[] arrayReverseSorted = gen.getArrayReverseSorted();

    // check for correct size
    assertEquals(gen.getArraySize(), 32768);

    // check other sizes
    assertEquals(new ArrayGenerator(2).getArraySize(), 4);
    assertEquals(new ArrayGenerator(3).getArraySize(), 8);
    assertEquals(new ArrayGenerator(4).getArraySize(), 16);
    assertEquals(new ArrayGenerator(5).getArraySize(), 32);
    assertEquals(new ArrayGenerator(6).getArraySize(), 64);
    assertEquals(new ArrayGenerator(7).getArraySize(), 128);
    assertEquals(new ArrayGenerator(8).getArraySize(), 256);
    assertEquals(new ArrayGenerator(9).getArraySize(), 512);
    assertEquals(new ArrayGenerator(10).getArraySize(), 1024);
    assertEquals(new ArrayGenerator(11).getArraySize(), 2048);
    assertEquals(new ArrayGenerator(12).getArraySize(), 4096);
    assertEquals(new ArrayGenerator(13).getArraySize(), 8192);
    assertEquals(new ArrayGenerator(14).getArraySize(), 16384);

    // assert that creating an generators.ArrayGenerator with an exponent out of range throws
    // an IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> new ArrayGenerator(1));
    assertThrows(IllegalArgumentException.class, () -> new ArrayGenerator(16));

    // check for correct range of integers
    for (int i = 0; i < n; i++) {
      assertTrue(arrayRandom[i] >= 0 && arrayRandom[i] <= 40000);
    }

    // reverse ordered array
    for (int i = 1; i < n; i++) {
      assertTrue(arrayReverseSorted[i - 1] >= arrayReverseSorted[i]);
    }

    // ordered array
    for (int i = 1; i < n; i++) {
      assertTrue(arrayFullySorted[i - 1] <= arrayFullySorted[i]);
    }

    // 50% ordered array
    int sortedEndIndex = (int) Math.round(n * 0.5);
    Integer[] array50PercentSortedByArraysClass = Arrays.copyOf(arrayRandom, n);
    Arrays.sort(array50PercentSortedByArraysClass, 0, sortedEndIndex);
    assertArrayEquals(array50PercentSortedByArraysClass, array50PercentSorted);

    // 75% ordered array
    sortedEndIndex = (int) Math.round(n * 0.75);
    Integer[] array75PercentSortedByArraysClass = Arrays.copyOf(arrayRandom, n);
    Arrays.sort(array75PercentSortedByArraysClass, 0, sortedEndIndex);
    assertArrayEquals(array75PercentSortedByArraysClass, array75PercentSorted);
  }
}