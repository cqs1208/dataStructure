package com.sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author : chenqingsong
 * @date : 2019-07-05 21:41
 */
public class QuickSort {
    public static void main(String[] args) {
       // int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 456};

        int arr[] = new int[170000000];
        for(int i = 0; i < 170000000; i++){
            arr[i] = (int)(Math.random() * 1600000000);
        }

        Long startTime = System.currentTimeMillis() / 1000;
        quickSort(arr, 0, arr.length - 1);
        Long endTime = System.currentTimeMillis() / 1000;
        //   System.out.println("排序后 " + Arrays.toString(arr));
        System.out.println("化费时间：" + (endTime - startTime) + "秒");
    }

    public static void quickSort(int[] arr, int left, int right){
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中间值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量，交换时使用
        //while 循环的目的是让比pivot 值小放到左边
        //比pivot值大放到右边
        while (l < right){
            //在pivot的左边一直找，找到大于等于pivot值，才退出
            while (arr[l] < pivot){
                l += 1;
            }
            //在pivot的右边一直找，找到小于等于pivot值，才退出
            while (arr[r] > pivot){
                r -= 1;
            }

            //如果 l >= r 说明pivot的左右两的值，已经按照左边全部是小与pivot，右边全部是大于pivot的值
            if(l >= r){
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //交换完后，发现arr[l] ==  pivot的值相等，前移
            if(arr[l] == pivot){
                r -= 1;
            }
            //交换完后，发现arr[r] ==  pivot的值相等，后移
            if(arr[r] == pivot){
                l += 1;
            }

        }

        //如果 l == r 必须l++ r--
        if(l == r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if(left < r){
            quickSort(arr, left, r);
        }

        //向右递归
        if(right > l){
            quickSort(arr, l, right);
        }


    }
}
