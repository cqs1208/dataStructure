package com.sort;

import java.util.Arrays;

/**
 * @author : chenqingsong
 * @date : 2019-06-29 22:02
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int arr[] = {3, 9, -1, 10, 20};

        int arr[] = new int[80000];
        for(int i = 0; i < 80000; i++){
            arr[i] = (int)(Math.random() * 8000000);
        }

        //测试冒泡
      //  System.out.println("排序前 " + Arrays.toString(arr) );
        Long startTime = System.currentTimeMillis() / 1000;
        bubboleSort(arr);
        Long endTime = System.currentTimeMillis() / 1000;
     //   System.out.println("排序后 " + Arrays.toString(arr));
        System.out.println("化费时间：" + (endTime - startTime) + "秒");


    }

    public static void bubboleSort(int arr[]){
        //第一趟，将最大的数排在最后
        int temp = 0;
        boolean flag = false; //标识变量，标识是否进行过交换
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j < arr.length - 1 - i; j++){
                //如果前面的数比后面的数大，则交换
                if(arr[j] > arr[j + 1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(!flag){//在一趟交换中，一次交换都没有发生过
                break;
            }else{
                flag = false; //重复flag，进行过下次交换
            }
        }
    }
}
