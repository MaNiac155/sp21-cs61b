package deque;

import java.util.Iterator;

public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    default public boolean isEmpty(){
        return size() == 0;
    };
    public int size();
    public T removeFirst();
    public T removeLast();
    public  void printDeque();
    public T get(int index);
    public Iterator<T> iterator();
    public boolean equals(Object o);
}
