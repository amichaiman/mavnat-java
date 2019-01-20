import java.nio.BufferOverflowException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Stack;
public class Main{
    public static void main(String[] args) {
        SQueue<Integer> s = new SQueue<>();
        s.enqueue(1);
        s.enqueue(2);
        s.enqueue(3);
        s.enqueue(4);
        s.enqueue(5);
        s.enqueue(6);
        System.out.println(s.dequeue());
        s.enqueue(7);
        s.enqueue(8);
        s.enqueue(9);
        s.showStructure();

    }
}

interface TheQueue <T> {
    public static final int DEF_MAX_QUEUE_SIZE = 10;
    public void enqueue ( T newElement );
    public T dequeue ( );
    public void clear ( );

    public boolean isEmpty ( );
    public boolean isFull ( );
    public void showStructure ( );
}


class SQueue<T> implements TheQueue<T> {
    private Stack<T> A; //insert queue
    private Stack<T> B; //output queue
    private int maxElements;

    public SQueue ( ){
        setup(DEF_MAX_QUEUE_SIZE);
    }
    public SQueue ( int size ){
        setup(size);
    }

    private void setup(int size) {
        this.maxElements = size;
        A = new Stack<>();
        B = new Stack<>();
    }

    @Override
    public void enqueue(T newElement) {
        if (this.size() >= maxElements) {
            throw new BufferOverflowException();
        }
        A.push(newElement);
    }

    @Override
    public T dequeue() {
        if (B.empty()) {
            int numberOfElementsToCopy = A.size();
            for (int i=0; i<numberOfElementsToCopy; i++) {
                T elem = A.pop();
                B.push(elem);
            }
        }
        return B.pop();
    }

    @Override
    public void clear() {
        A.clear();
        B.clear();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean isFull() {
        return size() == maxElements;
    }

    public int size() {
        return A.size() + B.size();
    }

    @Override
    public void showStructure() {
        System.out.println("B:");
        ListIterator<T> iterator = B.listIterator(B.size());
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
        System.out.println("A:");
        for (T elem : A) {
            System.out.println(elem);
        }
    }
}
