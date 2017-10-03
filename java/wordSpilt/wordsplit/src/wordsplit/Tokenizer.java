package wordsplit;

import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
class main{
    public static void main(String args[]){
//        "C:\\Users\\LENOVO\\Desktop\\dickens.txt"
        Tokenizer a =new Tokenizer("C:\\Users\\LENOVO\\Desktop\\java_zh.txt");
        for (int i =0; i<a.getWords().length; i++){
            String b = a.nextToken();
        }
    }
}

public class Tokenizer {

	private String pathname;
    private String[] words;

	//重载构造函数，接受输入的文件路径名
    public Tokenizer(String pname){
        pathname = pname;
        try{//防止文件读取或建立错误，catch捕捉错误并打印
            //读取文件
            File filename = new File(pathname);
            FileInputStream is = new FileInputStream(filename);

            //建立一个输入流对象reader
            InputStreamReader reader = new InputStreamReader(is,"utf-8");

            //将文本读入
            BufferedReader br = new BufferedReader(reader);

            String line = "";
            line = br.readLine();
            //一次读入一行
            while(br.readLine() != null){
                line = line + br.readLine();
            }
            br.close();
            is.close();


/**         String regEx = "[//u4e00-//u9fa5]";
 * 判断有没有中文
 */
            String reg;

            if (line.getBytes().length == line.length()) {
                reg = "(,\\s+)|('\\s+)|(.(')+)|(;)|(.(\\\"))|(\\“)|(\\;)|(!)|(--)|(,)|(\\.)|((\\(|\\)))|(\\s+)|(\\n)";
            } else {
                reg ="(，\\s+)|(')|(’\\s+)|(。(‘)+)|(;)|(。)|(\\“)|(！)|(，)|(,)|(、)|(：)|(\\.\\s+)|(「)|(」)|(“)|(”)|((\\(|\\)))|(\\s+)|(\\n)";
            }
            words = line.split(reg);


        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private int wordCount = 0;
    public String nextToken(){
        if (wordCount < words.length){
            System.out.println(words[wordCount]);
            String temp = words[wordCount];
            wordCount++;
            return temp;
        }else {
            System.out.println("NULL");
            return null;
        }
    }

    public String[] getWords() {
        return words;
    }

}
