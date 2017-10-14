import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        int[] A = {1,2,3};
        int[] B = {1,2,3,4,5};

        Conbin result = new Conbin(A,B);
        printArr(result.getSorted());

        int[] C = {1,1,2,2};
        int[] D = {1,1,2,2,3,3,4};
        Conbin result2 = new Conbin(C,D);
        printArr(result2.getSorted());
    }

    static void printArr(int[] a){
        for (int i=0; i<a.length; i++){
            System.out.print(a[i] + "\t");
        }
        System.out.println();
    }
}

class Conbin{
    private int[] A;
    private int[] B;
    private int[] C;

    public Conbin(int[] a, int[] b){
        this.A = a;
        this.B = b;
        this.C = new int[A.length + B.length];
    }

    public int[] getSorted(){
        int indexA = 0;
        int indexB = 0;
        int indexC = 0;

        while (indexA < A.length&&indexB < B.length){
            if (A[indexA] <= B[indexB]){
                C[indexC++] = A[indexA++];
            }else {
                C[indexC++] = B[indexB++];
            }
        }

        while (indexA < A.length) {
            C[indexC++] = A[indexA++];
        }
        while (indexB < B.length) {
            C[indexC++] = B[indexB++];
        }

        return C;
    }
}
