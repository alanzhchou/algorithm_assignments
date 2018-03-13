import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        NMMap nmMap = new NMMap();
        nmMap.init();
        System.out.println(nmMap);
    }
}

class NMMap{
    public static InputReader reader = new InputReader(System.in);
    private int width = 0;
    private int height = 0;
    private ArrayList<char[]> map = new ArrayList<char[]>();

    public void init(){
        initSize();
        initMap();
    }

    public int testPoints(Point point1,Point point2){
        return -1;
    }

    @Override
    public String toString() {
        StringBuffer mapInfo = new StringBuffer("");

        for (int i=0; i<height; i++) {
            char[] line = map.get(i);
            for (int j = 0; j < width; j++) {
                mapInfo.append(line[j] + " ");
            }
            mapInfo.append("\n");
        }

        String result = "NMMap\n" +
                        "width=" + width +
                        ", height=" + height +
                        "\n" + mapInfo;
        return result;
    }

    private void initSize(){
        try{
            this.width = NMMap.reader.nextInt();
            this.height = NMMap.reader.nextInt();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initMap(){
        StringBuffer buffer = new StringBuffer("");

        for (int i=0; i<height; i++){
            buffer.append(NMMap.reader.next());
            char[] line = new char[width];

            //true-r是路，false-w是墙
            for (int j=0; j<width; j++){
                line[j] = buffer.charAt(j);
            }
            buffer.delete(0,buffer.length());
            map.add(line);
        }
    }
}

class Point{
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = reader.read();
            }
            int x = 0;
            while (c > 32) {
                x = x * 10 + c - '0';
                c = reader.read();
            }
            return negative ? -x : x;
        }catch(IOException e){
            return  -1;
        }
    }
}


