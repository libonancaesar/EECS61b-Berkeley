import javax.swing.*;
import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left;
        private Node right;
        private int size;

        public Node(K key, V val){
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
            this.size = 1;
        }
    }

    public BSTMap(){

    }

    @Override
    public void clear(){
        root = null;
    }

    @Override
    public boolean containsKey(K key){
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key)!= null;
    }

    @Override
    public V get(K key){

        return get(root, key);
    }

    private V get(Node n , K key) {
        if (key == null) {
            throw new IllegalArgumentException("Calls get() with a null key");
        }
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0 ) {
            return get(n.left, key); /* searching left sub-tree*/
        } else if (cmp > 0) {
            return get(n.right, key);/* searching right sub-tree*/
        } else {
            return n.val;
        }
    }


    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return size(root);
    }


    private int size (Node n) {
        int nodeSize = 0;
        if (n != null) {
            nodeSize = n.size;
        }
        return nodeSize;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        root = insert(root, key, value);
    }

    private Node insert(Node n, K key, V value) {
        if (n == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(n.key);
        if (cmp == 0) {
            n.val = value;
        } else if (cmp < 0 ){
            n.left = insert(n.left, key, value);
        } else {
            n.right = insert(n.left, key, value);
        }
        n.size = size(n.right) + size(n.left) + 1;
        return n;
    }



    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException("keySet() method is not supported.");
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException("remove() method is not supported.");
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException("remove() method is not supported.");
    }

    public void printInOrder() {
        printInOrder(root);
    }


    private void printInOrder(Node n) {
        if (n == null) {
            return;
        }
        printInOrder(n.left);
        System.out.println("key: " + n.key + " value: " + n.val);
        printInOrder(n.right);
    }

    @Override
    public Iterator<K> iterator(){
        throw new UnsupportedOperationException("iterator() method is not supported.");
    }

}
