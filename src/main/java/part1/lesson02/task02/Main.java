package part1.lesson02.task02;


import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();

        map.put(128, 120L);
        map.put(140, 110L);
        map.put(148, 150L);

        System.out.println("Size is: " + map.size);
        System.out.println("Value is:" +map.get(140));

    }


    public static class MyHashMap implements Map {
        private final int INITIAL_CAPACITY = 16;
        private final float LOAD_FACTOR = 0.75f;


        private Integer[] keys;
        private Long[] values;
        private int currentSize = 0;
        private int size;

        public MyHashMap() {
            this.size = INITIAL_CAPACITY;
            this.keys = new Integer[size];
            this.values = new Long[size];
        }

        @Override
        public int size() {
            return currentSize;
        }

        @Override
        public void put(Integer key, Long value) {
            if (checkOverFlow(size(), getSize())) {
                resize();
            }
            int index = getIndex(key);
            for (int i = index; ; i++) {
                if (i == size) {
                    i = 0;
                }
                if (keys[i] == null) {
                    keys[i] = key;
                    currentSize++;
                }
                if (keys[i].equals(key)) {
                    values[i] = value;
                    break;
                }
            }
        }

        private void resize() {
            currentSize = 0;
            Integer[] keysCopy = new Integer[getSize()];
            Long[] valuesCopy = new Long[getSize()];
            setSize(getSize() * 2);
            System.arraycopy(keys, 0, keysCopy, 0, keys.length);
            System.arraycopy(values, 0, valuesCopy, 0, values.length);
            keys = new Integer[getSize()];
            values = new Long[getSize()];
            for (int i = 0; i < keysCopy.length; i++) {
                if (keysCopy[i] != null) {
                    put(keysCopy[i], valuesCopy[i]);
                }

            }
        }

        public Long get(Integer key) {
            int index = getIndex(key);
            int i = index + 1;
            if (keys[index] == key) {
                return values[index];
            }
            while (i != index) {
                if (i == size) {
                    i = 0;
                }
                if (keys[i] == key) {
                    return values[i];
                }
                i++;
            }
            return null;
        }

        private boolean checkOverFlow(int currentSize, int capacity) {
            return capacity * LOAD_FACTOR <= currentSize;
        }

        private int getIndex(Integer key) {
            return hashCode(key) & (size - 1);
        }


        private int hashCode(Integer key) {
            if (key == 0) {
                return 0;
            }
            return Objects.hashCode(key);
        }

        private int getSize() {
            return size;
        }

        private void setSize(int size) {
            this.size = size;


        }
    }
}














