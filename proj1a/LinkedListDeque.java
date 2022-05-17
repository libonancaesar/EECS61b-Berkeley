public class LinkedListDeque<T> {
    public Node sentinelHead;
    public int size;

    private class Node{
        public T item;
        public Node previous;
        public Node next;

        public Node(T t,  Node p, Node n) {
            item = t;
            next = n;
            previous = p;
        }
    }
    /* The first item if it exist is sentinel.next
       The last item if it exist is sentinel.previous
       */
    public LinkedListDeque() {
        sentinelHead = new Node(null, null, null);
        sentinelHead.next = sentinelHead;
        sentinelHead.previous = sentinelHead;
        size = 0;
    }

    public LinkedListDeque (T value) {
        sentinelHead = new Node(null, null, null);
        sentinelHead.next = new Node(value, sentinelHead, sentinelHead);
        sentinelHead.previous = sentinelHead.next;
        size = 1;
    }
    /* 1) The previous of new node should points at sentinel
       2) The next of new node should points at where sentinel.next points at
       3) The original sentinel.next.previous should point at the new node
       4) The original sentinel.next.next should point at sentinel
     */
    public void addFirst(T item) {
        sentinelHead.next.previous = new Node(item, sentinelHead, sentinelHead.next);
        sentinelHead.next = sentinelHead.next.previous;
        size = size + 1;
    }

    /* Add the last item to the end of the linked list */
    public void addLast(T item) {
        sentinelHead.previous.next =  new Node(item, sentinelHead.previous, sentinelHead);
        sentinelHead.previous = sentinelHead.previous.next;
        size += 1;
    }

    /* return the size of the double linked list */
    public int size(){

        return size;
    }
    /* check if the linked list is empty or not*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }

    }

    /* print the linked list */
    public void printDeque (){
        Node p = sentinelHead;
        while (p.next != sentinelHead) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /* remove the first item of the linked list */
    public T removeFirst() {
        if (sentinelHead.next == sentinelHead) {
            return null;
        }
        else {
            sentinelHead.next = sentinelHead.next.next;
            sentinelHead.next.previous = sentinelHead;
            size = size - 1;
            return sentinelHead.next.item;
        }
    }

    public T removeLast() {
        if (sentinelHead.previous == sentinelHead) {
            return null;
        }
        else {
            sentinelHead.previous = sentinelHead.previous.previous;
            sentinelHead.previous.next = sentinelHead;
            size = size -1;
            return sentinelHead.previous.item;
        }
    }

    /* get the item at a given index non-destructively */

    public T get (int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            Node temp = sentinelHead;
            for (int i = 0; i <= index; i++) {
                temp = temp.next;
            }
            return temp.item;
        }
    }

    /* helper function for recursive get */
    public T getRecursionhelper (int id, Node nd) {
        if (id == 0) {
            return nd.next.item;
        }
        return getRecursionhelper(id - 1, nd.next);
    }


    /* get the item a given index using recursion */
    public T getRecursion (int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node p = sentinelHead;
        return getRecursionhelper(index,p);

    }

    /* Create a copy of other and the changes in other will not influence the original linked list */
    public LinkedListDeque (LinkedListDeque other) {
        sentinelHead = new Node(null, null, null);
        sentinelHead.previous = sentinelHead;
        sentinelHead.next = sentinelHead;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }


//    /* remove the last item of the do */
//    public static void main (String[] args) {
//        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
//        System.out.println(lld1.isEmpty());
//    }
}
