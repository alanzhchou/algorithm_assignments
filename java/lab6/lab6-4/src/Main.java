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

        //number of testcases
        int times = in.nextInt();
        for (int t=0; t<times; t++){
            int nodeNumber = in.nextInt();

            ArrayList<Integer> inOrder = new ArrayList<Integer>();
            ArrayList<Integer> postOrder = new ArrayList<Integer>();
            for (int i=0; i<nodeNumber; i++){
                int inOrderNode = in.nextInt();
                inOrder.add(inOrderNode);
            }
            for (int i=0; i<nodeNumber; i++){
                int postOrderNode = in.nextInt();
                postOrder.add(postOrderNode);
            }

            PreOrder exam = new PreOrder(inOrder,postOrder);
            exam.printPre();
        }
    }
}

class PreOrder{
    private ArrayList<Integer> preOrder;
    private ArrayList<Integer> inOrder;
    private ArrayList<Integer> postOrder;

    public PreOrder(ArrayList<Integer> inOrder,ArrayList<Integer> postOrder){
        this.inOrder = inOrder;
        this.postOrder = postOrder;
        this.preOrder = new ArrayList<Integer>();
        setPreOrder(inOrder,postOrder);
    }

    private int setPreOrder(ArrayList<Integer> inOrder,ArrayList<Integer> postOrder){
        if (inOrder.size() == 0){
            return 1;
        }
        preOrder.add(postOrder.get(postOrder.size()-1));

        ArrayList<Integer> inOrderLeft = new ArrayList<Integer>();
        ArrayList<Integer> postOrderLeft = new ArrayList<Integer>();

        ArrayList<Integer> inOrderRight = new ArrayList<Integer>();
        ArrayList<Integer> postOrderRight = new ArrayList<Integer>();

        int rootIndex = inOrder.indexOf(postOrder.get(postOrder.size()-1));
        for (int i=0; i<rootIndex; i++){
            inOrderLeft.add(inOrder.get(i));
        }
        for (int i=rootIndex+1; i<inOrder.size(); i++){
            inOrderRight.add(inOrder.get(i));
        }

        for (int i=0; i<rootIndex; i++){
            postOrderLeft.add(postOrder.get(i));
        }
        for (int i=rootIndex; i<inOrder.size()-1; i++){
            postOrderRight.add(postOrder.get(i));
        }

        setPreOrder(inOrderLeft,postOrderLeft);
        setPreOrder(inOrderRight,postOrderRight);
        return 1;
    }

    public void printPre(){
        for (int i=0; i<preOrder.size(); i++){
            System.out.print(preOrder.get(i) + " ");
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