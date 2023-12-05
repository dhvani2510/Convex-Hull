package main.data_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stack<T> {
    private Object[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Object get(int index) {
        return array[index];
    }
    // Constructor
    public Stack() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Push operation
    public void push(T element) {
        ensureCapacity();
        array[size++] = element;
    }

    // Pop operation
    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Object element = array[--size];
        if(element==null)
            throw new ClassCastException("Cannot cast ");
        array[size] = null; // Set the popped element to null for garbage collection
        return element;
    }

    // Peek operation
    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return  array[size - 1];
    }

    // PeekNextToTop operation
    public Object peekNextToTop() {
        if (size < 2) {
            throw new IllegalStateException("Not enough elements in the stack to peek next to top");
        }
        return array[size - 2];
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the size of the stack
    public int size() {
        return size;
    }

    // Ensure the capacity of the array
    private void ensureCapacity() {
        if (size == array.length) {
            int newCapacity = array.length * 2;
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    public List<Point> toArray() {
        List<Point> points = new ArrayList<>();
        for (Object obj: array) {
            Point p = (Point) obj;
            points.add(p);
        }
        return points;
    }
}
