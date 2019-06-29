package com.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        //完成中缀表达式转后缀表达式
        //说明
        // 1, 1+((2+3)*4)-5 => 1 2 3 + 4 * 5 -
        // 2, 直接对一个字符串操作不方便，因此，先转成list
        String expression = "1+((2+3)*4)-5";


        //先定义一个逆波兰表达式
        //(3+4*5-6) => 3 4 + 5 * 6 -
        String suffixExpression = "3 4 + 5 * 6 -";
        //1， 先将3 4 + 5 * 6 - =》 放到ArrayList中
        //2, 将ArrayList 传递给一个方法，配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
      //  System.out.println("rpnList : " + rpnList);

        int res = calculate(rpnList);
        System.out.println(res);
    }

    //将中缀表达式转list
    public static  List<String> toInfixExpressionList(String suffixExpression){
        //定义一个list，存放中缀表达式对应的值
        List<String> ls = new ArrayList<>();
        int i = 0; //这是一个指针，用于遍历中缀表达式字符串
        String str; //对多位数的拼接
        char c ; //每遍历到一个
        return null;
    }

    //将一个逆波兰表达式依次将数据和运算符放入到ArrayList 中
    public static List<String> getListString (String suffixExpression){
        //将suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for(String str : split){
            list.add(str);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /**
     * 1, 从左至右扫描，将3和4压入栈中
     * 2，遇到+运算符，因此弹出3和4 计算得7 ，再将7压入栈中
     * 3，将5压入栈中
     * 4，遇到* 将5和7弹出，计算得35 再将35压入栈中
     * 5，将6入栈
     * 6，最后是-运算符，计算35-6 的29
     */
    public static int calculate(List<String> ls){
        //创建栈
       Stack<String> stack = new Stack<>();
       for(String item : ls){
           //使用正则表达式
           if(item.matches("\\d+")){// 匹配多位数
               //入栈
               stack.push(item);
           }else{
               int num2 = Integer.valueOf(stack.pop());
               int num1 = Integer.valueOf(stack.pop());
               int res = 0;
               if(item.equals("+")){
                   res = num1 + num2;
               }else if(item.equals("-")){
                   res = num1 - num2;
               }else if(item.equals("*")){
                   res = num1 * num2;
               }else if(item.equals("/")){
                   res = num1 / num2;
               }
               stack.push(res + "");
           }
       }
       return Integer.valueOf(stack.pop());
    }
}

/**
 * 1,初始化两个栈，运算符栈s1和操作结果栈s2
 * 2，从左至右扫描
 * 3，遇到数字，压如s2栈
 * 4，遇到运算符时，比较s1栈顶运算符的优先级
 *   1，栈空或为“（”则直接入栈
 *   2，否则，若优先级高于栈顶运算符 ，直接入栈
 *   3，否则，将s1栈中的运算符弹出压如s2栈中，再按4.1步骤进行比较
 * 5，如果遇到括号
 *   1，如果"(" 直接入栈
 *   2，如果")" 顺序将s1中的运算符弹出到s2中，直到遇到"("，此时，运算符丢弃
 *
 */