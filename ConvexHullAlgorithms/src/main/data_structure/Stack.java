package main.data_structure;

import java.util.Arrays;
import java.util.List;

public class Stack<T> {
    private Object[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public List<T> toArray() {
        return (List<T>) Arrays.stream(array).toList();
    }

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
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T element = (T) array[--size];
        array[size] = null; // Set the popped element to null for garbage collection
        return element;
    }

    // Peek operation
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return (T) array[size - 1];
    }

    // PeekNextToTop operation
    public T peekNextToTop() {
        if (size < 2) {
            throw new IllegalStateException("Not enough elements in the stack to peek next to top");
        }
        return (T) array[size - 2];
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
}
