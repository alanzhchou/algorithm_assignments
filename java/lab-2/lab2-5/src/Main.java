import java.util.Random;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        //生成随机数素组，用于作为各个位置的底数
        RandomArray random1 = new RandomArray(5,1,5);//1,2,3,4,5

        //设置底数，和起始位置
        Monopoly mono1 = new Monopoly(random1.getRandomArray(),2);

        //设置步数，此方法中内置toString，直接格式化输出查询信息
        mono1.forSteps(6);
        //应当输出为1,4,3,4,5
    }
}

//计算各位置，最终生成数字的结果
class Monopoly {
    private int[] basedNums;
    private int startPoint;
    private int steps;

    private int[] resultNums;

    //这三个方法，设置各个位置上的基数，和起始点
    //构造函数可无参数，有一个各个位置上的基数的数组参数，有两个一个为基数数组参数，另一个为起始点参数
    public Monopoly(){
    }
    public Monopoly(int[] basedNums){
        setBasedNums(basedNums);
    }
    public Monopoly(int[] basedNums,int startPoint){
        setBasedNums(basedNums);
        setStartPoint(startPoint);
    }
    public void setBasedNums(int[] basedNums){
        this.resultNums = basedNums;
        this.basedNums = basedNums;
        String result = "in the base of: \n";
        for (int i=0; i<basedNums.length; i++){
            result = result + String.valueOf(i+1) + "\t";
        }
        result +="\n";

        for (int i=0; i<basedNums.length; i++){
            result = result  + String.valueOf(basedNums[i]) + "\t";
        }
        result +="\n";
        System.out.println(result);
    }

    public void setStartPoint(int startPoint){
        this.startPoint = startPoint;
    }


    public void setSteps(int steps){
        this.steps = steps;
    }

    public int[] getResultNums(){
        return resultNums;
    }

    public void forSteps(int a){
        setSteps(a);
        int[] times = new int[basedNums.length];
        calResult(calTimes(times,steps));

        this.toString();
    }

    private int[] calTimes(int[] times,int step){
        int start = this.startPoint;
        int mod = this.basedNums.length;
        int circles = step/mod;
        int remin = step%mod;

        for (int i=0; i<mod; i++){
            times[i] = circles;
        }
        while (remin!=0){
            times[start-1]++;
            start++;
            if (start>=mod){
                start = start%mod;
            }
            remin--;
        }
        return times;
    }

    private void calResult(int[] times){
        for(int i=0; i<resultNums.length; i++){
            resultNums[i] = powerMod(basedNums[i],times[i],123456789);
        }
    }

    public String toString(){
        String result = "";
        result += "you start at " + String.valueOf(startPoint) + "\n";

        result += "the result is \n";

        for (int i=0; i<resultNums.length; i++){
            result = result + String.valueOf(resultNums[i]) + "\t";
        }
        result +="\n";

        System.out.println(result);
        return result;
    }

    //快速幂取模算法
    //由a*b % c =  ( ( a % c ) * ( b % c )  ) % c
    //得a ^ b % n = ( a % n ) ^ b % n;
    //递归:
    //b & 1 == 1时 a ^ b = ( a ^ ( b / 2 ) ) ^ 2 * a;    即b为奇数，当以下ans乘出一个a后即成偶数，直接进行对半，和平方的操作
    //b & 1 == 0时 a ^ b = ( a ^ ( b / 2 ) ) ^ 2;    即b为偶数
    private int powerMod(int base, int power, int mod)
    {
        int ans = 1;
        base = base % mod;
        while(power > 0) {
            if(power % 2 == 1){
                ans = (ans * base) % mod;
            }
            power = power/2;
            base = (base * base) % mod;
        }
        return ans;
    }
}


//生成不重复随机int数组的类，构造函数中依次传入长度，上限，下限，用getRandomArray返回改数组，也可以用toString格式化返回该数组信息
class RandomArray{
    private int[] randomArray;

    //构造方法，用于传入长度，上限和下限
    public RandomArray(int length,int min,int max){
        setRandomArray(length,min,max);
    }
    //这里接受长度，上下限，生成所需的不重复的数组，set到私有变量randomArray中
    private void setRandomArray(int length,int min,int max){
        Random rand = new Random();
        if (length > (max - min + 1) || max < min) {
            randomArray = null;
        }else {
            int[] result = new int[length];

            int count = 0;
            while(count < length) {
                int randnum = rand.nextInt(max) + min;

                boolean judge = true;
                for (int j = 0; j < length; j++) {
                    if(randnum == result[j]){
                        judge = false;
                        break;
                    }
                }
                if(judge){
                    result[count] = randnum;
                    count++;
                }
            }
            Arrays.sort(result);
            randomArray = result;
        }
    }

    public int[] getRandomArray(){
        return randomArray;
    }
    public String toString(){
        String result = "";
        for (int i=0; i<randomArray.length; i++){
            result = result + String.valueOf(randomArray[i]) + "\t";
        }
        System.out.println(result);
        return result;
    }
}