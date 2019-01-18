public class Main {
    public static void main(String[] args) {

    }
}

class LogicExprTreeNode {
    private char key;
    private LogicExprTreeNode left, right;

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public LogicExprTreeNode getLeft() {
        return left;
    }

    public void setLeft(LogicExprTreeNode left) {
        this.left = left;
    }

    public LogicExprTreeNode getRight() {
        return right;
    }

    public void setRight(LogicExprTreeNode right) {
        this.right = right;
    }

    public LogicExprTreeNode(char key, LogicExprTreeNode left,
                             LogicExprTreeNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
}


class LogicExprTree {
    private LogicExprTreeNode root;
    public LogicExprTree ( ){

    }
    public void build (String expression ){

    }
    public void expression ( ){
        printInfix(root);
    }

    private void printInfix(LogicExprTreeNode cur) {
        if (cur == null) {
            return;
        }
        if (cur.getKey() != '0' && cur.getKey() != '1') {
            System.out.print('(');
        }
        printInfix(cur.getLeft());

        System.out.print(cur.getKey());

        printInfix(cur.getRight());
        if (cur.getKey() != '0' && cur.getKey() != '1') {
            System.out.print(')');
        }
    }

    public boolean evaluate ( ){
        return evaluate(root);
    }

    private boolean evaluate(LogicExprTreeNode cur) {
        if (cur == null) {
            return false;
        }
        if (cur.getKey() == '0' || cur.getKey() == '1') {
            return cur.getKey() == '1';
        }
        boolean left = evaluate(cur.getLeft());
        boolean right = evaluate(cur.getRight());

        return cur.getKey() == '*' ? left && right: left || right;
    }

    public void clear ( ) {

    }
    public void showStructure (){

    }
}
