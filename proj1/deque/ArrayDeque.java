package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int size;
    private int front;
    private int rear;
    private int capacity = 4;

    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque)) return false;
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (size != other.size) return false;
        for (int i = 0; i < size; i++) {
            if (!this.removeFirst().equals(other.removeFirst())) return false;
        }
        return true;
    }

    @Override
    public T get(int index) {
        return array[(front + index) % array.length];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int p = (front + i) % capacity;
            System.out.print(array[p] + " ");
        }
        System.out.println();
    }

    private void smallerResize() {
        T[] a = (T[]) new Object[capacity / 2];
        if (rear < front) {
            System.arraycopy(array, 0, a, 0, rear);
            System.arraycopy(array, front, a, capacity - front, capacity - front);
            front = capacity - front;
        } else {
            System.arraycopy(array, front, a, 0, size);
            front = 0;
            rear = size;
        }
        array = a;
        capacity = capacity / 2;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;

        if (size > 4 && size <= capacity / 4) {
            smallerResize();
        }
        size--;
        if (rear == 0) rear = capacity;
        rear = (rear - 1) % capacity;
        T tmp = array[rear];
        array[rear] = null;
        return tmp;
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        if (size > 4 && size <= capacity / 4) {
            smallerResize();
        }
        size--;
        T tmp = array[front];
        array[front] = null;
        front = (front + 1) % capacity;
        return tmp;
    }

    @Override
    public int size() {
        return size;
    }

    private void biggerResize() {
        T[] a = (T[]) new Object[capacity * 2];
        if (rear < front) {
            System.arraycopy(array, 0, a, 0, rear);
            System.arraycopy(array, front, a, capacity + front, capacity - front);
            front = capacity + front;
        } else {
            System.arraycopy(array, front, a, front, size);
        }
        array = a;
        capacity = capacity * 2;
    }

    @Override
    public void addLast(T item) {
        if ((rear + 1) % capacity == front) {
            biggerResize();
        }
        array[rear] = item;
        size++;
        rear = (rear + 1) % capacity;
    }

    @Override
    public void addFirst(T item) {
        if ((rear + 1) % capacity == front) {
            biggerResize();
        }
        if (front == 0) front = capacity;
        front = (front - 1) % capacity;
        array[front] = item;
        size++;
    }

    public ArrayDeque() {
        array = (T[]) new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (currentIndex < size) {
                T v = array[(front + currentIndex) % capacity];
                currentIndex++;
                return v;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
