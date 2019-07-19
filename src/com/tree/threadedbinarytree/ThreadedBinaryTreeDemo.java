package com.tree.threadedbinarytree;

/**
 * 线索二叉树
 * @author : chenqingsong
 * @date : 2019-07-07 22:54
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(3, "吴用");
        HeroNode node3 = new HeroNode(6, "卢俊义");
        HeroNode node4 = new HeroNode(8, "林冲");
        HeroNode node5 = new HeroNode(10, "林冲");
        HeroNode node6 = new HeroNode(14, "关胜");

        //
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        binaryTree.threadedNodes();

        //以10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();

        System.out.println("10号节点的前驱节点是：" + leftNode.toString());
        System.out.println("10号节点的后续节点是：" + rightNode.toString());

        System.out.println("使用线索化方式遍历");
        binaryTree.threadedList();
    }
}

//定义二叉树,(线索话二叉树)
class BinaryTree{
    private HeroNode root;

    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //编写对二叉树进行中序线索华

    /**
     *
     */
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //遍历线索化二叉树
    public void threadedList(){
        //定义一个变量，存储当前遍历的节点
        HeroNode node = root;
        while(node != null){
            //循环找到leftType == 1 的节点，第一个是8号节点
            //后面随着遍历而变化，因为当leftType == 1 时，说明是按线索化处理的有效节点
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前这个节点
            System.out.println(node.toString());

            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1){
                //获取当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历的节点
            node = node.getRight();
        }
    }

    /**
     *
     * @param node 就是当前节点的线索化
     */
    public void threadedNodes(HeroNode node){
        if(null == node){
            return;
        }
        //1,先线索化左子树
        threadedNodes(node.getLeft());
        //2线索化当前节点
        //2.1处理当前节点的前驱节点
        if(node.getLeft() == null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型
            node.setLeftType(1);
        }
        //2.2处理当前节点的后续
        if(null != pre && pre.getRight() == null){
            //让前驱节点的右指针指向前驱节点
            pre.setRight(node);
            //修改前驱节点的右指针的类型
            pre.setRightType(1);
        }
        //让当前节点是下一个节点的前驱节点
        pre = node;


        //线索化右子树
        threadedNodes(node.getRight());
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


    //说明
    //1如果leftType == 0 表示指向左子树,leftType == 1 表示指向前驱节点
    //1如果rightType == 0 表示指向右子树,rightType == 1 表示指向后继节点
    private int leftType;
    private int rightType;

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

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

