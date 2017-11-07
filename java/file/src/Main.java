import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        String[] heads = {"id","UTC_date","latitude","longitude","depth","magnitude","region"};
        String[] types = {"int","String","float","float","int","float","String"};
        CVSReader read = new CVSReader("earthquakes.csv",types);

//        for (String head:heads){
//            System.out.println(read.getVectorColValue(head));
//        }

        System.out.println(read.getRowbyKey("624203"));
    }
}

class CVSReader<T>{
    private String title;
    //由于必须对列之后的选取存在一定的顺序性故此处用传统数组代替哈希表存储列名和类型
    private String[] tableHeads;
    private String[] types;
    private Map<String,Vector> tableValues;
    private String[] allItems;

    //此方法只需指定各列的数据类型，即数据文件中必须自带表头
    //指定一个目标文件作为读取的数据库文件，不存在则抛出异常"不存在的，你根本没有这个文件"
    //存在该文件，则设置数据表表名为切割后的文件路径（即文件名去掉后缀）
    //同时读取文件储存在allItems的String[]中供之后调用
    public CVSReader(String filepath,String[] types){
        try {
            File file = new File(filepath);
            BufferedReader reader= new BufferedReader(new FileReader(file));
            //将去后缀的文件名写入实例表名
            setTitle(filepath);

            StringBuffer allItemsBuffer = new StringBuffer();
            String temp = "";
            while (true){
                allItemsBuffer.append(temp);
                allItemsBuffer.append("\n");
                temp = reader.readLine();
                if (temp == null){
                    break;
                }
            }
            //删除首尾两个/n占位符
            allItemsBuffer.deleteCharAt(0);
            allItemsBuffer.deleteCharAt(allItemsBuffer.length()-1);

            StringBuffer forTableHeads = new StringBuffer();
            while (allItemsBuffer.charAt(0) != '\n'){
                forTableHeads.append(allItemsBuffer.charAt(0));
                allItemsBuffer.deleteCharAt(0);
            }
            allItemsBuffer.deleteCharAt(0);

            tableHeads = forTableHeads.toString().split(",");
            allItems = allItemsBuffer.toString().split(",|\\n");

            this.setCols(tableHeads,types);
        } catch (IOException e) {
            System.out.println("不存在的，你根本没有这个文件");
        }
    }

    //此方法需指定各列的名称和数据类型，即数据文件中必须不含自带表头
    //指定一个目标文件作为读取的数据库文件，不存在则抛出异常"不存在的，你根本没有这个文件"
    //存在该文件，则设置数据表表名为切割后的文件路径（即文件名去掉后缀）
    //同时按行读取文件储存在allItems的StringBuffer中供之后调用
    public CVSReader(String filepath,String[] heads, String[] types){
        try {
            File file = new File(filepath);
            BufferedReader reader= new BufferedReader(new FileReader(file));
            //将去后缀的文件名写入实例表名
            setTitle(filepath);

            StringBuffer allItemsBuffer = new StringBuffer();
            String temp = "";

            while (true){
                allItemsBuffer.append(temp);
                allItemsBuffer.append("\n");
                temp = reader.readLine();
                if (temp == null){
                    break;
                }
            }
            //删除首尾两个/n占位符
            allItemsBuffer.deleteCharAt(0);
            allItemsBuffer.deleteCharAt(allItemsBuffer.length()-1);
            this.allItems = allItemsBuffer.toString().split(",|\\n|\"");

            this.setCols(heads,types);
        } catch (IOException e) {
            System.out.println("不存在的，你根本没有这个文件");
        }
    }

    //设置列表的cols,
    // heads代表该列的表头名称比如id，name等，
    // types代表该列采用的储存类型，可选的有"int","String","float"三种
    private void setCols(String[] heads, String[] types){
        try {
            if (heads.length == types.length){
                this.tableHeads = heads;
                this.types = types;
                tableValues = new HashMap<String,Vector>();

                for (int i=0; i<heads.length; i++){
                    tableValues.put(heads[i],new Vector());
                }
            }else {
            }
            //尝试按照切分的allItems的字符串数组来填充各列的List
            this.fill(allItems);
        } catch (Exception e) {
            System.out.println("不可棱，肯定是你col设置的不对");
        }
    }

    //返回查询的name列的值，此方法将所有值以String[]返回
    //若不存在该列，则报错-->"搞错了，这个列有问题，，，"
    public String[] getStringColValue(String name){
        try {
            Vector aim = tableValues.get(name);
            String[] colValues = new String[aim.size()];
            for (int i=0; i<colValues.length; i++){
                colValues[i] = String.valueOf(aim.get(i));
            }
            return colValues;
        }catch (Exception e){
            System.out.println("搞错了，这个列有问题，，，");
        }
        return null;
    }

    //返回查询的name列的值，此方法将所有值以对应类型的Vector返回，即储存的即为正确类型
    //...（储存时正确的话）
    //若不存在该列，则报错-->"搞错了，这个列有问题，，，"
    public Vector getVectorColValue(String name){
        try {
            return tableValues.get(name);
        }
        catch (Exception e){
            System.out.println("搞错了，这个列有问题，，，");
        }
        return null;
    }

    //根据某个key值来寻找并返回该key所在的行的所有数据，以Vector的形式
    //未查找到的话则显示异常："不存在的，没有，，，，"
    public Vector getRowbyKey(String key){
        int index = -1;
        boolean flag = false;
        for (String head:tableHeads){
            Vector search = tableValues.get(head);
            for (int i=0; i<search.size(); i++){
                if (String.valueOf(search.get(i)).equals(key)){
                    index = i;
                    flag = true;
                    break;
                }
            }
            if (flag){
                break;
            }
        }

        Vector row = new Vector();
        try {
            for (String head:tableHeads){
                row.add(tableValues.get(head).get(index));
            }
        }catch (Exception e){
            System.out.println("不存在的，没有，，，，");
        }

        return row;
    }


    /**
     * @param head 表头的名称
     * @return 该列数据储存的类型,不存在时返回"Do Not Exist"
     */
    public String getTypeByHead(String head){
        for (int i=0; i<tableHeads.length; i++){
            if (tableHeads[i] == head){
                return types[i];
            }
        }
        return "Do Not Exist";
    }


    /**
     * @return 数据存储的类型，按tableHeads的顺序
     */
    public String[] getTypes(){
        return this.types;
    }


    /**
     * @return 数据存储的各列表头
     */
    public String[] getTableHeads(){
        return this.tableHeads;
    }

    /**
     * @return 该表的title
     */
    public String getTitle(){
        return this.title;
    }

    private void fill(String[] longString){
        int rank;//遍历时的列
        int colNumbers = tableHeads.length;//总的列数
        try {
            for (int i=0; i<longString.length; i++){
                rank = i%colNumbers;
                switch (types[rank]){
                    case "int":
                        tableValues.get(tableHeads[rank]).add(Integer.parseInt(longString[i]));
                        break;
                    case "float":
                        tableValues.get(tableHeads[rank]).add(Double.parseDouble(longString[i]));
                        break;
                    default:
                        if (longString[i].charAt(0) == '\"')
                            tableValues.get(tableHeads[rank]).add(longString[i].split("\"")[1]);
                        else
                            tableValues.get(tableHeads[rank]).add(longString[i]);
                        break;
                }
            }
        }catch (Exception e){
            System.out.println("填充列的时候错了，，，");
        }
    }

    private void setTitle(String filepath){
        String[] getTitle = filepath.split("/");
        this.title = getTitle[getTitle.length-1].split("\\.")[0];
    }
}