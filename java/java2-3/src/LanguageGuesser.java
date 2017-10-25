import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class LanguageGuesser {
    public static void  main(String[] args) throws IOException {
        String file_path = args[0];
        CompareResult result = new CompareResult(file_path);
        //result.toString();
        result.getResult();
    }
}

class CompareResult {
    private String[] fileName;
    private String[] filePath;
    private int size = 0;
    private float[] similarity;

    private String[] current_file_lib;

    public CompareResult(String current_file_path) throws IOException {
        Tokenizer current_file = new Tokenizer(current_file_path);
        this.current_file_lib = current_file.getAllTokens();

        WordsLibrary lib = new WordsLibrary();
        this.fileName = lib.getFileName();
        this.filePath = lib.getFilePath();
        this.size = lib.getSize();
        similarity = new float[this.size];

        this.fill_one_similarity();
    }

    private void fill_one_similarity() throws IOException {
        int all = current_file_lib.length-1;

        for (int index=0; index<fileName.length; index++){
            int same = 0;
            Tokenizer lib = new Tokenizer(filePath[index]);
            String a = lib.nextToken();
            while (a!=null){
                for (int i=0; i<current_file_lib.length-1; i++){
                    if (a.equals(current_file_lib[i])){
                        same++;
                    }
                }
                a = lib.nextToken();
            }
            similarity[index] = (float) same/all;
        }
    }

    public String getResult(){
        String result_language = this.fileName[getMaxIndex()].split("\\.")[0];
        System.out.printf("%s\n",result_language);
        return result_language;
    }

    public String toString(){
        System.out.println("the similarity here:");
        for (int i=0; i<this.similarity.length; i++){
            System.out.printf("%s --> %.3f\n",this.fileName[i],this.similarity[i]);
        }
        return null;
    }

    private int getMaxIndex(){
        int index = 0;
        float max = -1;
        for (int i=0; i<this.similarity.length; i++){
            if (this.similarity[i] > max){
                max = this.similarity[i];
                index = i;
            }
        }
        return index;
    }
}

class WordsLibrary {
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

class Tokenizer {
    private String fileContent = new String("");
    StringBuffer token = new StringBuffer();
    private String[] allToken;

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
        return tok.toLowerCase();
    }

    public String[] getAllTokens(){
        String a = this.nextToken();
        while (a!=null){
            this.token.append(a + ";");
            a = this.nextToken();
        }
        this.allToken = token.toString().split(";");
        return this.allToken;
    }
}

class PathMapping {
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
            }
        }

        Iterator iter_3 = path_mapping.entrySet().iterator();
        while (iter_3.hasNext()) {
            iter_3.next();
            this.size++;
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

class ReadCnf {
    private String stop_words_dir = "";

    public ReadCnf(){
        Properties defprop = new  Properties();
        defprop.put("stop_words_dir","null");

        Properties prop = new Properties(defprop);
        try (BufferedReader conf
                     = new BufferedReader(new FileReader("folder_setting.cnf"))){
            prop.load(conf);
        }catch (IOException e){
            System.err.println("Warning: folder path setting failed");
        }
        this.stop_words_dir = prop.getProperty("stop_words_dir");
    }

    public String getStop_words_dir(){
        return this.stop_words_dir;
    }

    public String toString(){
        System.out.println(this.stop_words_dir);
        return "";
    }
}
