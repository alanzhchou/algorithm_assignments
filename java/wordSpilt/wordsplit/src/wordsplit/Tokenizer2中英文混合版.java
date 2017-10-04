package wordsplit;

import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

class main{
    public static void main(String args[]){
        // "C:\\Users\\LENOVO\\Desktop\\dickens.txt"，可自行修改路径名
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

            //这里对line进行预处理，碰到中文字符或者前面有中文字符，就在拆分的字符串中加一个“*”符号，便于之后用正则切割
            char [] c = line.toCharArray();
            StringBuffer newStr = new StringBuffer();
            int incept = 0;
            for (int i = 0; i < c.length; i++) {
                String ch = "" + c[i];
                if ((c[i]>=0x4E00 && c[i]<=0x9FA5)){
                    newStr.append("*").append(ch);
                } else {
                    if (i!=0&&c[i-1]>=0x4E00 && c[i-1]<=0x9FA5){
                        newStr.append("*").append(ch);
                    }
                    else{
                        newStr.append(ch);
                    }
                }
                incept = ch.getBytes().length;
            }
            line = newStr.toString();

            //对中英文应用不同的正则来分割
            String reg;
            if (line.getBytes().length == line.length()) {
                reg = "(\\*)|(,\\s+)|('\\s+)|(.(')+)|(;)|(.(\\\"))|(\\“)|(\\;)|(!)|(--)|(,)|(\\.)|(\\[|\\])|((\\(|\\)))|(\\s+)|(\\\n)|(\\s)";
            } else {
                reg ="(\\*)|(，\\s+)|(')|(’\\s+)|(。(‘)+)|(;)|(。)|(\\“)|(！)|(，)|(,)|(、)|(：)|(\\.\\s+)|(「)|(」)|(“)|(”)|((\\(|\\)))|(\\s+)|(\\\n)|(\\s)";
            }
            //这里暂时用words2保存一下，之后可能进行其他处理，处理完之后，将words数组指针指向操作完的words2即可
            String[] words2 = line.split(reg);


            words = words2;
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    //一个一个输出切割好的字符串
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

