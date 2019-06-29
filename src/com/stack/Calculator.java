package com.stack;

public class Calculator {
    public static void main(String[] args) {
        //完成表达式的运算
        String expression = "30+9/3+2*6-2";

        //创建两个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0; //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将扫描得到char保存到ch中
        String keepNum = ""; //用于拼接多位数
        //循环扫描
        while (true){
            //一次得到expression每个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch，然后做相应处理
            if(operStack.isOper(ch)){
                //判断当前符号栈是否为null
                if(!operStack.isEmpty()){
                    //处理
                    //比较操作
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        // 运算符号小于栈顶运算符号
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2, oper);
                        //把运算结果入栈
                        numStack.push(res);
                        //当前操作符入栈
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else{
                    //如果为null 直接入栈
                    operStack.push(ch);
                }
            }else { //如果是数，直接入栈
                //numStack.push(ch - 48); //
                //分析思路
                //1，当处理多位数数时不能发现一需要向expression个数字就立即入栈，因为可能是多位数
                //2, 在处理数时，的表达式的index后再看一位，直到是运算符
                //3, 因此需要定义一个变量，用于拼接
                //处理多位数
                keepNum += ch;

                //如果ch是最后，则直接入栈
                if(index == expression.length() -1){
                    numStack.push(Integer.valueOf(keepNum));
                }else{
                    //判断下一个是不是数字，如果是，继续扫描，若果不是，则入栈
                    if(operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))){
                        numStack.push(Integer.valueOf(keepNum));
                        //重要的！！！
                        //keepNum 清空
                        keepNum = "";
                    }
                }

            }
            //让index + 1 ，判断是否扫描到expression 最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }

        //顺序从数栈和符号栈中取数据计算
        while (true){
            //如果符号栈为null，则计算结束
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式%s = %d：" ,expression,  numStack.pop());
    }
}

//定义ArrayStack类，表示一个栈
class ArrayStack2 {
    private int maxSize; //栈的大小
    private int[] stack;  //数组模拟栈
    private int top = -1; //栈顶

    public ArrayStack2 (int maxSize){
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

    // 返回运算符的优先级,优先级用数字表示
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1; //目前表达式只有+-*/
        }
    }

    //判断是不是运算符
    public boolean isOper (char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算
    public int cal (int num1, int num2, int oper){
        int res = 0; //计算的结果
        switch (oper){
            case '+' :
                res = num1 + num2;
                break;
            case '-' :
                res = num2 - num1; //注意顺序
                break;
            case '*' :
                res = num1 * num2;
                break;
            case '/' :
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    //返回栈顶数据，不出栈
    public int peek(){
        return stack[top];
    }
}

