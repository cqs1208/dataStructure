package com.sort;

import java.util.Arrays;

/**
 * @author : chenqingsong
 * @date : 2019-06-30 22:43
 */
public class InsertSort {


    public static void main(String[] args) {

        int arr[] = new int[80000];
        for(int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 8000000);
        }

        Long startTime = System.currentTimeMillis() / 1000;
        insertSort(arr);
        Long endTime = System.currentTimeMillis() / 1000;
        //   System.out.println("排序后 " + Arrays.toString(arr));
        System.out.println("化费时间：" + (endTime - startTime) + "秒");

      //  int arr[] = {101, 34, 119, 1 };
       // insertSort(arr);
    }

    //插入排序
    public static void insertSort(int[] arr){
        //第一轮 101, 34, 119, 1  =》  34, 101, 119, 1

        for(int i = 1; i < arr.length; i++){
            //定义一个待插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1; //即arr[0]这个数的下标

            //给insertVal 找到插入的位置
            //1，insertIndex > 0 保证在给insertVal找插入的位置不越界
            //2, 待插入的数还没有适当位置
            //3，就需要将arr[insertIndex] 后移
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex + 1
            //判断是否需要赋值
            if(insertIndex + 1 == 1){
                arr[insertIndex + 1] = insertVal;
            }
        }
    }

}
