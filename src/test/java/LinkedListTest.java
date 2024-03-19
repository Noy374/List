import static org.junit.Assert.*;

import org.example.LinkedList;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
    private LinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new LinkedList<>();
    }

    @Test
    public void testAdd() {
        assertTrue(list.isEmpty());
        list.add(5);
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertTrue(list.contains(5));
    }

    @Test
    public void testRemove() {
        list.add(10);
        list.add(20);
        list.add(30);
        assertEquals(3, list.size());
        assertTrue(list.contains(20));
        assertTrue(list.remove(20));
        assertFalse(list.contains(20));
        assertEquals(2, list.size());
    }

    @Test
    public void testAddAll() {
        LinkedList<Integer> otherList = new LinkedList<>();
        otherList.add(1);
        otherList.add(2);
        otherList.add(3);
        list.addAll(otherList);
        assertEquals(3, list.size());
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
        assertTrue(list.contains(3));
    }

    @Test
    public void testClear() {
        list.add(100);
        list.add(200);
        list.add(300);
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void testGet() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, (int)list.get(0));
        assertEquals(2, (int)list.get(1));
        assertEquals(3, (int)list.get(2));
    }

    @Test
    public void testRemoveByIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(1, (int)list.removeByIndex(0));
        assertEquals(2, list.size());
        assertEquals(2, (int)list.removeByIndex(0));
        assertEquals(1, list.size());
    }

    @Test
    public void testAddAtIndex() {
        list.add(1);
        list.add(3);
        list.add(1, 2);
        assertEquals(2, (int)list.get(1));
        list.add(2, 4);
        assertEquals(4, (int)list.get(2));
    }


    @Test
    public void testToArray() {
        list.add(1);
        list.add(2);
        list.add(3);
        Object[] array = list.toArray();
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
        assertEquals(3, array[2]);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
        list.remove(1);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testToString() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals("1 2 3 ", list.toString());
    }

}
