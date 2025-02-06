package sorters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectionSortTest {

    @Test
    void sort() {
        SelectionSort<Integer> sorter = new SelectionSort<>();

        Integer[] nullArray = null;
        sorter.sort(nullArray);
        assertNull(nullArray);

        Integer[] emptyArray = new Integer[]{};
        sorter.sort(emptyArray);
        assertArrayEquals(new Integer[]{}, emptyArray);

        Integer[] oneArray = new Integer[]{1};
        sorter.sort(oneArray);
        assertArrayEquals(new Integer[]{1}, oneArray);

        Integer[] twoArray = new Integer[]{4, 1};
        sorter.sort(twoArray);
        assertArrayEquals(new Integer[]{1, 4}, twoArray);

        Integer[] threeArray = new Integer[]{2, 4, 1};
        sorter.sort(threeArray);
        assertArrayEquals(new Integer[]{1, 2, 4}, threeArray);

        Integer[] fourArray = new Integer[]{3, 4, 1, 2};
        sorter.sort(fourArray);
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, fourArray);

        Integer[] tenArray = new Integer[]{70, 85, 4001, 3, 29, 580, 251, 19, 3821, 989};
        sorter.sort(tenArray);
        assertArrayEquals(new Integer[]{3, 19, 29, 70, 85, 251, 580, 989, 3821, 4001}, tenArray);
    }
}