import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        InputReader in = new InputReader(inputStream);

        int testCase = in.nextInt();
        for (int i=0; i<testCase; i++){
            int numberOfNodes = in.nextInt();

            for (int j=0; j<numberOfNodes; j++){
                int nodeValue = in.nextInt();
            }

            for (int k=0; k<numberOfNodes-1; k++){
                int nodeFather = in.nextInt();
                int nodeSelf = in.nextInt();
            }
        }
    }
}

class Btree {
    ArrayList<Node> nodes = new ArrayList<Node>();
    Node root = null;

    public Btree(){

    }
}


class Node{
    Boolean isRoot = false;
    int selfValue = 0;
    int sum;
    Node leftChild = null;
    Node rightChild = null;

    public Node(int self){
        selfValue = self;
        sum = selfValue;
    }

    public boolean hasChild(){
        return (leftChild != null) ? true : false;
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