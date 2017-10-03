import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //生成随机数组对象（传入值依次为数组长度，下限，上限），之后可用用getRandomArray（）返回该数组
        RandomArray rand1 = new RandomArray(10,2,15);

        //生成安排座位的对象，（这里直接传入了代表座位序号的数组，也可不直接传入参数，之后用setAvailableSeats传入数组）
        SeatArrangement testSeat1 = new SeatArrangement(rand1.getRandomArray());

        //传入学生数，格式化输出查询结果，本身返回maxSpacing（int）
        testSeat1.findMaxSpaceFor(7);
    }
}

//实现座位最大空隔查找的类别，
//构造时传入座位序号的数组，或不传入，之后用setAvailableSeats置入数组
//只公开两个方法：输入学生数查找最大空隔的方法findMaxSpaceFor，其本身包含toString方法，可直接格式化输出查询信息，本身返回值是int类型的maxSpacing
//toString方法，格式化输出查询信息
class SeatArrangement{
    private int[] availableSeats;
    private int  numberOfStudents;
    private int maxSpace = 1;

    //此三个函数用来构建对象，并赋值最初的，用来进行算法的可用位置数据的数组
    public SeatArrangement(){
    }
    public SeatArrangement(int[] availableSeats){
        setAvailableSeats(availableSeats);
    }
    public void setAvailableSeats(int[] availableSeats){
        this.availableSeats = availableSeats;
    }
    private void setNumberOfStudents(int numberOfStudents){
        this.numberOfStudents = numberOfStudents;
    }
    private void setMaxSpace(int maxSpace){
        this.maxSpace = maxSpace;
    }

    //供外界调用的public方法，传入学生数，格式化输出查询结果，本身返回maxSpacing（int）
    public int findMaxSpaceFor(int numberOfStudents){
        setNumberOfStudents(numberOfStudents);
        this.toString();

        find(numberOfStudents);
        return maxSpace;
    }

    //这里是实现set主要返回值：maxSpace的实际函数
    private void find(int numberOfStudents){
        int bigSpace = (availableSeats[availableSeats.length-1]-availableSeats[0])/(numberOfStudents-1);
        int smallSpace = 1;

        int space = 1;

        while (true){
            if (judge(bigSpace) == true){
                space = bigSpace;
                break;
            }else if (judge(bigSpace) == false &&judge(smallSpace) == false){
                space = -1;
                break;
            }else if (judge(bigSpace) == false &&judge(smallSpace) == true){
                int midSpace;
                space = smallSpace;
                if (bigSpace - smallSpace == 1){
                    midSpace = (bigSpace + smallSpace)/2 + 1;
                }else {
                    midSpace = (bigSpace + smallSpace)/2;
                }

//                System.out.println(String.valueOf(smallSpace) + String.valueOf(midSpace) + String.valueOf(bigSpace));
//                System.out.println(String.valueOf(judge(smallSpace)) + String.valueOf(judge(midSpace)) + String.valueOf(judge(bigSpace)));
                if (judge(midSpace) == true){
                    smallSpace = midSpace;
                }else {
                    bigSpace = midSpace;
                }
            }
        }
        setMaxSpace(space);
    }

    //这里是一个对传入的space进行检测的方法，按照此空隔可安排所有学生则返回true，反之为false
    private boolean judge(int testSpace){
        int counter = 1;
        int lastIndex = 0;
        for (int i=0; i<availableSeats.length; i++){
            if (availableSeats[i]-availableSeats[lastIndex] >= testSpace){
                lastIndex = i;
                counter++;
            }
        }
        boolean flag;
        if (counter >= numberOfStudents){
            flag = true;
        }else {
            flag = false;
        }
        return flag;
    }

    public String toString(){
        String result = "in the seats of number: \n";
        for (int i=0; i<availableSeats.length; i++){
            result = result + String.valueOf(availableSeats[i]) + "\t";
        }
        result = result + "\n" + "for seating students of " + String.valueOf(numberOfStudents) +"\n";
        result = result + "the max spacing is " + String.valueOf(maxSpace) + "\n";
        System.out.println(result);
        return result;
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
                int randnum = rand.nextInt(max-1) + min;

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