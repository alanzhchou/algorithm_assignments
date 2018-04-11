import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

class OfficeHour{
    public static InputReader reader = new InputReader(System.in);


}

class InputReader{
    private BufferedReader reader;
    private StringTokenizer tokenizer;

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
        try {
            int c = reader.read();
            while (c <= 32) {
                c = reader.read();
            }
            int x = 0;
            while (c > 32) {
                x = x * 10 + c - '0';
                c = reader.read();
            }
            return x;
        }catch(IOException e){
            return  -1;
        }
    }
}