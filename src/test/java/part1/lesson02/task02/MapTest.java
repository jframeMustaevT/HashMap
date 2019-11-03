package part1.lesson02.task02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {


    @Test
    public void testSize() {
        Main.MyHashMap map= new Main.MyHashMap();
        assertEquals(0,map.size());
        map.put(100,47L);
        assertEquals(1,map.size());
    }

    @Test
    public void testNull() {
        Main.MyHashMap map = new Main.MyHashMap();
        map.put(101,null);
        checkOutPut(map,null,100);
        checkOutPut(map,null,101);
    }



    private void checkOutPut(Main.MyHashMap map, Long expected, Integer key) {
        Long actual= map.get(key);
        assertEquals(expected,actual);
    }

}