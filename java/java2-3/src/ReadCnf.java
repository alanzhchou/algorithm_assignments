/**
 * Created by ZH-AlanChou on 2017/10/13.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


//这个类用来读取配置文件,构造函数中就已经初始化读取配置文件，
//并将参数stop_words_dir设置为读取的配置
//用toString方法打印出设置的文件配置地址
//用getStop_words_dir方法返回字符串类型的“设置的文件配置地址”
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
