import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ZH-AlanChou on 2017/10/13.
 */

/*
此方法用来建立每个路径下，文件所存储的所有字库，用键值对来存储，
键字符为库的名字，值为对应的语言词库的字符串数组。
 */
public class WordsLibrary {
    private String[] fileName;
    private String[] filePath;
    private int size = 0;

    private HashMap path_mapping  = new HashMap();
//    private HashMap file_to_lib = new HashMap();

    public WordsLibrary() throws IOException {
        PathMapping mapping = new PathMapping();

        this.path_mapping = mapping.getPathMapping();
        this.size = mapping.getSize();

        this.fileName = new String[this.size];
        this.filePath = new String[this.size];

        fill_fileName_and_filePath();
    }

    //操作哈希表和此类中的两个
    private void fill_fileName_and_filePath(){
        int index = 0;
        Iterator iter_2 = path_mapping.entrySet().iterator();
        while (iter_2.hasNext()) {
            Map.Entry entry = (Map.Entry) iter_2.next();
            filePath[index] = entry.getValue().toString();
            fileName[index] = entry.getKey().toString();
            index++;
        }
    }

    public String toString(){
        for (int i=0;i<this.size;i++){
            System.out.println(fileName[i] + "-->" + filePath[i]);
        }
        return null;
    }

//    public void getAllToken(String name) throws ValueException, IOException {
//        int index = getindex(name);
//        Tokenizer myTokens = new Tokenizer(filePath[index]);
//        String a = myTokens.nextToken();
//        while (a!=null){
//            System.out.println(a);
//            a = myTokens.nextToken();
//        }
//    }
//
//    private int getindex(String name){
//        int index = -1;
//        for (int i=0; i<this.fileName.length;i++){
//            System.out.println(fileName[i]);
//            if (name.equals(fileName[i])){
//                index = i;
//                break;
//            }
//        }
//        return index;
//    }

    public int getSize(){
        return this.size;
    }
    public String[] getFileName(){
        return this.fileName;
    }
    public String[] getFilePath(){
        return this.filePath;
    }
}
