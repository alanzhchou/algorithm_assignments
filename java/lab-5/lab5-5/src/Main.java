import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        StringHash test = new StringHash();

        int size = in.nextInt();

        int trash = 0;
        StringBuffer temp = new StringBuffer("");
        for (int i=0; i<size; i++){
            trash = in.nextInt();
            temp.append(in.next());

            test.testStringHash(temp.toString());
            temp.delete(0,temp.length());
        }
    }
}

class StringHash{
    private KMP kmp;

    public StringHash(){
        kmp = new KMP();
    }

    private int getHashedValue(String test){
        int result = 0;

        for (int j=1; j<test.length()+1; j++){
            result += (j)*kmp.match(test,test.substring(0,j));
        }
        return result;
    }

    public void testStringHash(String test){
        System.out.println(getHashedValue(test));
    }
}

class KMP{
    public int[] next(String sub){
        int len = sub.length();
        int[] nextArray = new int[len];
        nextArray[0] = -1;
        if (sub.length()>1){
            nextArray[1] = 0;
        }

        int j = 0;
        for (int i=2; i<len; i++){
            while (j>0&&sub.charAt(j) != sub.charAt(i-1)){
                j = nextArray[j];
            }
            if (sub.charAt(j) == sub.charAt(i-1)){
                j++;
            }
            nextArray[i] = j;
        }
        return nextArray;
    }

    public int match(String father,String sub){
        int[] subNext = next(sub);
        int matchTimes = 0;

        int i = 0;
        boolean falg = false;
        while (i<father.length()){
            for (int j=0; j<sub.length();j++){
                if (father.charAt(i) != sub.charAt(j)){
                    i = i - subNext[j];
                    break;
                }
                if (j == sub.length()-1){
                    matchTimes++;
                    i = i-sub.length()+2;
                    break;
                }
                i++;
                if (i>=father.length()){falg = true;break;}
            }
            if (falg){break;}
        }
        return matchTimes;
    }

    public void testString(String father,String sub){
        System.out.println(match(father,sub));
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

    public String nextLine(){
        while (tokenizer == null || !tokenizer.hasMoreTokens()){
            try{
                tokenizer = new StringTokenizer(reader.readLine());
            }catch (IOException e){
                throw new RuntimeException();
            }
        }
        StringBuffer temp = new StringBuffer("");
        while (tokenizer.hasMoreTokens()){
            temp.append(tokenizer.nextToken());
            temp.append(" ");
        }
        temp.deleteCharAt(temp.length()-1);
        System.out.println(temp.toString());
        return  temp.toString();
    }
}