package com.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * @author : chenqingsong
 * @date : 2019-07-05 20:48
 */
public class ShellSort {
    public static void main(String[] args) {
      //  int[] arr = {8,9,1,7,2,3,5,4,6,0};
        int arr[] = new int[16000000];
        for(int i = 0; i < 16000000; i++){
            arr[i] = (int)(Math.random() * 1600000000);
        }

        Long startTime = System.currentTimeMillis() / 1000;
        shellSort2(arr);
        Long endTime = System.currentTimeMillis() / 1000;
        //   System.out.println("排序后 " + Arrays.toString(arr));
        System.out.println("化费时间：" + (endTime - startTime) + "秒");
    }

    //使用逐步推导方式来编写希尔排序
    //对有序序列在插入时采用(交换法)
    public static void shellSort(int[] arr){

        int temp = 0;
        for(int gap = arr.length / 2; gap > 0; gap /= 2){ //
            //希尔排序第一轮
            //因为第一轮排序，是将十个数据分为五组
            for(int i = gap; i < arr.length; i++){
                //遍历各组所有的元素（共五组，每组2个元素），步长5
                for(int j = i - gap; j >= 0; j -= gap){
                    //如果当前元素大于步长后的那个数，说明需要排序
                    if(arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //对有序序列在插入时采用(移位法)
    public static void shellSort2(int[] arr){

        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            //希尔排序第一轮
            //增量gap，并逐步缩小增量
            for(int i = gap; i < arr.length; i++){
                int j = i;
                int temp = arr[j];
                if(arr[j] < arr[j - gap]){
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出循环后，就给temp找到了插入的位置
                    arr[j] = temp;
                }
            }
        }
       // System.out.println(Arrays.toString(arr));
    }
}
