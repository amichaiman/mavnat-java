class Main {
    public static void main(String[] args) {
        AQueue<Integer> queue = new AQueue<>(4);
        queue.enqueue(4);
        queue.enqueue(7);
        queue.enqueue(9);
        queue.enqueue(6);
        queue.dequeue();
        queue.enqueue(0);
        queue.dequeue();
        queue.enqueue(3);
        queue.showStructure();
    }
}


interface Queue<AnyType> {
    public static final int DEF_MAX_QUEUE_SIZE = 10;
    public void enqueue(AnyType newElement );
    public AnyType dequeue( );
    public void clear( );
    public boolean isEmpty( );
    public boolean isFull( );
    public void showStructure( );
}
class AQueue<AnyType> implements Queue {
    private int maxSize;
    private int front;
    private int rear;
    private int numOfElements;
    private AnyType[] elements;

    public AQueue(){
        setup(DEF_MAX_QUEUE_SIZE);
    }
    public AQueue(int size){
        setup(size);
    }
    private void setup(int size){
        elements = (AnyType[]) new Object[size];
        maxSize = size;
    }

    @Override
    public void enqueue(Object newElement){
        if (isFull()) return;
        elements[rear] = (AnyType) newElement;
        if (++rear >= maxSize) {
            rear = 0;
        }
        numOfElements++;
    }

    @Override
    public Object dequeue(){
        if (isEmpty()) return null;
        int index = front;
        if (++front >= maxSize) {
            front = 0;
        }
        numOfElements--;
        return elements[index];
    }

    @Override
    public void clear() {
        elements = (AnyType[]) new Object[maxSize];
    }

    @Override
    public boolean isEmpty() {
        return numOfElements == 0;
    }

    @Override
    public boolean isFull() {
        return numOfElements == maxSize;
    }

    @Override
    public void showStructure() {
        if (isEmpty()) { return; }
        if (front < rear) {
            for (int i=front; i<rear; i++) {
                System.out.println(elements[i]);
            }
        } else {
            for (int i=front; i<maxSize; i++) {
                System.out.println(elements[i]);
            }
            for (int i=0; i<rear; i++) {
                System.out.println(elements[i]);
            }
        }
    }
}