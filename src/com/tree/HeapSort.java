package com.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        //要求升序排列
      //  int arr[] = {4,6,8,5,9, -1,65,30,-9};

        int arr[] = new int[30000000];
        for(int i = 0; i < 30000000; i++){
            arr[i] = (int)(Math.random() * 1600000000);
        }

        Long startTime = System.currentTimeMillis() / 1000;
        headSort(arr);
        Long endTime = System.currentTimeMillis() / 1000;
        //   System.out.println("排序后 " + Arrays.toString(arr));
        System.out.println("化费时间：" + (endTime - startTime) + "秒");

    }

    //堆排序
    public static void headSort(int arr[]){
        //分步完成
      /*  adjustHeap(arr, 1, 5);
        System.out.println("第一次 " + Arrays.toString(arr));

        adjustHeap(arr, 0, 5);
        System.out.println("第二次 " + Arrays.toString(arr));
*/
        for(int i = arr.length / 2 -1; i >= 0; i--){
            adjustHeap(arr, i, arr.length);
        }

        //2, 将堆顶元素和末尾元素交换
        //3, 重新调整结构，继续变成大顶堆，对顶元素与末尾元素交换，使数组有序
        int temp = 0;
        for(int j = arr.length - 1; j > 0; j--){
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;

            adjustHeap(arr, 0, j);
        }

       // System.out.println("大顶堆 " + Arrays.toString(arr));
    }

    /**
     * 将一个数组（二叉树）调整成大顶堆
     * 将以i对应的非叶子节点的数调整成大顶堆 {4,6,8,5,9} =》 {4,9,8,5,6}
     * 如果再次调用传入的是i= 0 {4,9,8,5,6} => {9,6,8,5,4}
     * @param arr     原数组
     * @param i       表示非叶子节点在数组中的索引
     * @param length  多少个元素进行调整（length在逐渐减少）
     */
    public static void adjustHeap(int arr[], int i, int length){
        int temp = arr[i]; //先取出当前元素的值
        //开始调整
        //1,  k = i * 2 + 1 i节点的左子节点
        for(int k = i * 2 + 1; k < length; k = k * 2 + 1){
            if( k + 1 < length && arr[k] < arr[k + 1]){ // 左子节点的值小于右子节点
                k++; //k指向右子节点
            }
            if(arr[k] > temp){//如果子节点大于父节点
                arr[i] = arr[k];
                i = k; // 把较大的值赋给当前节点， 让i指向k继续循环比较
            }else{
                break;
            }
        }
        //当for循环结束后，已经将以父节点的最大值，放到了最顶
        arr[i] = temp;//将temp放到调整后的位置
    }
}
