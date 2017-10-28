import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);
        KMP kmp = new KMP();

        int size = in.nextInt();

        int a = 0;
        StringBuffer father = new StringBuffer("");
        int b = 0;
        StringBuffer sub = new StringBuffer("");

        for (int i=0; i<size; i++){
            a = in.nextInt();
            father.append(in.next());
            b = in.nextInt();
            sub.append(in.next());

            kmp.testString(father.toString(),sub.toString());

            father.delete(0,father.length());
            sub.delete(0,sub.length());
        }
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