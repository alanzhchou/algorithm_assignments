//邻接矩阵形式计算


//import java.io.*;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) throws InterruptedException {
//        graph();
//
//        int minLength = -1;
//        int index = 0;
//
//        int nodeNum = reader.nextInt();
//        for (int i=0; i<nodeNum; i++){
//            int x = reader.nextInt();
//            int y = reader.nextInt();
//
//            if (minLength != -1){
//                if (minLength == 0){
//                    minLength = -1;
//                    break;
//                }else {
//                    minLength += BFS(index,x*width+y);
//                }
//                index = x*width + y;
//            }else {
//                index = x*width + y;
//                minLength = BFS(0,index);
//            }
//        }
//        System.out.println(minLength);
//    }
//
//    public static InputReader reader = new InputReader(System.in);
//
//    static int width = 0;
//    static int height = 0;
//    static int numbers = 0;
//    static boolean[] graph;
//    static boolean[][] matrix;
//    static Queue<Integer> queue = new LinkedList<Integer>();
//    static int[] layerMap;
//
//    public static void graph(){
//        width = reader.nextInt();
//        height = reader.nextInt();
//        numbers = width*height;
//
//        graph = new boolean[numbers];
//        matrix = new boolean[numbers][numbers];
//        layerMap = new int[numbers];
//
//        int index = 0;
//
//        for(int i=0; i<height; i++){
//            String buffer = reader.next();
//            for(int j=0; j<width; j++) {
//                if (buffer.charAt(j) == 'r'){
//                    index = i*width+j;
//                    graph[index] = true;
//
//                    if (j != 0 && graph[index-1]){
//                        matrix[index-1][index] = true;
//                        matrix[index][index-1] = true;
//                    }
//                    if (i != 0 && graph[index-width]){
//                        matrix[index-width][index] = true;
//                        matrix[index][index-width] = true;
//                    }
//                }
//            }
//        }
//    }
//
//    public static int BFS(int start,int end){
//        int layer = 0;
//        int minStep = 0;
//        for (int i=0; i<layerMap.length; i++){
//            layerMap[i] = 0;
//        }
//
//        queue.add(start);
//
//        while (!queue.isEmpty()){
//            if (start == end){
//                minStep = layer;
//                queue.clear();
//                break;
//            }
//            start = queue.remove();
//
//            boolean[] adjacentList = matrix[start];
//            layer = layerMap[start];
//
//            for (int i=0; i<numbers; i++){
//                if (adjacentList[i] && layerMap[i] == 0){
//                    queue.add(i);
//                    layerMap[i] = layer+1;
//                }
//            }
//        }
//        return minStep;
//    }
//}
//
//class InputReader{
//    private BufferedReader reader;
//    private StringTokenizer tokenizer;
//
//    public InputReader(InputStream stream){
//        reader = new BufferedReader(new InputStreamReader(stream),32768);
//        tokenizer = null;
//    }
//
//    public String next(){
//        while (tokenizer == null || !tokenizer.hasMoreTokens()){
//            try{
//                tokenizer = new StringTokenizer(reader.readLine());
//            }catch (IOException e){
//                throw new RuntimeException();
//            }
//        }
//        return  tokenizer.nextToken();
//    }
//
//    public int nextInt(){
//        try {
//            int c = reader.read();
//            while (c <= 32) {
//                c = reader.read();
//            }
//            boolean negative = false;
//            if (c == '-') {
//                negative = true;
//                c = reader.read();
//            }
//            int x = 0;
//            while (c > 32) {
//                x = x * 10 + c - '0';
//                c = reader.read();
//            }
//            return negative ? -x : x;
//        }catch(IOException e){
//            return  -1;
//        }
//    }
//}