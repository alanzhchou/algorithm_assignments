import java.io.IOException;
import java.util.HashMap;

/**
 * Created by ZH-AlanChou on 2017/10/13.
 */

/*
此方法用来建立每个路径下，文件所存储的所有字库，用键值对来存储，
键字符为库的名字，值为对应的语言词库的字符串数组
 */
public class WordsLibrary {
    private String[] fileName;
    private String[] filePath;

    PathMapping mapping;
    private HashMap path_mapping  = new HashMap();
    private int size = 0;

    public WordsLibrary() throws IOException {
        PathMapping mapping = new PathMapping();
        this.mapping = mapping;

        this.path_mapping = mapping.getPathMapping();
        this.size = mapping.getSize();

        this.fileName = new String[this.size];
        this.filePath = new String[this.size];
    }

    //操作哈希表和此类中的两个
    public void fill_fileName_and_filePath(){

    }

    public PathMapping getMapping(){
        return this.mapping;
    }
}
