package com.huffmanCode;

import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] contentBytes = str.getBytes();
        System.out.println(contentBytes.length);

        List<Node> nodes = getNodes(contentBytes);
        Node node = createHuffmanTree(nodes);
        System.out.println(nodes);
        preOrder(node);

    }

    //
    private static void preOrder(Node node){
        if(null != node){
            node.preOrder();
        }
    }

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

    //
    private static  Node createHuffmanTree(List<Node> nodes){
        while(nodes.size() > 1){
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