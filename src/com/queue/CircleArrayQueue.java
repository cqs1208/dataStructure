package com.queue;

import java.util.Scanner;

/**
 * @author : chenqingsong
 * @date : 2019-06-22 23:27
 */
public class CircleArrayQueue {

    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列案例---");
        //创建一个队列
        CircleArray queue = new CircleArray(4);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列中取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's' :
                    queue.showQueue();
                    break;
                case 'a' :
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g' :
                    try{
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h' :
                    try{
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e' :
                    scanner.close();
                    loop = false;
                    break;
                default:

            }

        }
        System.out.println("程序退出--");
    }

}

class CircleArray {
    private int maxSize; //表示数组的最大容量
    // 队列的第一个元素的位置, 也就是说arr[font]就是队列的第一个元素
    //font的初始值为0
    private int front;
    //rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定
    //rear的初始值为0
    private int rear;
    private int[] arr; //该数据用于存储数据，模拟队列

    public CircleArray (int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断是否满
    //当队列满时，条件是 ( rear + 1 ) % maxSize = font
    public boolean isFull(){
        return ( rear + 1 ) % maxSize == front;
    }

    //判断队列是否为null
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断杜烈是否满
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，治理必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否null
        if(isEmpty()){
            throw new RuntimeException("队列null，不能取数据");
        }
        //这里需要分析出，font是指向队列的第一个元素
        //1， 先把font 对应的值保留到一个临时变量
        //2, 将font后移
        //3, 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空，没有数据---");
        }
        // 从font开始遍历，遍历多少个元素
        //
        for(int i = front; i < front + size(); i++){
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列的有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    // 显示杜烈的头数据，注意不是取出数据
    public int headQueue(){
        //判断
        if(isEmpty()){
            throw new RuntimeException("队列空的，没有数据--");
        }
        return arr[front];
    }
}
