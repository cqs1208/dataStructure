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
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式： " + infixExpressionList);

        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式： " + parseSuffixExpressionList);
        //先定义一个逆波兰表达式
        //(3+4*5-6) => 3 4 + 5 * 6 -
        String suffixExpression = "3 4 + 5 * 6 -";
      /*  //1， 先将3 4 + 5 * 6 - =》 放到ArrayList中
        //2, 将ArrayList 传递给一个方法，配合栈完成计算

        List<String> rpnList = getListString(suffixExpression);
      //  System.out.println("rpnList : " + rpnList);

        int res = calculate(rpnList);
        System.out.println(res);*/
    }

    //中缀表达式转后缀表达式
    public static  List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<>();
        //因为stack2，在整个转换过程中，没有pop，而且后面还需要逆序输出
        //因此直接用List
        List<String> s2 = new ArrayList<>();

        //遍历
        for(String item : ls){
            //如果是一个数，加入到s2
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果")" 顺序将s1中的运算符弹出到s2中，直到遇到"("，此时，运算符丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop(); // 将"（" 弹出
            }else {
                //遇到运算符时，比较s1栈顶运算符的优先级
                // *   1，栈空或为“（”则直接入栈
                // *   2，否则，若优先级高于栈顶运算符 ，直接入栈
                // *   3，否则，将s1栈中的运算符弹出压如s2栈中，再按4.1步骤进行比较
                while (s1.size() != 0  && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈顶
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }

    //将中缀表达式转list
    public static  List<String> toInfixExpressionList(String s){
        //定义一个list，存放中缀表达式对应的值
        List<String> ls = new ArrayList<>();
        int i = 0; //这是一个指针，用于遍历中缀表达式字符串
        String str; //对多位数的拼接
        char c ; //每遍历到一个字符，就放入到c
        do{
            //如果c是一个非数字，加入到ls
            if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                ls.add("" + c);
                i++;
            }else {
                //如果是一个数，需要考虑多位数的问题
                str = ""; //先将str置""
                while (i < s.length() && (c=s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += c; //拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i < s.length());
        return ls;
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

//编写一个类 Operation 返回一个运算符对应的优先级
class  Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static  int MUL = 2;
    private static int DIV = 2;

    //返回对应的优先级
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+" :
                result = ADD;
                break;
            case "-" :
                result = SUB;
                break;
            case "*" :
                result = MUL;
                break;
            case "/" :
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
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