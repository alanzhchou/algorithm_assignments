import java.util.Arrays;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
            //生成长度为10，元素为1-10的随机数组；即1,2,3,4,5,6,7,8,9,10
            //其中RandomArray类中setRandomArray方法，已注释排序语句，所以输出为乱序数组，若需输出有序数组，去除该注释即可
        RandomArray random1 = new RandomArray(10,1,10);

        Twin test1 = new Twin(random1.getRandomArray());

            //置入键值，用于查询
        test1.twinOf(8);//本身会返回twins的数量（int）
            //格式化输出twinOf置入数字后的查询信息
        System.out.println(test1.toString());
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
            //Arrays.sort(result);
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
        return result;
    }
}

//实现查找输出twins数量的类，构造函数传入任意数组（可有序，也可无序，会先将数组排序后存储），构造函数也可为空，之后用setFindTwin来初始化
//通过调用类的twinOf函数来置入键值，用于查询，twinOf本身会直接返回twins的数量
//提供其他public方法：toString方法，格式化输出twinOf置入数字后的查询信息
class Twin{
    private int[] findTwin;
    private int key;
    private int number = 0;

    //三个public方法用来构造对象，设置对象中的用来查询的数组
    public Twin(){
    }
    public Twin(int[] findTwin){
        setFindTwin(findTwin);
    }
    public void setFindTwin(int[] findTwin){
        Arrays.sort(findTwin);
        this.findTwin = findTwin;
    }

    //这里是实现查找并返回的实际算法，输入查询键值keyNum，返回twin的对数
    public int twinOf(int keyNum){
        setKey(keyNum);
        setNumber(find(keyNum));
        return number;
    }

    private int find(int key){
        //此处视为了在查找之前就先把大于等于的部分去除，从而减少查找的次数
        int searchDomain = -1;
        for (int i=0; i<findTwin.length; i++){
            if (findTwin[i] >= key){
                searchDomain = i;
                break;
            }
        }
        if (searchDomain == -1){
            searchDomain = findTwin.length;
        }

        int num = 0;
        for (int i=0; i<searchDomain; i++){
            //这里通过引入可指定范围的二分查找，只搜索在数组该项后的数据，避免了重复查找
            if (search(findTwin,i+1,searchDomain,key-findTwin[i]) >= 0){
                num++;
            }
        }
        return num;
    }

    //这里借用了Arrays中的静态方法,
    private int search(int[] a, int fromIndex, int toIndex,int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    private void setNumber(int num){
        this.number = num;
    }

    private void setKey(int key){
        this.key = key;
    }

    public String toString(){
        String result = "in the array of: ";
        for (int i=0; i<findTwin.length; i++){
            result = result + String.valueOf(findTwin[i]) + "\t";
        }
        if (number > 1){result = result + "\nfor the key of: " + key +"; there are " + number + " pairs of twins\n";}
        else if (number == 1){result = result + "\nfor the key of: " + key +"; there are " + number + " pair of twins\n";}
        else if (number == 0){result = result + "\nfor the key of: " + key +"; there are no twins\n";}
        return result;
    }
}