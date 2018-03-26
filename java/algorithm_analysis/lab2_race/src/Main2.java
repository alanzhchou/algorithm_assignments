import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : Alan
 * @version : 1.0
 * @serial : 2018/3/22
 * @since : Java_8
 */
public class Main2 {
    public static void main(String[] args) {
        Graph graph = new Graph();
        int minLength = -1;
        int index = 0;

        int nodeNum = Graph.reader.nextInt();
        for (int i=0; i<nodeNum; i++){
            int x = Graph.reader.nextInt();
            int y = Graph.reader.nextInt();

            if (minLength == -1){
                index = x*graph.width + y;
                minLength = graph.BFS(0,index);
            }else {
                if (minLength == 0){
                    minLength = -1;
                    break;
                }else {
                    minLength += graph.BFS(index,x*graph.width+y);
                }
                index = x*graph.width + y;
            }
        }
        System.out.println(minLength);
    }
}

class Graph{
    public static InputReader reader = new InputReader(System.in);
    int width;
    int height;
    HashMap<Integer,ArrayList<Integer>> points = new HashMap<Integer,ArrayList<Integer>>();
    Queue<Integer> queue = new LinkedList<Integer>();

    public Graph(){
        width = reader.nextInt();
        height = reader.nextInt();

        StringBuffer buffer = new StringBuffer("");
        for(int i=0; i<height; i++){
            buffer.append(reader.next());
            for(int j=0; j<width; j++) {
                if (buffer.charAt(j) == 'r'){
                    int index = i*width+j;
                    ArrayList<Integer> point = new ArrayList<Integer>();
                    points.put(index,point);
                    //上
                    if (i != 0 && points.get(index-width) != null){
                        point.add(index-width);
                        points.get(index-width).add(index);
                    }
                    //左
                    if (j !=0 && points.get(index-1) != null){
                        point.add(index-1);
                        points.get(index-1).add(index);
                    }
                }
            }
            buffer.delete(0,buffer.length());
        }
    }

    public int BFS(int start,int end){
        if (points.get(start).size() == 0 || points.get(end).size() == 0)
            return 0;
        int[] layerMap = new int[width*height];

        int minLength = 0;
        int layer = 0;

        queue.add(start);

        while (!queue.isEmpty()){
            if (start == end){
                minLength = layer;
                queue.clear();
                break;
            }
            start = queue.remove();
//            int y = start/width;
//            int x = start-width*y;
//            System.out.print("{" + x + " " + y + "}\t");

            ArrayList<Integer> linkedPoints = points.get(start);

            layer = layerMap[start];
            if (layer == 0){
                layerMap[start] = -1;
            }
            if (!linkedPoints.isEmpty()){
                int finalLayer = layer;
                linkedPoints.stream().forEach(q->{
                    if (layerMap[q] == 0){
                        queue.add(q);
                        layerMap[q] = finalLayer +1;
                    }
                });
            }
        }
//        System.out.println();
        return minLength;
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