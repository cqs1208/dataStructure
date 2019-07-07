package com.sort;

import java.util.Arrays;

/**
 * 归并排序
 * @author : chenqingsong
 * @date : 2019-07-05 22:35
 */
public class MergeSort {
    public static void main(String[] args) {
     //   int arr[] = {8,4,5,7,1,3,6,2};

        int arr[] = new int[20000000];
        for(int i = 0; i < 20000000; i++){
            arr[i] = (int)(Math.random() * 1600000000);
        }

        int temp[] = new int[arr.length]; //归并排序需要一个额外空间\

        Long startTime = System.currentTimeMillis() / 1000;
        mergeSort(arr, 0, arr.length - 1, temp);
        Long endTime = System.currentTimeMillis() / 1000;
        //   System.out.println("排序后 " + Arrays.toString(arr));
        System.out.println("化费时间：" + (endTime - startTime) + "秒");
    }

    //分 + 合的方法
    public static void mergeSort(int arr[], int left, int right, int temp[]){
        if(left < right){
            int mid = (left + right) / 2; //中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);

            //到合并时
            merge(arr, left, mid, right,temp);
        }
    }

    //合并的方法

    /**
     *
     * @param arr  排序的原始数组
     * @param left 初始索引
     * @param mid  中间索引
     * @param right 右边索引
     * @param temp 中转数组
     */
    public static void merge(int arr[], int left, int mid, int right, int temp[]){
        int i = left; //初始化i， 左边有序序列的初始索引
        int j = mid + 1; //初始化j， 右边有序序列的初始索引
        int t = 0; //temp数组的当前索引

        //1，先把左右两边的数据按规则填充到temp数组，直到左右两边有一边处理完毕为止
        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else{
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //2，将剩余数据的一边的数据，依次全部填充到temp
        while(i <= mid){
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while(j <= right){
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //3，将temp数组的元素拷贝到arr
        //并不是每次拷贝8个
        t = 0;
        int tempLeft = left;
        //第一次合并，tempLeft = 0 right = 1
        //第一次合并，tempLeft = 2 right = 3
        //第一次合并，tempLeft = 0 right = 3
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }
}
