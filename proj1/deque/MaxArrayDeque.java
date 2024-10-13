package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max() {
        T max = null;
        for (int i = 0; i < size(); i++) {
            max = comparator.compare(max, this.get(i)) < 0 ? this.get(i) : max;
        }
        return max;
    }

    public T max(Comparator<T> c) {
        T max = null;
        for (int i = 0; i < size(); i++) {
            max = c.compare(max, this.get(i)) < 0 ? this.get(i) : max;
        }
        return max;
    }
}
