package com.recursion;

import java.util.Arrays;

/**
 * @author : chenqingsong
 * @date : 2019-06-29 20:26
 */
public class Queue8 {

    //定义一个max表示共有多少个皇后
    int max = 8;

    //定义一个Array 保存皇后放置位置的结果， 比如arr = {0,4,7,5,2,6,1,3,}
    int[] array = new int[max];

    static int count = 0;

    public static void main(String[] args) {
        //测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共有" + count + "种解法");
    }

    //将皇后摆放的位置打印
    private void print(){
        count++;
       for(int i = 0; i < array.length; i++){
           System.out.print(array[i] + " ");
       }
        System.out.println();
    }

    //放置第n个皇后
    //check是每一次递归时，进入到check中都有循环，因此就有回溯
    private void check(int n){
        if(n == max){ // n = 8
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for(int i = 0; i < max; i++){
            //先把当前皇后放到该行的第1列
            array[n] = i;
            //判断当放置第n个皇后时，是否冲突
            if(judge(n)){ //不冲突
                //接着放第n+1个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行array[n] = i, 即将第n个皇后，放置到后移一个位置

        }
    }

    //查看当我们放置第n个皇后时，就去检测该皇后是否已经和前面摆放的皇后冲突
    /**
     * @param n 第n个皇后
     * @return
     */
    private boolean judge(int n){
        for(int i = 0; i < n; i++){
            //1， array[i] == array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //2,  Math.abs(n - i) == Math.abs(array[n] - array[i])
            // 表示判断第n个皇后是否和前面的n-1个皇后在同一斜线
            if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }
}
