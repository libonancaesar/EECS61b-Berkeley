import jdk.jshell.execution.LoaderDelegate;

import javax.swing.*;
import java.util.Set;
import java.util.Iterator;


public class MyHashMap<K, V> implements Map61B<K, V>{

    private static final int INIT_SIZE = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int initialSize;
    private double loadFactor;

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
        return;
    }


    @Override
    public Set<K> keySet(){
        return null;
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
