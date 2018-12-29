public class Main {
    public static void main(String[] args) {
        DiagMatrix d = new DiagMatrix(5);
        try {
            d.put(1, 1, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println(d.get(1,1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
class DiagMatrix {
    private final int degree;
    int []matrix;

    public DiagMatrix(int degree) {
        this.degree = degree;
        matrix = new int[degree];
    }

    public void put(int i, int j, int value) throws Exception {
        if ((i != j) || i<0 || j<0 || i>(degree-1) || j>(degree-1)) {
            throw new Exception("invalid index");
        }
        matrix[i] = value;
    }

    public int get(int i, int j) throws Exception {
        if (i<0 || j<0 || i>(degree-1) || j>(degree-1)) {
            throw new Exception("invalid index");
        }
        if (i != j) return 0;
        return matrix[j];
    }

}
class AntiDiagMatrix {
    private final int degree;
    int []matrix;

    public int getDegree() {
        return degree;
    }

    public AntiDiagMatrix(int degree) {
        this.degree = degree;
        matrix = new int[degree];
    }

    public AntiDiagMatrix multWithDiag(AntiDiagMatrix b) throws Exception {
        if (degree != b.getDegree()) {
            throw new Exception("unmatching degrees");
        }
        AntiDiagMatrix result = new AntiDiagMatrix(degree);
        for (int i=0; i<degree; i++) {
            result.put(i,degree-1-i, matrix[i]*b.get(i,i));
        }
        return result;
    }
    public DiagMatrix multWithAntiDiag(AntiDiagMatrix b) throws Exception {
        if (degree != b.getDegree()) {
            throw new Exception("unmatching degrees");
        }
        DiagMatrix result = new DiagMatrix(degree);
        for (int i=0; i<degree; i++) {
            result.put(i,i, matrix[degree-1-i] * b.get(degree-1-i, i));
        }
        return result;
    }

    public void put(int i, int j, int value) throws Exception {
        if ((i + j != degree-1) || i<0 || j<0 || i>(degree-1) || j>(degree-1)) {
            throw new Exception("invalid index");
        }
        matrix[j] = value;
    }

    public int get(int i, int j) throws Exception {
        if ((i + j != degree-1) || i<0 || j<0 || i>(degree-1) || j>(degree-1)) {
            throw new Exception("invalid index");
        }
        return matrix[j];
    }
}
