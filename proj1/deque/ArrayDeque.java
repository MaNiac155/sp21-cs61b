package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Deque<T>,Iterable<T> {
    private T[] array;
    private int size;
    private int front;
    private int rear;
    private int capacity=8;

    @Override
    public T get(int index) {
        return array[(front+index)%array.length];
    }

    @Override
    public void printDeque() {
        for(int i=0;i<size;i++){
            int p=(front+i)%capacity;
            System.out.print(array[p]+" ");
        }
        System.out.println();
    }

    @Override
    public T removeLast() {
        size--;
        rear--;
        T tmp=array[rear];
        array[rear]=null;
        return tmp;
    }

    @Override
    public T removeFirst() {
        size--;
        T tmp=array[front];
        array[front]=null;
        front=(front+1)%capacity;
        return tmp;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void addLast(T item) {
        array[rear]=item;
        size++;
        rear=(rear+1)%capacity;
    }

    @Override
    public void addFirst(T item) {
        if(front==0) front=capacity;
        front=(front-1)%capacity;
        array[front]=item;
        size++;
    }

    public ArrayDeque() {
        array = (T[]) new Object[capacity];
        front=0;
        rear=0;
        size=0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T>{
        private int currentIndex=0;
        @Override
        public boolean hasNext() {
            return currentIndex<size;
        }

        @Override
        public T next() {
            if(currentIndex<size){
                T v= array[(front+currentIndex)%capacity];
                currentIndex++;
                return v;
            }
            else{
                throw new NoSuchElementException();
            }
        }
    }
}
