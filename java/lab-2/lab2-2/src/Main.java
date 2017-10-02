import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        RandomArray random = new RandomArray(10,1,11);//生成一个指定范围的随机数组对象，并将其排好序
        Searcher test1 = new Searcher(random.getRandomArray());//返回的随机数组的get方法可获得该数组，

        test1.search(2);
        test1.search(30);
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
    private int searchArray[];//储存输入的有序数组，即查询的范围
    private int key;//储存查询的键值

    public Searcher(){}//构造方法一：无参数，之后可通过set来添加array
    public Searcher(int[] array){//构造方法二：直接set来添加array
        setSearchArray(array);
    }

    public void setSearchArray(int[] array){
        searchArray = array;
    }

    public int search(int key){//初始化搜索目标，返回搜索的结果：不存在则返回-1，存在则返回所在的index
        this.key = key;
        System.out.println(this.toString());//这里调用重写的toString方法，使得在查询的输入的同时即可格式化输出结果YES/NO
        return Arrays.binarySearch(searchArray,key);
    }

    public String toString(){
        String result = "In array of:\t";
        for (int i=0; i<searchArray.length; i++){
            result = result + String.valueOf(searchArray[i]) + "\t";
        }
        if (Arrays.binarySearch(searchArray,key) < 0){
            result = result + "\nNO! \nfor key: " + key + "; no this value in test array\n" ;
            //如下样式：
            //NO!
            //for key: 30; no this value in test array
        }else {
            result = result + "\nYES! \nfor key: " + key + ";\nits index is " + String.valueOf(Arrays.binarySearch(searchArray,key)) + "\n" ;
            //如下样式：
            //YES!
            //for key: 2;
            //its index is 1
        }
        return result;
    }
}


