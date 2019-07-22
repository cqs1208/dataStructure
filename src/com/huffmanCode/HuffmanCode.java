package com.huffmanCode;

import java.io.*;
import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
        /*String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();

        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("huffmanBodeByte: " + Arrays.toString(huffmanCodesBytes));

        byte[] bytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println(new String(bytes));*/

        //测试压缩文件
      /*  String srcFile = "/Users/admin/Desktop/定额发票2.png";

        String dstFile = "/Users/admin/Desktop/ceshi.zip";
        String dstFile2 = "/Users/admin/Desktop/ceshi2.zip";

        zipFile(srcFile, dstFile, dstFile2);
        System.out.println("压缩文件成功");*/

        String srcFile = "/Users/admin/Desktop/ceshi.zip";
        String  srcFile2 = "/Users/admin/Desktop/ceshi2.png";
        String  dstFile3 = "/Users/admin/Desktop/ceshi3.png";

        zipFile(srcFile,srcFile2,  dstFile3);
        System.out.println("解压文件成功");

    }

    //文件解压

    /**
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 解压到那个位置
     */
    public static void unZipFile(String zipFile, String zipFile2, String dstFile) throws IOException {
        //创建输出流
        FileInputStream is = null;
        FileInputStream is2 = null;
        OutputStream os = null;
        ObjectInputStream ois = null;
        ObjectInputStream ois2 = null;

        try {
            is = new FileInputStream(zipFile);
            is2 = new FileInputStream(zipFile2);
            ois = new ObjectInputStream(is);
            ois2 = new ObjectInputStream(is2);
            byte[] huffmanBytes = (byte[])ois.readObject();
            //Huffman编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>)ois2.readObject();

            byte[] bytes = decode(huffmanCodes, huffmanBytes);

            os = new FileOutputStream(dstFile);
            //写出数据
            os.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            os.close();
            ois.close();
            is.close();
        }
    }

    //将一个文件压缩

    /**
     *
     * @param srcFile 传入的希望压缩的文件的全路径
     * @param dsFile 压缩后将压缩文件放到哪个目录
     */
    private static  void zipFile(String srcFile, String dsFile, String dsFile2){
        //创建输出流
        FileInputStream is = null;
        OutputStream os = null;
        OutputStream os2 = null;
        ObjectOutputStream oos = null;
        ObjectOutputStream oos2 = null;
        try {
            //创建文件输入流
             is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);

            //获取到文件对应的Huffman编码表
           byte[] huffmanBytes = huffmanZip(b);
           //创建文件输出流
             os = new FileOutputStream(dsFile);
             os2 = new FileOutputStream(dsFile2);
             //创建一个和输出流关联的ObjectOutputStream
             oos = new ObjectOutputStream(os);
             oos2 = new ObjectOutputStream(os2);
             //这里已对象流的方式写入Huffman编码。为了解压使用
             oos.writeObject(huffmanBytes);

             oos2.writeObject(huffmanCodes);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
             oos2.close();
             oos.close();
             os2.close();
             os.close();
             is.close();
            }catch (Exception e){

            }
        }
    }

    //解码

    /**
     *
     * @param huffmanCodes  huffman编码表
     * @param huffmanBytes 编码过的字节数组
     * @return 返回原来字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes){
        //1，先得到huffmanBytes 对应的二进制字符串，形式10100100100010101001
        StringBuilder stringBuilder = new StringBuilder();

        //2,将byte【】 转成二进制的字符串
        for(int i = 0; i < huffmanBytes.length; i++){
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
      //  System.out.println(stringBuilder.toString());

        //把字符串按指定的Huffman编码进行解码
        //把Huffman编码进行调换，因为反向查询
        Map<String, Byte> map = new HashMap<>();
        for(Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }
     //   System.out.println(map);

        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for(int i = 0; i < stringBuilder.length();){
            int count = 1; //小的计数器
            boolean flag = true;
            Byte b = null;

            while(flag){
                //  取出一个1或0
                String key = stringBuilder.substring(i, i+count);//i不动，count移动，直到匹配到一个字符
                b = map.get(key);
                if(null == b){
                    count++;
                }else{

                    flag = false;
                }
            }
            list.add(b);
            i += count; //i直接移动到count位置
        }

        byte[] by = new byte[list.size()] ;
        for(int i = 0; i < by.length; i++){
            by[i] = list.get(i);
        }
        return by;
    }


    /**
     * 将一个byte转成一个二进制的字符串
     * @param b
     * @param flag 标记是否需要补高位 true：补(最后一个不需补高位)
     * @return 是该b 对应的二进制的字符串，（按补码返回）
     */
    private static String byteToBitString(boolean flag , byte b){
        //使用变量保存b
        int temp = b; //将b转成 int

        if(flag){
            //如果是正数，补高位
            temp |= 256;
        }

        String str = Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length() - 8);
        }else{
            return  str;
        }
    }

    //将前面的方法封装起来，便于调用

    /**
     *
     * @param bytes 原始的字符串对应的字节数字
     * @return 返回经过Huffman编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes){

        List<Node> nodes = getNodes(bytes);
        //根据Nodes创建Huffman树
        Node node = createHuffmanTree(nodes);
        ////生成对应的Huffman编码
        Map<Byte, String>  huffmanCodes = getCodes(node);
        //压缩
        byte[] huffmanCodesBytes = zip(bytes, huffmanCodes);

        return huffmanCodesBytes;
    }

    //编写方法，将字符串对应的byte[]数组，通过生成的Huffman编码表，返回一个Huffman编码压缩后的byte数组

    /**
     *
     * @param bytes 原始的字符串对应的bytes
     * @param huffmanCodes 生成的Huffman编码
     * @return 返回Huffman编码处理后的byte[]。8位对应一个byte放入到byte数组中
     * huffmanCodeByte[0] = 10101000 => byte
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        //1,先利用Huffman编码表，将bytes转成Huffman编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();

        //遍历byte数组
        for(byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
      //  System.out.println(stringBuilder.toString());
        //将"1010010100101001010010100101010转成byte数组
        //统计返回byte[]长度
        int len;
        if(stringBuilder.length() % 8 == 0  ){
            len = stringBuilder.length() / 8;
        }else{
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; //记录第一个byte
        for(int i = 0; i < stringBuilder.length(); i+=8){ //每8位对应一个byte
            String stringByte ;
            if(i + 8 > stringBuilder.length()){ //不够8位
                stringByte = stringBuilder.substring(i );
            }else{
                stringByte = stringBuilder.substring(i , i+8);
            }
            //将strByte转成一个byte， 放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(stringByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }


    //生成赫夫曼树对应的赫夫曼编码
    //1，将赫夫曼编码表存放在Map<Byte, String> 形式
    // 32-> 01  97->100  100->1100等等
    static Map<Byte,String> huffmanCodes = new HashMap<>();
    //2, 在生成赫夫曼编码表时需要拼接路径。定义一个StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便
    private static Map<Byte, String> getCodes(Node node){
        if(null == node){
            return null;
        }
        //处理root的左子树
        getCodes(node.left, "0", stringBuilder);
        //处理右子树
        getCodes(node.right, "1", stringBuilder);

        return  huffmanCodes;
    }
    /**
     * 将传入的Node节点的所有叶子节点的Huffman编码，存放到huffmanCodes集合
     * @param node 传入节点
     * @param code 路径 左子节点是0 右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2中
        stringBuilder2.append(code);
        if(node != null){ //如果node == null 不处理
            //判断当前弄得是叶子节点还是非叶子节点
            if(node.data == null){//非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            }else {//说明是叶子节点
                //就表示找到某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //
    private static void preOrder(Node node){
        if(null != node){
            node.preOrder();
        }
    }

    /**
     *
     * @param bytes 接收字符数组
     * @return 返回的就是List形式
     */
    public static List<Node> getNodes(byte[] bytes){
        //创建ArrayList
        List<Node> nodes = new ArrayList<>();
        //存储每个byte出现的次数
        Map<Byte, Integer> map = new HashMap<Byte, Integer>();
        for(byte b : bytes){
            if(null == map.get(b) ){
                map.put(b,1);
            }else{
                map.put(b, map.get(b) + 1);
            }
        }

        //把每个键值对转成Node对象，并加入到nodes集合
        for(Map.Entry<Byte, Integer> entry :   map.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    /**
     *
     * @param nodes
     * @return 返回赫夫曼树根节点
     */
    private static  Node createHuffmanTree(List<Node> nodes){
        while(nodes.size() > 1){
            //从小到大
            Collections.sort(nodes);

            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node parent = new Node(null,left.weight + right.weight);
            parent.left = left;
            parent.right = right;

            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return  nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    Byte data; //存放的数据
    int weight; //权重，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public  void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}