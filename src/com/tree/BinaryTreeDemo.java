package com.tree;


/**
 * @author : chenqingsong
 * @date : 2019-07-07 13:05
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree() ;
        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");


        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);

        //前序遍历
       // binaryTree.preOrder();
      //  HeroNode hereNode = binaryTree.preOrderSearch(5);

        //中序遍历
       // binaryTree.infixOrder();
       // HeroNode hereNode = binaryTree.infixOrderSearch(5);
        //后序遍历
       // binaryTree.postOrder();
      //  HeroNode hereNode = binaryTree.postOrderSearch(5);

      /*  if(null == hereNode){
            System.out.println("没有该值");
        }else{
            System.out.println(hereNode.toString());
        }*/

      //测试 删除
        System.out.println("删除前，前序遍历");
        binaryTree.preRoder(); //（1,2,3,5,4）
        binaryTree.delNode(4);
        System.out.println("删除后，前序遍历");
        binaryTree.preRoder(); //（1,2）

    }
}

//定义二叉树
class BinaryTree{
    private HeroNode root;
    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除节点
    public void delNode(int no){
        if(null != root){
            if(no == root.getNo()){
                root = null;
            }else{
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空数，不能删除");
        }
    }

    //前序遍历
    public void preRoder(){
        if(null != this.root){
            root.preRoder();
        }else{
            System.out.println("二叉树为null，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if(null != this.root){
            root.infixOrder();
        }else{
            System.out.println("二叉树为null，无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if(null != this.root){
            root.postOrder();
        }else{
            System.out.println("二叉树为null，无法遍历");
        }
    }

    //前序遍历
    public HeroNode preOrderSearch(int no){
        if(root != null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }

    //中序遍历
    public HeroNode infixOrderSearch(int no){
        if(root != null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }

    //后序遍历
    public HeroNode postOrderSearch(int no){
        if(root != null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }

}


//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    //递归删除节点
    //1，如果删除的节点是叶子节点，则删除该节点
    //2，如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no){
        //思路
        //如果当前节点左不为空，并且左子节点就是要删除的节点，就将this.right = null 并且返回
        if(null != this.left && no == this.left.no){
            this.left = null;
            return;
        }
        //如果当前节点右节点不为空，并且左子节点就是要删除的节点，就将this.right = null 并且返回
        if(null != this.right && no == this.right.no){
            this.right = null;
            return;
        }
        //向左子树递归删除
        if(null != this.left){
            this.left.delNode(no);
        }

        //向右子树递归删除
        if(null != this.right){
            this.right.delNode(no);
        }
    }

    //前序遍历
    public void preRoder(){
        System.out.println(this) ; //先输出父节点
        //递归左子树
        if(null != this.left){
            this.left.preRoder();
        }
        //递归向有字数前序遍历
        if(null != this.right){
            this.right.preRoder();
        }
    }
    //中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if(this.left != null){
            this.left.infixOrder();
        }
        //输出当前节点
        System.out.println(this);

        //递归向右子树中序遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    //后续遍历
    public void postOrder(){
        //递归向左子树后序遍历
        if(this.left != null){
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if(this.right != null){
            this.right.postOrder();
        }
        //输出当前节点
        System.out.println(this);
    }


    //前序遍历

    /**
     *
     * @param no 编号
     * @return 如果找到返回Node ，如果没有找到返回null
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("前序遍历------");
        if(this.no == no){
            return this;
        }

        //则判断当前节点的左子节点是否为null，如果不为null，则递归前序查找
        //如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){//找到
            return resNode;
        }

        //则判断当前节点的右子节点是否为null，如果不为null，则递归前序查找
        //如果右递归前序查找，找到节点，则返回
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历

    /**
     *
     * @param no 编号
     * @return 如果找到返回Node ，如果没有找到返回null
     */
    public HeroNode infixOrderSearch(int no){
        //则判断当前节点的左子节点是否为null，如果不为null，则递归前序查找
        //如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){//找到
            return resNode;
        }

        System.out.println("中序遍历------");
        if(this.no == no){
            return this;
        }

        //则判断当前节点的右子节点是否为null，如果不为null，则递归前序查找
        //如果右递归前序查找，找到节点，则返回
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历

    /**
     *
     * @param no 编号
     * @return 如果找到返回Node ，如果没有找到返回null
     */
    public HeroNode postOrderSearch(int no){


        //则判断当前节点的左子节点是否为null，如果不为null，则递归前序查找
        //如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){//找到
            return resNode;
        }


        //则判断当前节点的右子节点是否为null，如果不为null，则递归前序查找
        //如果右递归前序查找，找到节点，则返回
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }

        if(resNode != null){//找到
            return resNode;
        }

        System.out.println("后序遍历------");
        if(this.no == no){
            return this;
        }

        return resNode;
    }

}
