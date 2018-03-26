import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        while (true){
            int clothNum = Topological.reader.nextInt();
            int compareNum = Topological.reader.nextInt();
            if (compareNum == clothNum && compareNum == 0){
                break;
            }else {
                Topological topological = new Topological(clothNum,compareNum);
                topological.getSortingInfo();
                topological.getResult();
//                System.out.println(topological.toString());
            }
        }
    }
}

class Topological{
    public static InputReader reader = new InputReader(System.in);
    public static PrintWriter writer = new PrintWriter(System.out);

    int clothNum = 0;
    int compareNum = 0;
    HashMap<Character,ArrayList> graph = new HashMap<Character,ArrayList>();
    int compareState = 0;
    int relations = 0;
    boolean[] catched;

    public Topological(int clothNum, int compareNum) {
        this.clothNum = clothNum;
        this.compareNum = compareNum;
        catched = new boolean[clothNum];
    }

    public void getSortingInfo(){
        StringBuffer buffer = new StringBuffer("");
        for (int i=0; i<compareNum; i++){
            buffer.append(reader.next());
            char larger = buffer.charAt(0);
            char smaller = buffer.charAt(2);
            catched[larger-'A'] = true;
            catched[smaller-'A'] = true;

            if (graph.get(smaller) == null || graph.get(smaller).indexOf(larger) == -1){
                ArrayList<Character> linkedPoints = graph.get(larger);
                if (linkedPoints == null){
                    linkedPoints = new ArrayList<Character>();
                    linkedPoints.add(smaller);
                    graph.put(larger,linkedPoints);
                }else if (graph.get(larger).indexOf(smaller) == -1){
                    graph.get(larger).add(smaller);
                }
            }else {
                compareState = 2;
                relations = i+1;
                break;
            }
            buffer.delete(0,3);
            relations = i+1;
        }

        for (int i = 0; i < catched.length; i++) {
            if (catched[i] == false && compareState == 0){
                compareState = 1;
                break;
            }
        }
        System.out.println(graph);
    }

    public void getResult(){
        if (compareState == 2){
            System.out.println("Inconsistency found after " + relations + " relations.");
        }else if (compareState == 1){
            System.out.println("Preference sequence cannot be determined.");
        }else{
            System.out.println("Preference sequence determined after " + relations + " relations: " + dfs() + ".");
        }
    }

    private String dfs(){
        HashMap visited = new HashMap<Character,Boolean>();
        StringBuffer buffer = new StringBuffer("");
        Stack stack = new Stack();

        Iterator iterator = graph.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            char key = (char) entry.getKey();

            if (visited.get(key) == null){
                stack.push(key);
                visited.put(key,true);

                while (!stack.empty()){
                    char top = (char) stack.peek();
                    ArrayList linkTo = graph.get(top);

                    if (linkTo == null){
                        buffer.append(stack.pop());
                    }else  {
                        for (int i = 0; i < linkTo.size(); i++) {
                            char sub = (char) linkTo.get(i);
                            if (visited.get(sub) == null) {
                                stack.push(sub);
                                visited.put(sub, true);
                                break;
                            }
                        }
                    }
                }
                System.out.println(buffer + "\n");
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return "Topological{" +
                "clothNum=" + clothNum +
                ", compareNum=" + compareNum +
                ", graph=" + graph +
                ", compareState=" + compareState +
                ", relations=" + relations +
                '}';
    }
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