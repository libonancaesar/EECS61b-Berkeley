public interface Deque <Item>{

    public boolean isEmpty();

    public int size();

    public void addFirst(Item x);

    public Item removeFirst();

    public void addLast(Item x);

    public Item removeLast();

    public Item get(int x);

    public void printDeque ();

    public Item getFirst();

    public Item getLast();
}
