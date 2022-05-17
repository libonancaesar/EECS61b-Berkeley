package es.datastructur.synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    public int capacity();

    public int fillCount();

    public T dequeue();

    public void enqueue(T x);

    public T peek();

    default boolean isEmpty(){
        return fillCount() == 0;
    }

    default boolean isFull(){
        return fillCount() == capacity();
    }

}
