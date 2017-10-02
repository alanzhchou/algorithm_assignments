import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        RandomArray random = new RandomArray(10,1,11);
        Searcher test1 = new Searcher(random.getRandomArray());

        test1.search(2);
        System.out.println(test1.toString());

        test1.search(30);
        System.out.println(test1.toString());
    }
}


//生成一个随机数的整型数组，并排序返回
class RandomArray{
    private int[] randomArray;

    public RandomArray(int length,int bottom,int top){
        Random rand = new Random();
        int a[] = new int[length];

        for (int i=0; i<a.length; i++){
            a[i] = rand.nextInt(top)+bottom;
            for (int j=0; j<i; j++){
                if (a[i] == a[j]){
                    a[i] = rand.nextInt(top)+bottom;
                }else {}
            }
        }
        Arrays.sort(a);
        randomArray = a;
    }

    public int[] getRandomArray(){
        return randomArray;
    }

    public String toString(){
        String result = "";
        for (int i=0; i<randomArray.length; i++){
            result = result + String.valueOf(randomArray[i]) + "\t";
        }
        return result;
    }
}

class Searcher{
    private int searchArray[];
    private int key;

    public Searcher(){}
    public Searcher(int[] array){
        setSearchArray(array);
    }

    public void setSearchArray(int[] array){
        searchArray = array;
    }

    public int search(int key){
        this.key = key;
        return Arrays.binarySearch(searchArray,key);
    }

    public String toString(){
        String result = "In array of:\t";
        for (int i=0; i<searchArray.length; i++){
            result = result + String.valueOf(searchArray[i]) + "\t";
        }
        if (Arrays.binarySearch(searchArray,key) < 0){
            result = result + "\nNO! \nfor key: " + key + "; no this value in test array\n" ;
        }else {
            result = result + "\nYES! \nfor key: " + key + ";\nits index is " + String.valueOf(search(key)) + "\n" ;
        }
        return result;
    }
}


