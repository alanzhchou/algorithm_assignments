public class Main {
    public static void main(String[] args) {
        Squares testSquares = new Squares(10);
        testSquares.printAddition();
        System.out.println("sum of squares is " + testSquares.getSum());
    }
}


class Squares{
    private long sum = 0;
    private int counter = 0;

    public Squares(int n){
        if(setCounter(n) == 1){
            System.out.println("success!");
            setSum(n);
        }else {
            System.out.println("fail: n <= 0");
        }
    }


    private int setCounter(int n){
        if (n>0){
            counter = n;
            return 1;
        }
        return -1;
    }

    public int getCounter(){
        return counter;
    }

    private void setSum(int n){
        long a = (long)n;
        sum = a*(a+1)*(2*a+1)/6;
    }

    public long getSum(){
        return sum;
    }

    public void printAddition(){
        if (counter <= 4){
            for (int i=1; i<= counter; i++) {
                System.out.printf("%d^2 ", i);
                if (i != counter){
                    System.out.printf(" + ");
                }
            }
            System.out.println(" = ?");
        }else {
            System.out.printf("1^2 + 2^2 + 3^2 + ... + %d^2 = ?\n",counter);
        }
    }
}