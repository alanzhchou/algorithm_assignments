public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}


class Monopoly {
    private int[] basedNums;
    private int[] resultNums;
    private int[] startPoint;
    private int steps;

    //这三个方法，设置各个位置上的基数
    public Monopoly(){
    }
    public Monopoly(int[] basedNums){
        this.basedNums = basedNums;
    }
    public void setBasedNums(int[] basedNums){
        this.basedNums = basedNums;
    }







    //快速幂取模算法
    //由a*b % c =  ( ( a % c ) * ( b % c )  ) % c
    //得a ^ b % n = ( a % n ) ^ b % n;
    //递归:
    //b & 1 == 1时 a ^ b = ( a ^ ( b / 2 ) ) ^ 2 * a;    即b为奇数，当以下ans乘出一个a后即成偶数，直接进行对半，和平方的操作
    //b & 1 == 0时 a ^ b = ( a ^ ( b / 2 ) ) ^ 2;    即b为偶数
    private int PowerMod(int a, int b, int c)
    {
        int ans = 1;
        a = a % c;
        while(b>0) {
            if(b % 2 == 1){
                ans = (ans * a) % c;
            }
            b = b/2;
            a = (a * a) % c;
        }
        return ans;
    }
}
