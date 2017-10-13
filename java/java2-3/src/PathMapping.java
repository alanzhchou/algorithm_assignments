/**
 * Created by ZH-AlanChou on 2017/10/13.
 */
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/*
这个类是用来返回文件及对应的文件路径的哈希表的，
储存形式为“文件名：文件相对路径”,如“polish.txt:languages\polish.txt”
1.默认构造函数为空，生成对象后，
2.用getPathMapping(String folder_path)调用查询某目录下的所有文件
返回值为此哈希表（调用前抛出IOException表示文件路径错误）
3.提供了一个public的toString方法（返回null），用来在控制台输出所有键值对，来检测是否运行正常
 */
public class PathMapping {
    private HashMap path_mapping  = new HashMap();
    private int size = 0;
    ReadCnf cnf;

    public PathMapping(){
        this.cnf = new ReadCnf();
    }

    public HashMap<String,String> getPathMapping() throws IOException{
//      try (Stream<Path> paths = Files.walk(Paths.get(folder_path))) {
//          paths
//                 .filter(Files::isRegularFile)
//                 .forEach(System.out::println);
//      }
        String folder_path = this.cnf.getStop_words_dir();

        path_mapping.clear();
        File folder = new File(folder_path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                StringBuffer name = new StringBuffer(file.getName());
                StringBuffer path = new StringBuffer(file.getPath());
                path_mapping.put(name,path);
                size++;
            }
        }
        return path_mapping;
    }

    public String toString(){
        Iterator iter_2 = path_mapping.entrySet().iterator();
        while (iter_2.hasNext()) {
            Map.Entry entry = (Map.Entry) iter_2.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + ":" + value);
        }
        return null;
    }

    public int getSize(){
        return this.size;
    }
}
