import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;

public class CycledLinkedList<E> implements Collection<E>{
    private int size;
    private Node begin;

    public class Node {
        private Node next, prev;
        private E value;

        public Node(E elem) {
            this.value = elem;
        }

        public Node next() {
            return next;
        }

        public Node prev() {
            return prev;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return begin == null;
    }

    @Override
    public boolean contains(Object o) {
        Node currElem = begin;
        do {
            if (currElem.value.equals(o))
                return true;
            currElem = currElem.next;
        } while (currElem == begin);
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node currElem = begin;
        for (int i = 0; i < size; ++i) {
            array[i] = currElem.value;
            currElem = currElem.next;
        }
        return array;
    }

    /**
     * Is not supported
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        addFirst(e);
        return true;
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param elem element whose presence in this collection is to be ensured
     * @return Node in collection associated with added element
     */
    public Node addFirst(E elem) {
        Node t = new Node(elem);
        if (begin != null) {
            t.next = begin.next;
            t.next.prev = t;
            t.prev = begin;
            t.prev.next = t;
        } else {
            begin = t;
            begin.next = t;
            begin.prev = t;
        }
        size++;
        return t;
    }

    /**
     * Inserts the specified element at the end of this list.
     * This method is equivalent to {@link #add}.
     *
     * @param elem element whose presence in this collection is to be ensured
     * @return Node in collection associated with added element
     */
    public Node addLast(E elem) {
        Node t = new Node(elem);
        if (begin != null) {
            t.prev = begin.prev;
            t.prev.next = t;
            t.next = begin;
            t.next.prev = t;
        } else {
            begin = t;
            begin.next = t;
            begin.prev = t;
        }
        size++;
        return t;
    }

    @Override
    public boolean remove(Object o) {
        Node currElem = begin;
        do {
            if (currElem.value.equals(o)) {
                if (size == 1) { //means currElem == begin
                    begin = null;
                } else {
                    currElem.prev.next = currElem.next;
                    currElem.next.prev = currElem.prev;
                }
                size--;
                return true;
            }
            currElem = currElem.next;
        } while (currElem == begin);
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o: c) {
            if (!this.contains(o))
                return false;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean res = false;
        for(E o: c) {
            if (this.add(o))
                res = true;
        }
        return res;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for(Object o: c) {
            if (this.remove(o))
                res = true;
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean res = false;
        for(E e: this) {
            if (!c.contains(e)) {
                res = this.remove(e);
            }
        }
        return res;
    }

    @Override
    public void clear() {
        size = 0;
        begin = null;
    }


}
