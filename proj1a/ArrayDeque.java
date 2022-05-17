import javax.swing.plaf.synth.SynthStyle;
import java.awt.desktop.SystemSleepEvent;
import java.io.ObjectStreamException;

public class ArrayDeque<T> {
    private int  front;
    private int  end;
    private T[] item;
    private int size;

    /* constructor of the array with starting size of 8 */
    public ArrayDeque() {
        item  = (T[]) new Object[8]; /* allocate 8 slots by default */
        front = 0;
        end = 0;
        size = 0;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    private int minusOne(int index) {
        return Math.floorMod(index - 1, item.length);
    }

    /* get the next index */
    private int plusOne(int index) {
        return Math.floorMod(index + 1, item.length);
    }

    public void addFirst(T value) {
        if (isEmpty()) {
            front = 0;
            end = 0;
            item[front] = value;
            size++;
            return;
        }
        if (size == item.length) {
            resizeUp();
        } else {
            front = minusOne(front);
        }
        item[front] = value;
        size ++;

    }

    public T removeFirst() {
        if (isEmpty()) {
            return  null;
        }
        T removedElement = item[front];
        if (size == 1) {
            front = 0;
            end = 0;
        }

        if (size < item.length/4 && item.length > 16) {
            resizeDown();
            item[front] = null;
            front  = plusOne(front);
        } else {
            item[front] = null;
            front = plusOne(front);
        }
        size --;
        return removedElement;
    }



    public void addLast (T value) {
        if (isEmpty()) {
            front = 0;
            end = 0;
            item[end] = value;
            size++;
            return;
        }
        if (size == item.length) {
            resizeUp();
        } else {
            end = plusOne(end);
        }
        item[end] = value;
        size ++;

    }


    public T removeLast() {
        if (isEmpty()) {
            return  null;
        }
        T removedElement = item[end];
        if (size == 1) {
            front = 0;
            end = 0;
        }

        if (size < item.length/4 && item.length > 16) {
            resizeDown();
            item[end] = null;
            end  = minusOne(end);
        } else {
            item[end] = null;
            end = minusOne(end);
        }
        size --;
        return removedElement;
    }

    private void resizeUp() {
        T[] expandedArray = (T[]) new Object[item.length * 2];
        // copy array into new array
        int sizeOfFirstCopy = item.length - front;
        int sizeOfAnotherCopy = size - sizeOfFirstCopy;
        System.arraycopy(item, front, expandedArray, 0, sizeOfFirstCopy);
        System.arraycopy(item, 0, expandedArray, sizeOfFirstCopy, sizeOfAnotherCopy);
        item = expandedArray;
        front = 0;
        end = size - 1;
    }

    private void resizeDown() {
        T[] expandedArray = (T[]) new Object[item.length / 2];
        // copy array into new array
        if (front < end) {
            System.arraycopy(item, front, expandedArray, 0, size);
        } else {
            int sizeOfFirstCopy = item.length - front;
            int sizeOfAnotherCopy = size - sizeOfFirstCopy;
            System.arraycopy(item, front, expandedArray, 0, sizeOfFirstCopy);
            System.arraycopy(item, 0, expandedArray, sizeOfFirstCopy, sizeOfAnotherCopy);

        }
        item = expandedArray;
        front = 0;
        end = size - 1;

    }

    /* get the item at a particular index */
    public T get(int index) {
        if (isEmpty() || index >= size || index < 0 ) {
            return null;
        } else {
            int id = (front + index) % item.length;
            return item[id];
        }
    }

    /* print the deque */
    public void printDeque () {
        if (size == 0) {
            System.out.print((String) null);
        } else {
            for (int i = front; i != end; i = plusOne(i) ) {
                System.out.print(item[i] + " ");
            }
            System.out.print(item[end] + " ");

        }
        System.out.println();
    }

    public ArrayDeque (ArrayDeque other) {
        int newArray = other.size;
        item  = (T[]) new Object[newArray];
        front = 0;
        end = 0;
        for (int i = 0; i < other.size; i++) {
            addLast((T) other.get(i));
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();
        ad.addFirst(5);
        ad.addFirst(25);
        ad.addFirst(34);
        ad.addFirst(2);
        ad.addFirst(1);
        ad.removeFirst();
        ad.removeLast();
        ad.printDeque();
        ArrayDeque<Integer> vd = new ArrayDeque<Integer>(ad);
        vd.printDeque();
    }


}














