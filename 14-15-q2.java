import com.sun.xml.internal.ws.api.model.MEP;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree(3);
        System.out.println("value inserted " + (tree.add(4) ? "" : "un") + "successfully");
        System.out.println("value inserted " + (tree.add(5) ? "" : "un") + "successfully");
        System.out.println("value inserted " + (tree.add(5) ? "" : "un") + "successfully");
        System.out.println("before");
        tree.print();
        tree.removeMin();
        tree.isFull();
        System.out.println("after");
        tree.print();

    }
}

class Tree {
    private int[] tree;
    private int maxHeight;
    private int currentHeight;
    private int numOfElements;
    public static final int EMPTY = Integer.MIN_VALUE;

    public Tree(int height) {
        this.maxHeight = height;
        tree = new int[getIndexFirstElemInLevel(height+1)];
        Arrays.fill(tree, EMPTY);
        currentHeight = 1;
        numOfElements = 0;
    }


    public boolean add(int data) {
        int firstElemIndex = getIndexFirstElemInLevel(currentHeight);
        /* iterate over last level in tree */
        for (int i = firstElemIndex; i<(firstElemIndex+Math.pow(3,currentHeight-1)); i++) {
            /* if spot is empty, and father is smaller than the new kid */
            if (tree[i] == EMPTY && tree[(i-1)/3] < data){
                tree[i] = data;
                numOfElements++;
                if (numOfElements >= getIndexFirstElemInLevel(currentHeight + 1)) {
                    currentHeight++;
                }
                return true;
            }
        }
        return false;
    }

    private int getIndexFirstElemInLevel(int level) {
        int n = 0;
        for (int i=0; i<level-1; i++) {
            n += Math.pow(3,i);
        }
        return n;
    }

    public void print() {
        for (int i=0; i< tree.length; i++) {
            System.out.print(tree[i] == EMPTY ? "" : tree[i]+" at index " + i + "\n");
        }
    }

    public void removeMin() {
        if (tree[0] == EMPTY) return;
        remove(0);
    }

    private void remove(int i) {
        int minIndex = min(i*3+1, i*3+2, i*3+3);
        if (tree[minIndex] == EMPTY) {
            tree[minIndex] = EMPTY;
            return;
        }
        tree[i] = tree[minIndex];
        tree[minIndex] = EMPTY;
        if (i <= getIndexFirstElemInLevel(currentHeight-1)) {
            remove(minIndex);
        }
    }
    private int min(int i, int j, int k) {
        int minIndex;
        if (tree[i] != EMPTY && tree[j] != EMPTY) {
             minIndex = tree[i] < tree[j] ? i : j;
        } else if (tree[i] == EMPTY && tree[j] == EMPTY){
            return k;
        } else {
            minIndex = tree[i] == EMPTY ? j : i;
        }
        return tree[k] == EMPTY ? minIndex : (tree[k] < tree[minIndex] ? k : minIndex);
    }

    public boolean isFull() {
        return numOfElements == getIndexFirstElemInLevel(currentHeight+1);
    }

    public boolean isEmpty() {
        return numOfElements == 0;
    }
}
