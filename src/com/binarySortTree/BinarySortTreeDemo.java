package com.binarySortTree;

/**
 * @author : chenqingsong
 * @date : 2019-07-21 16:55
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
       int[] arr = {7,3,10,12,5,1,9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();

        for(int i = 0; i < arr.length; i++){
            binarySortTree.add(new Node(arr[i]));
        }

        //删除叶子节点
        //binarySortTree.delNode(7);
       /* binarySortTree.delNode(1);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(10);
        binarySortTree.delNode(3);
        binarySortTree.delNode(7);*/
        binarySortTree.infixOrder();
    }
}

//创建二叉树
class BinarySortTree{
    private Node root;
    //添加
    public void add(Node node){
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    //
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else{
            System.out.println("数为null");
        }
    }

    //查找要删除的节点
    public Node search(int value){
        if(null == root){
            return null;
        }else{
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value){
        if(null == root){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    //1， 返回以node 为根节点的二叉树的最小节点的值
    //2，删除node 为根节点的二叉树的最小节点
    /**
     *
     * @param node 传入的节点
     * @return 返回的以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        while(target.left != null){
            target = target.left;
        }
        //这时target就指向了最小节点
        //删除最小节点
        delNode(target.value);

        return target.value;
    }

    //删除节点
    public void delNode(int value){
        if(null == root){
            return ;
        }else{
            //1,先找到需要删除的节点，targeNode
            Node targetNode = search(value);
            if(null == targetNode){
                return ;
            }

            //如果targetNode没有父节点
            if(null == root.left && null == root.right){
                root = null;
                return ;
            }

            //去找targetNode 的父节点
            Node parent = searchParent(value);

            if(null == targetNode.left && null == targetNode.right){  //叶子节点
                //判断targetNode是父节点的左子节点还是右子节点
                if(parent.left != null && parent.left.value == value){ //左子节点
                    parent.left = null;
                }else if(parent.right != null && parent.right.value == value){ //右子节点
                    parent.right = null;
                }
            }else  if(targetNode.left != null && targetNode.right != null){ // 删除有两颗子树的节点
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;
            }else{ // 删除有一颗子树的节点
                //若果要删除的节点有左子节点
                if(targetNode.left != null){
                    //targetNode是parent的左子节点
                    if(parent.left.value == value){
                        parent.left = targetNode.left;
                    }else{
                        parent.right = targetNode.right;
                    }
                }else{ // 如果要删除的节点有右子节点
                    if(parent.left.value == value){
                        parent.left = targetNode.right;
                    }else{
                        parent.right = targetNode.right;
                    }
                }

            }
        }
    }
}


class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加,递归形式添加
    public void add(Node node){
        if(node == null){
            return;
        }
        //判断传入的节点的值，和当前子树的根节点的关系
        if(node.value < this.value){
            //如果当前节点左子节点为null
            if(this.left == null){
                this.left = node;
            }else{
                //递归的向左子树添加
                this.left.add(node);
            }
        }else{
            //如果当前节点右子节点为null
            if(this.right == null){
                this.right = node;
            }else{
                //递归的向右子树添加
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    //查找要删除的节点

    /**
     *
     * @param value
     * @return
     */
    public Node search(int value){
        if(value == this.value){
            return this;
        }else if(value < this.value){
            if(this.left != null){
                return this.left.search(value);
            }
        }else{
            if(this.right != null){
                return this.right.search(value);
            }
        }
        return null;
    }

    //查找要删除节点的父节点
    public Node searchParent(int value){
        //如果当前节点就是要删除的节点的父节点
        if(this.left != null && this.left.value == value || (this.right != null && this.right.value == value)){
            return this;
        }else {
            //如果查找的值小与当前节点的值， 并且左子节点不为null
            if(value < this.value && this.left != null){
                return this.left.searchParent(value); //左递归
            }else if(value >= this.value && this.right != null){
                return this.right.searchParent(value); //右递归
            }else{
                return null; // 没有找到父节点
            }
        }
    }
}
