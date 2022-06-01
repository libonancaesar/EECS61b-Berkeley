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
        initialSize = INIT_SIZE;
        loadFactor = LOAD_FACTOR;
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        loadFactor = LOAD_FACTOR;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
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


    @Override
    public void put(K key, V val){
        int index =  (key.hashCode() & 0x7fffffff) % initialSize;
        if (map[index] == null) {
            map[index] = new Item(key, val);
            size = size + 1;
        } else {
            /* are we putting the same value ? */
        }
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


    @Override
    public V remove(K key){
        throw  new UnsupportedOperationException("MyHashMap doesn't support remove method.");
    }

    @Override
    public V remove(K key, V value) {
        throw  new UnsupportedOperationException("MyHashMap doesn't support remove method.");
    }

    }
