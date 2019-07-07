package com.sort;

import java.util.Arrays;

/**
 * 基数排序
 * @author : chenqingsong
 * @date : 2019-07-06 17:15
 */
public class RadixSort {
    public static void main(String[] args) {

      //  int arr[] = {53, 3, 542, 748, 14, 214};

        int arr[] = new int[30000000];
        for(int i = 0; i < 30000000; i++){
            arr[i] = (int)(Math.random() * 1600000000);
        }

        Long startTime = System.currentTimeMillis() / 1000;
        radixSort(arr);
        Long endTime = System.currentTimeMillis() / 1000;
        //   System.out.println("排序后 " + Arrays.toString(arr));
        System.out.println("化费时间：" + (endTime - startTime) + "秒");

      //  radixSort(arr);
      //  System.out.println(Arrays.toString(arr));
    }

    //基数排序
    public static void radixSort(int arr[]) {
        //得到最大的数的位数
        int max = arr[0]; //假设第一个是最大数
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        //第一轮排序 针对每个元素的各位进行排序
        //定义一个二维数组，每个桶表示一个数组
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少数据，我们定义了一个一维数组来记录各个桶的每次放入的数据个数
        //可以理解为
        //比如：bucketElementCounts[0], 记录的就是bucket[0]桶的放入的数据个数
        int[] bucketElementCounts = new int[10];


        for(int i = 0, n = 1;  i < maxLength; i++, n *= 10){
            //(针对每个元素的对应位进行排序处理，第一次各位，第二次。。)
            for(int j = 0; j < arr.length; j++){
                //取出个位的值
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据， 放入原来数组
            int index = 0;
            //遍历每一桶，并将桶中的数据，放入到原数组
            for(int k = 0; k < bucketElementCounts.length; k++){
                //如果桶中有数据，我们才放入到原素组
                if(bucketElementCounts[k] != 0){
                    //循环该桶，即第k个桶
                    for(int l = 0; l < bucketElementCounts[k]; l++){
                        //取出元素
                        arr[index++] = bucket[k][l];
                    }

                }
                //将桶清0
                bucketElementCounts[k] = 0;
            }
        }


    }
}
