package com.sort;

import java.util.Arrays;

/**
 * 选择排序
 * @author : chenqingsong
 * @date : 2019-06-29 22:45
 */
public class SelectSort {
    public static void main(String[] args) {
      //  int arr[] = {2,6,3,5,9};
      //  selectSort(arr);
       // System.out.println(Arrays.toString(arr));

        int arr[] = new int[80000];
        for(int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 8000000);
        }

        Long startTime = System.currentTimeMillis() / 1000;
        selectSort(arr);
        Long endTime = System.currentTimeMillis() / 1000;
        //   System.out.println("排序后 " + Arrays.toString(arr));
        System.out.println("化费时间：" + (endTime - startTime) + "秒");
    }

    //选择排序
    public static void selectSort(int[] arr){
        //原始数组 101，34，119，1
        for(int i = 0; i < arr.length - 1; i++){
            int minIdex = i;
            int min = arr[i];
            for(int j = i; j < arr.length - 1 ; j++){
                if(min > arr[j]){
                    min = arr[j]; //重置min
                    minIdex = j; // 重置minIndex
                }
            }
            //将最小值交换
            arr[minIdex] = arr[i];
            arr[i] = min;
        }
    }
}
