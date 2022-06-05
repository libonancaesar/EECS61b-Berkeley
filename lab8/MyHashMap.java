import jdk.jshell.execution.LoaderDelegate;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;


public class MyHashMap<K, V> implements Map61B<K, V>{

    private static final int INIT_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int initialSize;
    private double loadFactor;
    private Item<K, V>[] map;
    private int size = 0;

    private class Item<K, V> {
        public K key;
        public V val;
        private Item next;

        public Item(K k, V v) {
            key = k;
            val  = v;
            next = null;
        }

    }


    public MyHashMap() {
        this(INIT_SIZE, LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
       this(initialSize, LOAD_FACTOR);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        map = (Item<K, V>[]) new Item[initialSize];
    }

    @Override
    public void clear(){
        for (int i = 0; i < initialSize; i ++) {
            map[i] = null;
        }
        size = 0;
    }

    @Override
    public int size(){
        return size;
    }

    private void putHelper(K key, V value, Item item) {
        if (item.key == key) {
            item.val = value;
        } else if (item.next == null){
            item.next = new Item<>(key, value);
            size = size + 1;
        } else {
            putHelper(key, value, item.next);
        }
    }

    private int hashKeyFromTextBook(K key) {
        return (key.hashCode() & 0x7fffffff) % initialSize;
    }

    @Override
    public void put(K key, V val){
        int index =  hashKeyFromTextBook(key);
        if (map[index] == null) {
            map[index] = new Item(key, val);
            size = size + 1;
        } else {
            putHelper(key, val, map[index]);
        }
        if ((double) size/initialSize > loadFactor) {
            resize();
        }

    }

    private void resize(){
        MyHashMap<K, V> temp = new MyHashMap<>(initialSize * 2);
        Set<K> keys = keySet();
        for (K key: keys) {
            temp.put(key, get(key));/* this get is to get the value from the current object */
        }
        initialSize = temp.initialSize;
        map = temp.map;
    }

    @Override
    public Set<K> keySet(){
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < initialSize; i++) {
            Item<K, V> item = map[i];
            while (item != null) {
                keys.add((K) item.key);
                item = item.next;
            }
        }
        return keys;
    }
    private V getHelper(K key, Item item) {
        if (item == null) {
            return null;
        } else if (item.key.equals(key)) {
            return (V) item.val;
        } else {
            return (V) getHelper(key, item.next);
        }

    }

    @Override
    public V get(K key) {
        int index = hashKeyFromTextBook(key);
        return getHelper(key, map[index]);
    }

    @Override
    public Iterator iterator(){
        return new MyHashMapIter();
    }

    @Override
    public boolean containsKey(K key) {
        int index = hashKeyFromTextBook(key);
        return getHelper(key, map[index]) != null;
    }

    @Override
    public V remove(K key){
        throw  new UnsupportedOperationException("MyHashMap doesn't support remove method.");
    }

    @Override
    public V remove(K key, V value) {
        throw  new UnsupportedOperationException("MyHashMap doesn't support remove method.");
    }

    private class MyHashMapIter implements Iterator<K> {
        private Item cur;
        private int index;
        /**
         * Create a new MyHashMapIter by setting cur to the first node in the
         * linked list that stores the key-value pairs.
         */
        MyHashMapIter() {
            index = 0;
            cur = getItem(map[index]);
        }

        @Override
        public boolean hasNext() {
            return (cur != null);
        }

        @Override
        public K next() {
            K ret = (K) cur.key;
            cur = getItem(cur.next);
            return ret;
        }

        private Item getItem(Item cur) {
            if (cur == null) {
                index += 1;
                if (index > initialSize - 1) {
                    return null;
                } else {
                    return getItem(map[index]);
                }
            } else {
                return cur;
            }
        }

    }
}
