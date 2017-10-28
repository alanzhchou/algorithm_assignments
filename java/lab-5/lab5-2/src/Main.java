import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);

        int size = in.nextInt();
        for (int i=0; i<size; i++){
            String base = in.next();
            StringOperations test = new StringOperations(base);

            int ops = in.nextInt();
            StringBuffer op = new StringBuffer("");
            for (int j=0; j<ops; j++){
                op.append(in.next());
                if (op.charAt(0) == 'D'){
                    test.testOp(in.nextInt());
                }else if (op.charAt(0) == 'A'){
                    test.testOp(in.next().charAt(0),in.nextInt());
                }else{
                    test.testOp(op.toString(),in.nextInt(),in.nextInt());
                }
                op.delete(0,op.length());
            }
        }
    }
}


class StringOperations{
    private StringBuffer baseString;

    public StringOperations(){
        baseString = new StringBuffer("");
    }

    public StringOperations(String source){
        baseString = new StringBuffer(source);
    }

    public void setBaseString(String setSource){
        baseString.delete(0,baseString.length());
        baseString.append(setSource);
    }

    public void add(char x, int pos){
        baseString.insert(pos,x);
    }

    public void del(int pos){
        baseString.deleteCharAt(pos);
    }

    public String subStr(int pos1, int pos2){
        return baseString.substring(pos1,pos2+1);
    }

    public void reverseBetween(int pos1, int pos2){
        String subTemp = baseString.substring(pos1,pos2+1);
        StringBuffer temp = new StringBuffer();
        temp.append(subTemp);
        baseString.delete(pos1,pos2+1);
        baseString.insert(pos1,temp.reverse());
    }

    public void testOp(int pos){
        del(pos);
        System.out.println(baseString.toString());
    }

    public void testOp(String op, int pos1, int pos2){
        if (op.charAt(0) == 'R'){
            reverseBetween(pos1,pos2);
            System.out.println(baseString.toString());
        }else {
            System.out.println(subStr(pos1,pos2));
        }
    }

    public void testOp(char x, int pos){
        add(x,pos);
        System.out.println(baseString.toString());
    }

    public String toString(){
        return baseString.toString();
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

