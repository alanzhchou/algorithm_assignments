import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main6 {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);

        int length = 0;
        int size = 0;

        int b = in.nextInt();
        for (int i=0; i<b; i++){
            length = in.nextInt();
            size = in.nextInt();
        }
        int[] a = new int[length];;

        int element = 0;
        for (int i=0; i<length; i++){
            element = in.nextInt();
            a[i] = element;
        }
//         = {1,3,2,5,4};
        getSubMinimum(a,size);
        getSubMaximum(a,size);
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
                System.out.print(count[i] + " ");
            }
        }else{
            for (int i=0; i<test.length; i++){
                System.out.print(test[i] + " ");
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
                System.out.print(count[i] + " ");
            }
        }else{
            for (int i=0; i<test.length; i++){
                System.out.print(test[i] + " ");
            }
        }
        System.out.println();
    }
}

class InputReader{
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream){
        reader = new BufferedReader(new InputStreamReader(stream),32768);
        tokenizer = null;
    }

    public String next(){
        while (tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch (IOException e){
                throw new RuntimeException();
            }
        }
        return  tokenizer.nextToken();
    }

    public int nextInt(){
        return Integer.parseInt(next());
    }
}
