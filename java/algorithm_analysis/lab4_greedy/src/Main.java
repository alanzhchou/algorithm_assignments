import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        GreedyDemo demo = new GreedyDemo();
        demo.setAllParameters();
        System.out.println(demo.getMinTimeInAllTrip());
    }
}


class GreedyDemo{
    public static InputReader reader = new InputReader(System.in);

    //parameters for input
    private int airportNum;
    private int studentNum;
    private int ejectorNum;
    private ArrayList studentTimePlan = new ArrayList();

    //parameters for Temper calculating
    private int[] timeBetween;
    private int[] arrivalCounter;
    private int[] finalArrivals;
    private int[] arrivalTimeS;

    //parameters for output
    private int fullTime;
    private int minTime;

    public void setAllParameters(){
        airportNum = reader.nextInt();
        studentNum = reader.nextInt();
        ejectorNum = reader.nextInt();

        timeBetween = new int[airportNum-1];
        for (int i=0; i<timeBetween.length; i++){
            timeBetween[i] = reader.nextInt();
        }

        arrivalCounter = new int[airportNum];

        finalArrivals = new int[airportNum];
        for (int i = 0; i < studentNum; i++) {
            int[] oneStudent = new int[3];
            oneStudent[0] = reader.nextInt();
            oneStudent[1] = reader.nextInt();
            oneStudent[2] = reader.nextInt();

            finalArrivals[oneStudent[1]-1] = oneStudent[0]>finalArrivals[oneStudent[1]-1]?oneStudent[0]:finalArrivals[oneStudent[1]-1];

            arrivalCounter[oneStudent[2]-1]++;

            studentTimePlan.add(oneStudent);
        }
        setFullTime();
        setMinTime();
    }

    public String getMinTimeInAllTrip(){
        return String.valueOf(minTime);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < studentTimePlan.size(); i++) {
            buffer.append("[");
            int[] student = (int[]) studentTimePlan.get(i);
            for (int j = 0; j < student.length; j++) {
                buffer.append(student[j] + " ");
            }
            buffer.deleteCharAt(buffer.length()-1);
            buffer.append("]");
        }

        return "GreedyDemo{" + "\n" +
                " airportNum=" + airportNum + "\n" +
                " studentNum=" + studentNum + "\n" +
                " ejectorNum=" + ejectorNum + "\n" +
                " timeBetween=" + Arrays.toString(timeBetween) + "\n" +
                " arrivalCounter=" + Arrays.toString(arrivalCounter) + "\n" +
                " finalArrivals=" + Arrays.toString(finalArrivals) + "\n" +
                " arrivalTimeS=" + Arrays.toString(arrivalTimeS) + "\n" +
                " studentTimePlan=" + buffer.toString() + "\n" +
                " fullTime=" + fullTime + "\n" +
                " minTime=" + minTime + "\n" +
                '}';
    }

    private void setFullTime(){
        int full = 0;
        arrivalTimeS = new int[airportNum];
        for (int i = 1; i < airportNum; i++) {
            arrivalTimeS[i] = arrivalTimeS[i-1] > finalArrivals[i-1]?
                    arrivalTimeS[i-1] + timeBetween[i-1] : finalArrivals[i-1] + timeBetween[i-1];
        }

        for (int i = 0; i < studentTimePlan.size(); i++) {
            int[] student = (int[]) studentTimePlan.get(i);
            full += arrivalTimeS[student[2]-1] - student[0];
        }
        fullTime = full;
    }

    private void setMinTime(){
        int min = fullTime;
        int[] counterClone = arrivalCounter.clone();
        int ejectorLeft = ejectorNum;

        while (ejectorLeft > 0){
            int tempMax = 0;
            int maxIndex = -1;
            for (int j = 0; j < counterClone.length; j++) {
                if (counterClone[j]>tempMax){
                    tempMax = counterClone[j];
                    maxIndex = j;
                }
            }
            if (ejectorNum <= timeBetween[maxIndex-1]){
                min -= ejectorNum * counterClone[maxIndex];
                ejectorLeft = 0;
            }else {
                min -= timeBetween[maxIndex-1] * counterClone[maxIndex];
                ejectorLeft = ejectorNum - counterClone[maxIndex];
                counterClone[maxIndex] = 0;
            }
        }
        minTime = min;
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