package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;

        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T returnItem = rb[first];
        first = (first + 1) % capacity;
        fillCount--;
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new bufferIterator();
    }

    private class bufferIterator implements Iterator<T> {

        int wizPos = first;

        @Override
        public boolean hasNext() {
            return wizPos != last;
        }

        @Override
        public T next(){
            T returnedT = rb[wizPos];
            wizPos = wizPos + 1;
            return returnedT;
        }

    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (other == null || this.getClass() != other.getClass()) { return false; }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (this.fillCount() != o.fillCount()) { return false;}
        Iterator<T> it1 = this.iterator();
        Iterator<T> ito = o.iterator();
        while(it1.hasNext() && ito.hasNext()) {
            if (it1.next() != ito.next()) {
                return false;
            }
        }
        return true;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.
