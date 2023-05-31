package list;

import java.util.*;

/**
 * @author koubs
 * @date 2020/6/1
 **/
public class MyList implements List {

    private Object[] elements;

    private int curr;

    public MyList(){
        elements = new Object[16];
        curr = 0;
    }


    @Override
    public int size() {
        return curr;
    }

    @Override
    public boolean isEmpty() {
        return curr == 0;
    }

    @Override
    public boolean contains(Object o) {

        for (Object element: elements){
            if (Objects.equals(element,o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {

        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {
        curr = 0;
    }

    @Override
    public Object get(int index) {

        if (index > curr || index < 0){
            throw new IndexOutOfBoundsException("out of bound " + curr + "for" + index);
        }
        return elements[index];
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
