package com.tree;

/**
 * @author : chenqingsong
 * @date : 2019-07-07 22:17
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        //创建一个ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0); //1,2,4,5,3,6,7
    }
}


//编写一个ArrBinaryTree,实现顺序存储二叉树遍历
class ArrBinaryTree{
    private int[] arr; //存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder(){
        preOrder(0);
    }

    //编写方法，完成顺序存储二叉树的前序遍历
    /**
     *
     * @param index 数组的下标
     */
    public void preOrder(int index){
        //如果数组为null，或者arr.length = 0
        if(null == arr || arr.length == 0){
            System.out.println("数组为空");
        }
        //输出当前元素
        System.out.println(arr[index]);

        //向左递归遍历
        if(index * 2 + 1 < arr.length){
            preOrder(2 * index + 1);
        }

        //向右递归遍历
        if(index * 2 + 2 < arr.length){
            preOrder(2 * index + 2);
        }
    }
}