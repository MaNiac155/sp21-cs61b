package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    ListNode<T> head;
    int size = 0;

    public LinkedListDeque() {
        head = new ListNode<T>(null, null, null);

    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode<T> temp = head.next;
        if (temp == null) {
            return;
        }
        for (; temp.value != null; temp = temp.next) {
            System.out.print(temp.value + " ");
        }
    }

    public void addFirst(T item) {
        size++;
        ListNode<T> newNode = new ListNode<T>(item, head.next, head);
        if (size == 1) {
            newNode.next = head;
            head.prev = newNode;
            head.next = newNode;
        } else {
            head.next.prev = newNode;
            head.next = newNode;

        }
    }

    public void addLast(T item) {
        size++;
        ListNode<T> newNode = new ListNode<T>(item, head, head.prev);
        if (size == 1) {
            newNode.prev = head;
            head.next = newNode;
            head.prev = newNode;
        } else {
            head.prev.next = newNode;
            head.prev = newNode;
        }
    }

    public T removeFirst() {
        if (size == 0) return null;
        ListNode<T> node = head.next;
        head.next = node.next;
        node.next.prev = head;
        T value = node.value;
        node = null;
        size--;
        return value;

    }

    public T removeLast() {
        if (size == 0) return null;
        ListNode<T> node = head.prev;
        head.prev = node.prev;
        node.prev.next = head;
        T value = node.value;
        node = null;
        size--;
        return value;
    }

    public T get(int index) {
        ListNode<T> node = head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int currentIndex = 0;
        private ListNode<T> current = head.next;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (currentIndex <= size) {
                T v = current.value;
                current = current.next;
                currentIndex++;
                return v;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) return false;
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (size != other.size) return false;

        ListNode<T> temp1 = head.next;
        ListNode<T> temp2 = other.head.next;
        while (temp1 != head && temp2 != head) {
            if (temp1.value != temp2.value) return false;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return true;
    }
}

class ListNode<T> {
    T value;
    ListNode next;
    ListNode prev;

    public ListNode() {
        this.value = null;
        this.next = null;
        this.prev = null;
    }

    public ListNode(T value, ListNode next, ListNode prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

}
