/**
 * Created by ZH-AlanChou on 2017/10/13.
 */
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Arrays.*;


/*
此类用来读取某个文件内的内容，并简单分词
1.构造方法需传入一个String fileName，之后初始化读取文件存储在内部的fileContent变量中（private）
2.提供一个public的nextToken()方法，每次调用返回一个分词（按分词结果依次输出）
3.提供一个public的getAllTokens返回该文件中包含的分词组成的字符串数组
 */
public class Tokenizer {
    private String fileContent = new String("");
    private ArrayList allToken = new ArrayList();
    private int pos = 0;

    public Tokenizer(String fileName)
            throws FileNotFoundException,
            UnsupportedEncodingException,
            IOException {
        // Constructor - reads and loads in memory.
        char[] cbuf = new char[200];
        int    charsRead;
        InputStreamReader isr = new InputStreamReader(new
                FileInputStream(fileName), "UTF-8");
        while ((charsRead = isr.read(cbuf, 0, 200)) != -1) {
            fileContent += new String(java.util.Arrays.copyOfRange(cbuf,
                    0, charsRead));
        }
        isr.close();
        // System.out.println(fileContent);
    }

    public String nextToken() {
        String  tok = "";
        char    c;
        boolean last_was_quote = false;

        try {
            while (! Character.isLetterOrDigit(fileContent
                    .subSequence(pos, pos+1)
                    .charAt(0))) {
                pos++;
            }
            c = fileContent.subSequence(pos,pos+1).charAt(0);
            while (Character.isLetterOrDigit(c)
                    || (last_was_quote = (c == '\''))) {
                tok += fileContent.substring(pos,pos+1);
                pos++;
                c = fileContent.subSequence(pos,pos+1).charAt(0);
            }
            // Remove ending quote
            while (tok.charAt(tok.length()-1) == '\'') {
                tok = tok.substring(0, tok.length()-1);
            }
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
        this.allToken.add(tok.toLowerCase());
        return tok.toLowerCase();
    }

    public String[] getAllTokens(){
        String[] s = new String[allToken.size()];
        s = (String[]) allToken.toArray(s);
        return s;
    }
}

