package com.stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试
        //创建ArrayStack
        ArrayStack arrayStack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("s(show): 显示栈");
            System.out.println("e(exit): 退出程序");
            System.out.println("p(push): 添加数据到栈");
            System.out.println("g(pop): 从栈中取出数据");
            key = scanner.next();//接收一个字符
            switch (key) {
                case "s":
                    arrayStack.show();
                    break;
                case "p":
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "g":
                    try {
                        int res = arrayStack.pop();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    break;
                default:
            }
        }
    }
}

//定义ArrayStack类，表示一个栈
class ArrayStack {
    private int maxSize; //栈的大小
    private int[] stack;  //数组模拟栈
    private int top = -1; //栈顶

    public ArrayStack (int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop (){
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历
    public void show(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }

        for(int i = top; i >= 0; i--){
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
