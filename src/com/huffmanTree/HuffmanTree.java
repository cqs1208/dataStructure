package com.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree{
    public static void main(String[] args) {
        int arr[] = {13,7,8,3,29,6,1};
        Node node = createHuffmanTree(arr);
        preOrder(node);
    }

    //编写前序遍历的方法
    public static  void preOrder(Node node){
        if(node != null){
            node.preOrder();
        }else {
            System.out.println("孔数");
        }
    }

    //创建赫夫曼树
    public static Node createHuffmanTree(int[] arr){
        //1，遍历arr数组
        //2，将arr的每个元素构成一个Node
        //3，将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for(int value : arr){
            nodes.add(new Node(value));
        }

        while(nodes.size() > 1){
            //排序
            Collections.sort(nodes);

            //取出权值最小的二叉树
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            //构建一颗新的二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            //从ArrayList中删除使用过的二叉树
            nodes.remove(left);
            nodes.remove(right);
            //将parent加入到到ArrayList中
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

//创建节点类
class Node implements Comparable<Node>{
    int value; //节点权值
    Node left; //指向左子节点
    Node right; //指向右子节点

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

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
