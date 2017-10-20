import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] a = {1,3,2,5,4};
        getSubMinimum(a,3);
        getSubMaximum(a,3);
    }

    static void getSubMaximum(int[] test,int k){
        if (k != test.length){
            int[] count = new int[test.length-k+1];

            for (int i=0; i<count.length; i++){
                int max = test[i];
                for (int j=i; j<i+k; j++){
                    max = Math.max(max,test[j]);
                }
                count[i] = max;
            }

            for (int i=0; i<count.length; i++){
                System.out.print(count[i] + "\t");
            }
        }else{
            for (int i=0; i<test.length; i++){
                System.out.print(test[i] + "\t");
            }
        }
        System.out.println();
    }

    static void getSubMinimum(int[] test,int k){
        if (k != test.length){
            int[] count = new int[test.length-k+1];

            for (int i=0; i<count.length; i++){
                int min = test[i];
                for (int j=i; j<i+k; j++){
                    min = Math.min(min,test[j]);
                }
                count[i] = min;
            }

            for (int i=0; i<count.length; i++){
                System.out.print(count[i] + "\t");
            }
        }else{
            for (int i=0; i<test.length; i++){
                System.out.println(test[i] + "\t");
            }
        }
        System.out.println();
    }


}

