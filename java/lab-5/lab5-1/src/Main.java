import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);

        int size = 0;

        while (true){
            size = in.nextInt();
            try {
                if (size>10||size<1){
                    throw new Exception("");
                }else {
                    break;
                }
            }catch (Exception e){
            }
        }

        for (int i=0; i<size;){
            try {
                String test = in.next();
                if (testStr(test) == false){
                    throw new Exception("");
                }else {
                    subNum(test);
                    i++;
                }
            }catch (Exception e){
            }
        }
    }

    static int subNum(String str){
        int noEmpty = 0;
        for (int i=0; i<str.length(); i++){
            if (str.charAt(i) != ' '){
                noEmpty++;
            }
        }
        int numbers = noEmpty*(noEmpty+1)/2;
        System.out.println(numbers);
        return numbers;
    }

    static boolean testStr(String a){
        if (a.length()>1000||a.length()<=0){
            return false;
        }else {
            for (int i=0; i<a.length(); i++){
                if ((int)a.charAt(i)<97||(int)a.charAt(i)>122){
                    return false;
                }
            }
        }
        return true;
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

