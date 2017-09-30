import java.math.*;

public class Main {
    public static void main(String[] args) {
        long bottomLimit = 50;
        long upLimit = 100;
        LuckNumber luckNumber1 = new LuckNumber(bottomLimit,upLimit);

        System.out.printf("Between %d and %d, There are %d lucknumbers.\n"
                        ,bottomLimit,upLimit,luckNumber1.getCount());

       //or use code blow to print all lucknumbers
        for (int i=0; i<luckNumber1.getNumbers().length; i++){
            System.out.println(luckNumber1.getNumbers()[i]);
        }
        System.out.println("共 "+luckNumber1.getNumbers().length+" 个幸运数\n");

    }
}
/*
处理幸运数字的类，构造方法接受两个long型数字，
public方法如下：
getNumbers()：return long[]; 返回储存所有幸运数的数组
getCount()：return long; 返回幸运数的个数
 */
class LuckNumber{
    private long bottomLimit,upLimit;//代表搜索的上下限
    private int count;//代表幸运数字的个数
    private long[] Numbers;//储存所有幸运数

    public LuckNumber(long bottomLimit,long upLimit){
        this.upLimit = upLimit;
        this.bottomLimit =bottomLimit;
        countLuckNumber(bottomLimit,upLimit);
    }

    //计算幸运数字的个数
    private void countLuckNumber(long bottomLimit,long upLimit){
            int count = 0;
            for (long i=bottomLimit; i<upLimit+1; i++){
                if (isLuckNumber(i)){
                    count++;
                }
            }

            long[] nums = new long[count];
            int getnum = 0;
            for (long i=bottomLimit; i<upLimit+1; i++){
                if (isLuckNumber(i)){
                    nums[getnum] = i;
                    getnum++;
                }
            }
            setNumbers(nums);
            setCount(count);
    }
    //判断某个long数字是否为幸运数字，是返回true,否返回false
    private boolean isLuckNumber(long a){
        int[] array = toIntArray(a);
        int[] inverse = inverseArray(toIntArray(a));

        for (int i=0; i<inverse.length; i++){
            if (array[i] != inverse[i]){
                return false;
            }
        }
        return true;
    }
    //将代表长整型数字的数组进行“180度“旋转后输出
    private int[] inverseArray(int[] a){
        int[] temp = new int[a.length];
        for (int i=0; i<a.length; i++){
            temp[a.length-i-1] = inverseNumber(a[i]);
        }
        return temp;
    }
    //将长整型数字转化为数组表示
    private int[] toIntArray(long a){
        int countNumber = 0;
        long temp = a;long temp2 = a;
        while(temp>0){
            countNumber++;
            temp = temp/10;
        }

        int[] toInt =new int[countNumber];
        for (int i=0; i<toInt.length; i++){
            toInt[i] = (int) (a-(a/10*10));
            a = a/10;
        }
        return toInt;
    }
    //输出某一数字”180度”旋转后的数字
    private int inverseNumber(int a){
        int temp = 0;
        switch (a){
            case 0:temp=0;break;
            case 1:temp=1;break;
            case 2:temp=10;break;
            case 3:temp=11;break;
            case 4:temp=12;break;
            case 5:temp=13;break;
            case 6:temp=9;break;
            case 7:temp=14;break;
            case 8:temp=8;break;
            case 9:temp=6;break;
        }
        return temp;
    }
    //储存所有幸运数
    private void setNumbers(long[] a){
        Numbers = a;
    }
    //返回所有幸运数
    public long[] getNumbers(){
        return  Numbers;
    }
    //储存幸运数个数
    private void setCount(int count){
        this.count = count;
    }
    //返回储存所有幸运数的数组
    public long getCount(){

        return count;
    }
}